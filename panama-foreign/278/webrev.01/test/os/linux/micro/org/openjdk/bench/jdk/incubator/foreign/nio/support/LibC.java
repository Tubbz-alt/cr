// Generated by jbind

package org.openjdk.bench.jdk.incubator.foreign.nio.support;

import jdk.incubator.jbind.core.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import jdk.incubator.foreign.*;
import jdk.incubator.foreign.MemoryLayout.PathElement;
import static jdk.incubator.foreign.CSupport.*;

import java.util.function.LongFunction;

public final class LibC {
    private static final LibraryLookup[] LIBRARIES = RuntimeHelper.libraries(new String[] {});
    public static final class stat extends Struct<stat> {
        protected stat(MemorySegment ms) { super(ms); }
        public static final stat at(MemorySegment ms) { return new stat(ms); }
        public static final stat allocate(LongFunction<MemorySegment> allocator, int count) {
            return new stat(allocator.apply(sizeof() * count));
        }
        public static final stat allocate(LongFunction<MemorySegment> allocator) { return allocate(allocator, 1); }
        public final stat offset(int count) { return at(segment().asSlice(sizeof() * count)); }

        public static final GroupLayout $LAYOUT = MemoryLayout.ofStruct(
            C_LONG.withName("st_dev"),
            C_LONG.withName("st_ino"),
            C_LONG.withName("st_nlink"),
            C_INT.withName("st_mode"),
            C_INT.withName("st_uid"),
            C_INT.withName("st_gid"),
            C_INT.withName("__pad0"),
            C_LONG.withName("st_rdev"),
            C_LONG.withName("st_size"),
            C_LONG.withName("st_blksize"),
            C_LONG.withName("st_blocks"),
            MemoryLayout.ofStruct(
                C_LONG.withName("tv_sec"),
                C_LONG.withName("tv_nsec")
            ).withName("st_atim"),
            MemoryLayout.ofStruct(
                C_LONG.withName("tv_sec"),
                C_LONG.withName("tv_nsec")
            ).withName("st_mtim"),
            MemoryLayout.ofStruct(
                C_LONG.withName("tv_sec"),
                C_LONG.withName("tv_nsec")
            ).withName("st_ctim"),
            MemoryLayout.ofSequence(3, C_LONG).withName("__glibc_reserved")
        ).withName("stat");
        public static final long sizeof() { return $LAYOUT.byteSize(); }
        public static final long offsetof(String fieldName) { return $LAYOUT.byteOffset(MemoryLayout.PathElement.groupElement(fieldName)); }
        @Override
        public final GroupLayout getLayout() { return $LAYOUT; }

