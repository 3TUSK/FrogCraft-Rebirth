/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
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
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FrogRecipeInputOreDict implements IFrogRecipeInput {

	private final String entry;
	private final int amount;

	public FrogRecipeInputOreDict(String entry, int size) {
		this.entry = entry;
		this.amount = size;
	}

	public boolean matches(IFrogRecipeInput input) {
		return OreDictionary.getOres(entry).stream().anyMatch(input::matches);
	}

	@Override
	public boolean matches(Object actualInput) {
		return actualInput instanceof ItemStack && OreDictionary.getOres(entry).stream().anyMatch(test -> test.isItemEqual((ItemStack)actualInput));
	}

	@Nonnull
	@Override
	public <T> List<T> getActualInputs(Class<T> type) {
		if (type == ItemStack.class) {
			return OreDictionary.getOres(this.entry)
					.stream()
					.map(ItemStack::copy)
					.peek(stack -> stack.setCount(this.amount))
					.map(type::cast)
					.collect(Collectors.toList());
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public int getSize() {
		return this.amount;
	}

	@Nullable
	@Override
	public <I> I accepts(Class<I> type, I actualInput) {
		if (type == ItemStack.class) {
			ItemStack casted = (ItemStack)actualInput;
			casted.shrink(amount);
			return actualInput;
		} else {
			return actualInput;
		}
	}

	@Override
	public boolean equals(Object o) {
		return this == o || o instanceof FrogRecipeInputOreDict && ((FrogRecipeInputOreDict)o).entry.equals(this.entry);
	}
}
