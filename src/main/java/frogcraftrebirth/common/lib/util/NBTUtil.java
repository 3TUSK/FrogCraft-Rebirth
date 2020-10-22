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

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public final class NBTUtil {

	private NBTUtil() {}

	public static int getIntegerOrDefault(NBTTagCompound tag, String key, int defaultValue) {
		if (tag != null && tag.hasKey(key, Constants.NBT.TAG_INT)) {
			return tag.getInteger(key);
		} else {
			return defaultValue;
		}
	}

	public static double getDoubleOrDefault(NBTTagCompound tag, String key, double defaultValue) {
		if (tag != null && tag.hasKey(key, Constants.NBT.TAG_DOUBLE)) {
			return tag.getDouble(key);
		} else {
			return defaultValue;
		}
	}
}