        public static final VarHandle st_dev$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("st_dev")));
        public static final long st_dev$OFFSET = 0L;
        public final MemorySegment st_dev$ptr() {
            return segment().asSlice(0L);
        }
        public final long st_dev$get() {
            return (long) st_dev$VH.get(st_dev$ptr());
        }
        public final void st_dev$set(long value) {
            st_dev$VH.set(st_dev$ptr(), value);
        }

        public static final VarHandle st_ino$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("st_ino")));
        public static final long st_ino$OFFSET = 8L;
        public final MemorySegment st_ino$ptr() {
            return segment().asSlice(8L);
        }
        public final long st_ino$get() {
            return (long) st_ino$VH.get(st_ino$ptr());
        }
        public final void st_ino$set(long value) {
            st_ino$VH.set(st_ino$ptr(), value);
        }

        public static final VarHandle st_nlink$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("st_nlink")));
        public static final long st_nlink$OFFSET = 16L;
        public final MemorySegment st_nlink$ptr() {
            return segment().asSlice(16L);
        }
        public final long st_nlink$get() {
            return (long) st_nlink$VH.get(st_nlink$ptr());
        }
        public final void st_nlink$set(long value) {
            st_nlink$VH.set(st_nlink$ptr(), value);
        }

        public static final VarHandle st_mode$VH = RuntimeHelper.varHandle(int.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("st_mode")));
        public static final long st_mode$OFFSET = 24L;
        public final MemorySegment st_mode$ptr() {
            return segment().asSlice(24L);
        }
        public final int st_mode$get() {
            return (int) st_mode$VH.get(st_mode$ptr());
        }
        public final void st_mode$set(int value) {
            st_mode$VH.set(st_mode$ptr(), value);
        }

        public static final VarHandle st_uid$VH = RuntimeHelper.varHandle(int.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("st_uid")));
        public static final long st_uid$OFFSET = 28L;
        public final MemorySegment st_uid$ptr() {
            return segment().asSlice(28L);
        }
        public final int st_uid$get() {
            return (int) st_uid$VH.get(st_uid$ptr());
        }
        public final void st_uid$set(int value) {
            st_uid$VH.set(st_uid$ptr(), value);
        }

        public static final VarHandle st_gid$VH = RuntimeHelper.varHandle(int.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("st_gid")));
        public static final long st_gid$OFFSET = 32L;
        public final MemorySegment st_gid$ptr() {
            return segment().asSlice(32L);
        }
        public final int st_gid$get() {
            return (int) st_gid$VH.get(st_gid$ptr());
        }
        public final void st_gid$set(int value) {
            st_gid$VH.set(st_gid$ptr(), value);
        }

        public static final VarHandle __pad0$VH = RuntimeHelper.varHandle(int.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("__pad0")));
        public static final long __pad0$OFFSET = 36L;
        public final MemorySegment __pad0$ptr() {
            return segment().asSlice(36L);
        }
        public final int __pad0$get() {
            return (int) __pad0$VH.get(__pad0$ptr());
        }
        public final void __pad0$set(int value) {
            __pad0$VH.set(__pad0$ptr(), value);
        }

        public static final VarHandle st_rdev$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("st_rdev")));
        public static final long st_rdev$OFFSET = 40L;
        public final MemorySegment st_rdev$ptr() {
            return segment().asSlice(40L);
        }
        public final long st_rdev$get() {
            return (long) st_rdev$VH.get(st_rdev$ptr());
        }
        public final void st_rdev$set(long value) {
            st_rdev$VH.set(st_rdev$ptr(), value);
        }

        public static final VarHandle st_size$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("st_size")));
        public static final long st_size$OFFSET = 48L;
        public final MemorySegment st_size$ptr() {
            return segment().asSlice(48L);
        }
        public final long st_size$get() {
            return (long) st_size$VH.get(st_size$ptr());
        }
        public final void st_size$set(long value) {
            st_size$VH.set(st_size$ptr(), value);
        }

        public static final VarHandle st_blksize$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("st_blksize")));
        public static final long st_blksize$OFFSET = 56L;
        public final MemorySegment st_blksize$ptr() {
            return segment().asSlice(56L);
        }
        public final long st_blksize$get() {
            return (long) st_blksize$VH.get(st_blksize$ptr());
        }
        public final void st_blksize$set(long value) {
            st_blksize$VH.set(st_blksize$ptr(), value);
        }

        public static final VarHandle st_blocks$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("st_blocks")));
        public static final long st_blocks$OFFSET = 64L;
        public final MemorySegment st_blocks$ptr() {
            return segment().asSlice(64L);
        }
        public final long st_blocks$get() {
            return (long) st_blocks$VH.get(st_blocks$ptr());
        }
        public final void st_blocks$set(long value) {
            st_blocks$VH.set(st_blocks$ptr(), value);
        }

        public static final long st_atim$OFFSET = 72L;
        public final MemorySegment st_atim$ptr() {
            return segment().asSlice(72L);
        }
        public final timespec st_atim$get() {
            return timespec.at(st_atim$ptr());
        }
        public final void st_atim$set(timespec value) {
            timespec.at(st_atim$ptr()).asSegment().copyFrom(value.asSegment());
        }

        public static final long st_mtim$OFFSET = 88L;
        public final MemorySegment st_mtim$ptr() {
            return segment().asSlice(88L);
        }
        public final timespec st_mtim$get() {
            return timespec.at(st_mtim$ptr());
        }
        public final void st_mtim$set(timespec value) {
            timespec.at(st_mtim$ptr()).asSegment().copyFrom(value.asSegment());
        }

        public static final long st_ctim$OFFSET = 104L;
        public final MemorySegment st_ctim$ptr() {
            return segment().asSlice(104L);
        }
        public final timespec st_ctim$get() {
            return timespec.at(st_ctim$ptr());
        }
        public final void st_ctim$set(timespec value) {
            timespec.at(st_ctim$ptr()).asSegment().copyFrom(value.asSegment());
        }

        public static final VarHandle __glibc_reserved$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("__glibc_reserved")));
        public static final long __glibc_reserved$OFFSET = 120L;
        public final MemorySegment __glibc_reserved$ptr() {
            return segment().asSlice(120L);
        }
        public final long __glibc_reserved$get(long idx0) {
            return (long) __glibc_reserved$VH.get(__glibc_reserved$ptr(), idx0);
        }
        public final void __glibc_reserved$set(long idx0, long value) {
            __glibc_reserved$VH.set(__glibc_reserved$ptr(), idx0, value);
        }
    }
    public static final class timespec extends Struct<timespec> {
        protected timespec(MemorySegment ms) { super(ms); }
        public static final timespec at(MemorySegment ms) { return new timespec(ms); }
        public static final timespec allocate(LongFunction<MemorySegment> allocator, int count) {
            return new timespec(allocator.apply(sizeof() * count));
        }
        public static final timespec allocate(LongFunction<MemorySegment> allocator) { return allocate(allocator, 1); }
        public final timespec offset(int count) { return at(segment().asSlice(sizeof() * count)); }

        public static final GroupLayout $LAYOUT = MemoryLayout.ofStruct(
            C_LONG.withName("tv_sec"),
            C_LONG.withName("tv_nsec")
        ).withName("timespec");
        public static final long sizeof() { return $LAYOUT.byteSize(); }
        public static final long offsetof(String fieldName) { return $LAYOUT.byteOffset(MemoryLayout.PathElement.groupElement(fieldName)); }
        @Override
        public final GroupLayout getLayout() { return $LAYOUT; }

        public static final VarHandle tv_sec$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("tv_sec")));
        public static final long tv_sec$OFFSET = 0L;
        public final MemorySegment tv_sec$ptr() {
            return segment().asSlice(0L);
        }
        public final long tv_sec$get() {
            return (long) tv_sec$VH.get(tv_sec$ptr());
        }
        public final void tv_sec$set(long value) {
            tv_sec$VH.set(tv_sec$ptr(), value);
        }

        public static final VarHandle tv_nsec$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("tv_nsec")));
        public static final long tv_nsec$OFFSET = 8L;
        public final MemorySegment tv_nsec$ptr() {
            return segment().asSlice(8L);
        }
        public final long tv_nsec$get() {
            return (long) tv_nsec$VH.get(tv_nsec$ptr());
        }
        public final void tv_nsec$set(long value) {
            tv_nsec$VH.set(tv_nsec$ptr(), value);
        }
    }
    public static final class dirent extends Struct<dirent> {
        protected dirent(MemorySegment ms) { super(ms); }
        public static final dirent at(MemorySegment ms) { return new dirent(ms); }
        public static final dirent allocate(LongFunction<MemorySegment> allocator, int count) {
            return new dirent(allocator.apply(sizeof() * count));
        }
        public static final dirent allocate(LongFunction<MemorySegment> allocator) { return allocate(allocator, 1); }
        public final dirent offset(int count) { return at(segment().asSlice(sizeof() * count)); }

        public static final GroupLayout $LAYOUT = MemoryLayout.ofStruct(
            C_LONG.withName("d_ino"),
            C_LONG.withName("d_off"),
            C_SHORT.withName("d_reclen"),
            C_BOOL.withName("d_type"),
            MemoryLayout.ofSequence(256, C_BOOL).withName("d_name"),
            MemoryLayout.ofPaddingBits(40)
        ).withName("dirent");
        public static final long sizeof() { return $LAYOUT.byteSize(); }
        public static final long offsetof(String fieldName) { return $LAYOUT.byteOffset(MemoryLayout.PathElement.groupElement(fieldName)); }
        @Override
        public final GroupLayout getLayout() { return $LAYOUT; }

        public static final VarHandle d_ino$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("d_ino")));
        public static final long d_ino$OFFSET = 0L;
        public final MemorySegment d_ino$ptr() {
            return segment().asSlice(0L);
        }
        public final long d_ino$get() {
            return (long) d_ino$VH.get(d_ino$ptr());
        }
        public final void d_ino$set(long value) {
            d_ino$VH.set(d_ino$ptr(), value);
        }

        public static final VarHandle d_off$VH = RuntimeHelper.varHandle(long.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("d_off")));
        public static final long d_off$OFFSET = 8L;
        public final MemorySegment d_off$ptr() {
            return segment().asSlice(8L);
        }
        public final long d_off$get() {
            return (long) d_off$VH.get(d_off$ptr());
        }
        public final void d_off$set(long value) {
            d_off$VH.set(d_off$ptr(), value);
        }

        public static final VarHandle d_reclen$VH = RuntimeHelper.varHandle(short.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("d_reclen")));
        public static final long d_reclen$OFFSET = 16L;
        public final MemorySegment d_reclen$ptr() {
            return segment().asSlice(16L);
        }
        public final short d_reclen$get() {
            return (short) d_reclen$VH.get(d_reclen$ptr());
        }
        public final void d_reclen$set(short value) {
            d_reclen$VH.set(d_reclen$ptr(), value);
        }

        public static final VarHandle d_type$VH = RuntimeHelper.varHandle(byte.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("d_type")));
        public static final long d_type$OFFSET = 18L;
        public final MemorySegment d_type$ptr() {
            return segment().asSlice(18L);
        }
        public final byte d_type$get() {
            return (byte) d_type$VH.get(d_type$ptr());
        }
        public final void d_type$set(byte value) {
            d_type$VH.set(d_type$ptr(), value);
        }

        public static final VarHandle d_name$VH = RuntimeHelper.varHandle(byte.class, $LAYOUT.select(MemoryLayout.PathElement.groupElement("d_name")));
        public static final long d_name$OFFSET = 19L;
        public final MemorySegment d_name$ptr() {
            return segment().asSlice(19L);
        }
        public final byte d_name$get(long idx0) {
            return (byte) d_name$VH.get(d_name$ptr(), idx0);
        }
        public final void d_name$set(long idx0, byte value) {
            d_name$VH.set(d_name$ptr(), idx0, value);
        }
    }
    public static final MethodHandle mh___xstat = RuntimeHelper.downcallHandle(
        LIBRARIES, "__xstat",
        "(ILjdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;)I",
        FunctionDescriptor.of(C_INT,
            C_INT,
            C_POINTER,
            C_POINTER
        ), false
    );
    public static final int __xstat(int __ver, jdk.incubator.foreign.Addressable __filename, jdk.incubator.foreign.Addressable __stat_buf) {
        try {
            return (int) mh___xstat.invokeExact(__ver, __filename.address(), __stat_buf.address());
        } catch (Throwable ex) {
            throw new AssertionError(ex);
        }
    }
    public static final MethodHandle mh_opendir = RuntimeHelper.downcallHandle(
        LIBRARIES, "opendir",
        "(Ljdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemoryAddress;",
        FunctionDescriptor.of(C_POINTER,
            C_POINTER
        ), false
    );
    public static final jdk.incubator.foreign.MemoryAddress opendir(jdk.incubator.foreign.Addressable __name) {
        try {
            return (jdk.incubator.foreign.MemoryAddress) mh_opendir.invokeExact(__name.address());
        } catch (Throwable ex) {
            throw new AssertionError(ex);
        }
    }
    public static final MethodHandle mh_closedir = RuntimeHelper.downcallHandle(
        LIBRARIES, "closedir",
        "(Ljdk/incubator/foreign/MemoryAddress;)I",
        FunctionDescriptor.of(C_INT,
            C_POINTER
        ), false
    );
    public static final int closedir(jdk.incubator.foreign.Addressable __dirp) {
        try {
            return (int) mh_closedir.invokeExact(__dirp.address());
        } catch (Throwable ex) {
            throw new AssertionError(ex);
        }
    }
    public static final MethodHandle mh_readdir = RuntimeHelper.downcallHandle(
        LIBRARIES, "readdir",
        "(Ljdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemoryAddress;",
        FunctionDescriptor.of(C_POINTER,
            C_POINTER
        ), false
    );
    public static final jdk.incubator.foreign.MemoryAddress readdir(jdk.incubator.foreign.Addressable __dirp) {
        try {
            return (jdk.incubator.foreign.MemoryAddress) mh_readdir.invokeExact(__dirp.address());
        } catch (Throwable ex) {
            throw new AssertionError(ex);
        }
    }
}

