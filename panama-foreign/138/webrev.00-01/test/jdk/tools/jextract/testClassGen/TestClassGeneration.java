/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @modules jdk.incubator.jextract
 *          jdk.incubator.foreign/jdk.incubator.foreign.unsafe
 *          jdk.incubator.foreign/jdk.internal.foreign
 *          jdk.incubator.foreign/jdk.internal.foreign.abi
 *          java.base/sun.security.action
 * @library .. /test/lib
 * @build JextractToolRunner
 * @run testng/othervm -Dforeign.restricted=permit -Duser.language=en TestClassGeneration
 */

import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemoryLayout;
import jdk.incubator.foreign.MemorySegment;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static java.lang.invoke.MethodType.methodType;
import static jdk.incubator.foreign.MemoryLayout.PathElement.sequenceElement;
import static jdk.incubator.foreign.MemoryLayouts.C_CHAR;
import static jdk.incubator.foreign.MemoryLayouts.C_DOUBLE;
import static jdk.incubator.foreign.MemoryLayouts.C_FLOAT;
import static jdk.incubator.foreign.MemoryLayouts.C_INT;
import static jdk.incubator.foreign.MemoryLayouts.C_LONGLONG;
import static jdk.incubator.foreign.MemoryLayouts.C_SHORT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class TestClassGeneration extends JextractToolRunner {

    private static final VarHandle VH_bytes = MemoryLayout.ofSequence(C_CHAR).varHandle(byte.class, sequenceElement());

    private Path outputDir;
    private Loader loader;
    private Class<?> cls;

    @DataProvider
    public static Object[][] simpleConstants() {
        return new Object[][]{
            { "macro_byte",         byte.class,   (byte) 1                         },
            { "macro_short",        short.class, (short) 1                         },
            { "macro_int",          int.class,           1                         },
            { "macro_long",         long.class,          1L                        },
            { "macro_float",        float.class,         1.0F                      },
            { "macro_double",       double.class,        1.0D                      },
            { "macro_address_NULL", MemoryAddress.class, MemoryAddress.NULL        },
            { "macro_address_123",  MemoryAddress.class, MemoryAddress.ofLong(123) },
            { "enum_0",             int.class,           0                         },
            { "enum_1",             int.class,           1                         },
            { "enum_2",             int.class,           2                         },
            { "enum_anon_0",        int.class,           0                         },
            { "enum_anon_1",        int.class,           1                         },
            { "enum_anon_2",        int.class,           2                         },
        };
    }

    @DataProvider
    public static Object[][] stringConstants() {
        return new Object[][]{
            { "macro_string",         "abc"      },
            { "macro_string_noident", "123.asdf" },
        };
    }

    private static final Object[] NO_ARGS = {};

    @DataProvider
    public static Object[][] method() {
        return new Object[][]{
            { "func_byte",   methodType(byte.class),   (byte) 1,  NO_ARGS },
            { "func_short",  methodType(short.class), (short) 2,  NO_ARGS },
            { "func_int",    methodType(int.class),           3,  NO_ARGS },
            { "func_long",   methodType(long.class),          4L, NO_ARGS },
            { "func_float",  methodType(float.class),         5F, NO_ARGS },
            { "func_double", methodType(double.class),        6D, NO_ARGS },
        };
    }

    @DataProvider
    public static Object[][] globals() {
        return new Object[][]{
            { "global_byte",   byte.class,   C_CHAR,   (byte) 1  },
            { "global_short",  short.class,  C_SHORT, (short) 2  },
            { "global_int",    int.class,    C_INT,           3  },
            { "global_long",   long.class,   C_LONGLONG,      4L },
            { "global_float",  float.class,  C_FLOAT,         5F },
            { "global_double", double.class, C_DOUBLE,        6D },
        };
    }

    @DataProvider
    public static Object[][] structMembers() {
        return new Object[][] {
            { "Foo", C_CHAR.withName("c"),      byte.class,   (byte) 10  },
            { "Foo", C_SHORT.withName("s"),     short.class, (short) 10  },
            { "Foo", C_INT.withName("i"),       int.class,           10  },
            { "Foo", C_LONGLONG.withName("ll"), long.class,          10L },
            { "Foo", C_FLOAT.withName("f"),     float.class,         10F },
            { "Foo", C_DOUBLE.withName("d"),    double.class,        10D },
            { "Bar", C_INT.withName("a"),       int.class,           10 },
            { "Bar", C_INT.withName("b"),       int.class,           10 },
        };
    }

    @DataProvider
    public static Object[][] functionalInterfaces() {
        return new Object[][]{
            { "func_cb$cb", methodType(void.class, int.class) }
        };
    }

    @Test(dataProvider = "simpleConstants")
    public void testConstant(String name, Class<?> expectedType, Object expectedValue) throws Throwable {
        Method getter = checkMethod(cls, name, expectedType);
        assertEquals(getter.invoke(null), expectedValue);
    }

    @Test(dataProvider = "stringConstants")
    public void testStringConstant(String name, String expectedValue) throws Throwable {
        Method getter = checkMethod(cls, name, MemoryAddress.class);
        MemoryAddress actual = (MemoryAddress) getter.invoke(null);
        byte[] expected = expectedValue.getBytes(StandardCharsets.UTF_8);
        assertEquals(actual.segment().byteSize(), expected.length + 1);
        for (int i = 0; i < expected.length; i++) {
            assertEquals((byte) VH_bytes.get(actual, (long) i), expected[i]);
        }
    }

    @Test(dataProvider = "method")
    public void testMethod(String name, MethodType expectedType, Object expectedReturn, Object[] args) throws Throwable {
        Method mh_getter = checkMethod(cls, name + "$MH", MethodHandle.class);
        MethodHandle mh = (MethodHandle) mh_getter.invoke(null);
        assertEquals(mh.type(), expectedType);

        Object actualReturn = mh.invokeWithArguments(args);
        assertEquals(actualReturn.getClass(), expectedReturn.getClass());
        assertEquals(actualReturn, expectedReturn);

        Method func = checkMethod(cls, name, expectedType);
        assertEquals(func.invoke(null, args), expectedReturn);
    }

    @Test(dataProvider = "globals")
    public void testGlobal(String name, Class<?> expectedType, MemoryLayout expectedLayout, Object expectedValue) throws Throwable {
        Method layout_getter = checkMethod(cls, name + "$LAYOUT", MemoryLayout.class);
        assertEquals(layout_getter.invoke(null), expectedLayout);

        Method addr_getter = checkMethod(cls, name + "$ADDR", MemoryAddress.class);
        MemoryAddress addr = MemorySegment.ofNativeRestricted(
                (MemoryAddress)addr_getter.invoke(null),
                expectedLayout.byteSize(),
                null, null, null).baseAddress();

        Method vh_getter = checkMethod(cls, name + "$VH", VarHandle.class);
        VarHandle vh = (VarHandle) vh_getter.invoke(null);
        assertEquals(vh.varType(), expectedType);
        assertEquals(vh.get(addr), expectedValue);

        checkMethod(cls, name + "$get", expectedType);
        checkMethod(cls, name + "$set", void.class, expectedType);
    }

    @Test(dataProvider = "structMembers")
    public void testStructMember(String structName, MemoryLayout memberLayout, Class<?> expectedType, Object testValue) throws Throwable {
        String memberName = memberLayout.name().orElseThrow();

        Class<?> structCls = loader.loadClass("com.acme.examples_h$C" + structName);
        Method layout_getter = checkMethod(structCls, "$LAYOUT", MemoryLayout.class);
        MemoryLayout structLayout = (MemoryLayout) layout_getter.invoke(null);
        try (MemorySegment struct = MemorySegment.allocateNative(structLayout)) {
            Method vh_getter = checkMethod(structCls, memberName + "$VH", VarHandle.class);
            VarHandle vh = (VarHandle) vh_getter.invoke(null);
            assertEquals(vh.varType(), expectedType);

            Method getter = checkMethod(structCls, memberName + "$get", expectedType, MemoryAddress.class);
            Method setter = checkMethod(structCls, memberName + "$set", void.class, MemoryAddress.class, expectedType);
            MemoryAddress addr = struct.baseAddress();
            setter.invoke(null, addr, testValue);
            assertEquals(getter.invoke(null, addr), testValue);
        }
    }

    @Test(dataProvider = "functionalInterfaces")
    public void testFunctionalInterface(String name, MethodType type) {
        Class<?> fiClass = findNestedClass(cls, name);
        assertNotNull(fiClass);
        checkMethod(fiClass, "apply", type);
        checkMethod(fiClass, "allocate", MemoryAddress.class, fiClass);
    }

    @BeforeClass
    public void setup() {
        outputDir = getOutputFilePath("exmples_out");
        Path inputHeader = getInputFilePath("examples.h");
        run(
            "-t", "com.acme",
            "-d", outputDir,
            "-l", "Examples",
            "--",
            inputHeader
        ).checkSuccess();
        loader = classLoader(outputDir);
        cls = loader.loadClass("com.acme.examples_h");
    }

    @AfterClass
    public void tearDown() {
        loader.close();
        deleteDir(outputDir);
    }

}
