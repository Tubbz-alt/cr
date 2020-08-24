/*
 *  Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *  This code is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License version 2 only, as
 *  published by the Free Software Foundation.  Oracle designates this
 *  particular file as subject to the "Classpath" exception as provided
 *  by Oracle in the LICENSE file that accompanied this code.
 *
 *  This code is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *  FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 *  version 2 for more details (a copy is included in the LICENSE file that
 *  accompanied this code).
 *
 *  You should have received a copy of the GNU General Public License version
 *  2 along with this work; if not, write to the Free Software Foundation,
 *  Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 *   Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 *  or visit www.oracle.com if you need additional information or have any
 *  questions.
 *
 */
package jdk.internal.clang;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;
import static jdk.internal.clang.libclang.Index_h.CXError_ASTReadError;
import static jdk.internal.clang.libclang.Index_h.CXError_Crashed;
import static jdk.internal.clang.libclang.Index_h.CXError_Failure;
import static jdk.internal.clang.libclang.Index_h.CXError_InvalidArguments;
import static jdk.internal.clang.libclang.Index_h.CXError_Success;

public enum ErrorCode {
    Success(CXError_Success()),
    Failue(CXError_Failure()),
    Crashed(CXError_Crashed()),
    InvalidArguments(CXError_InvalidArguments()),
    ASTReadError(CXError_ASTReadError());

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    private static final Map<Integer, ErrorCode> lookup = Arrays.stream(values())
            .collect(toMap(ErrorCode::code, Function.identity()));

    public static ErrorCode valueOf(int code) {
        return lookup.computeIfAbsent(code, k -> { throw new NoSuchElementException("No ErrorCode with code: " + k); });
    }
}
