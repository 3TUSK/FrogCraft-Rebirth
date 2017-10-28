/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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

public class FrogMath {
	
	/**
	 * This has been annoying me for a very long time. Now it's time to end it.
	 * @param base base of the logarithm
	 * @param exp exponential 
	 * @return log<sub>base</sub>(exp)
	 */
	public static double logBase(double base, double exp) {
		return Math.log(exp)/Math.log(base);
	}
	
	public static double cot(double radians) {
		return 1/Math.tan(radians);
	}
	
	public static double sec(double radians) {
		return 1/Math.cos(radians);
	}
	
	public static double csc(double radians) {
		return 1/Math.sin(radians);
	}
	
	public static String toFancyString(int value) {
		try {
			return (String) Class.forName("ic2.core.util.Util").getDeclaredMethod("toSiString", double.class, int.class).invoke(null, value, 2);
		} catch (Exception e) {
			return Integer.toString(value);
		}
	}

}
