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

package frogcraftrebirth.common.lib.recipes;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class FrogRecipeInputEmpty implements IFrogRecipeInput {

	// New instance is not permitted.
	FrogRecipeInputEmpty() {}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public boolean matches(IFrogRecipeInput input) {
		return input == FrogRecipeInputs.EMPTY;
	}

	@Override
	public boolean matches(Object actualInput) {
		return actualInput == null || actualInput == ItemStack.EMPTY;
	}

	@Nonnull
	@Override
	public <T> List<T> getActualInputs(Class<T> type) {
		return Collections.emptyList();
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Nullable
	@Override
	public <I> I accepts(Class<I> type, I actualInput) {
		return actualInput;
	}
}
