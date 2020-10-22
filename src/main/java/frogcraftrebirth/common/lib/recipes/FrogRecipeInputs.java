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
import ic2.api.item.IC2Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public final class FrogRecipeInputs {

	private FrogRecipeInputs() {}

	public static final ItemStack UNI_CELL = IC2Items.getItem("fluid_cell");

	public static final Function<IFrogRecipeInput, List<ItemStack>> MAP_TO_ITEM = i -> i.getActualInputs(ItemStack.class);

	public static final IFrogRecipeInput EMPTY = new FrogRecipeInputEmpty();

	public static Collection<IFrogRecipeInput> wrap(ItemStack... stacks) {
		List<IFrogRecipeInput> list = new ArrayList<>(stacks.length);
		for (ItemStack stack : stacks) {
			if (UNI_CELL.isItemEqual(stack)) {
				try {
					list.add(new FrogRecipeInputUniversalFluidCell(stack));
				} catch (Exception e) {
					list.add(new FrogRecipeInputItemStack(stack));
				}
			} else {
				list.add(new FrogRecipeInputItemStack(stack));
			}
		}
		return list;
	}
}
