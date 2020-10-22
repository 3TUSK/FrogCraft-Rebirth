/*
 * Copyright (c) 2015 - 2020 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.lib.util;

import frogcraftrebirth.api.FrogAPI;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public final class MathUtil {
	
	public static String toFancyString(int value) {
		if (toSiString != null) {
			try {
				return toSiString.invoke( value, 2).toString();
			} catch (Throwable t) { // MethodHandle::invoke throws Throwable.
				return Integer.toString(value); // You know it doesn't work when you see all digits.
			}
		} else {
			return Integer.toString(value);
		}
	}

	static {
		MethodHandle toSiStringImpl = null;
		try {
			toSiStringImpl = MethodHandles.lookup().findStatic(ic2.core.util.Util.class,"toSiString", MethodType.methodType(String.class, double.class, int.class));
		} catch (Exception e) {
			FrogAPI.FROG_LOG.info("Notify author if you see this message. This message means that author of FrogCraft: Rebirth must update his scientific notation converter.");
		}
		toSiString = toSiStringImpl;
	}

	private static final MethodHandle toSiString;
}
