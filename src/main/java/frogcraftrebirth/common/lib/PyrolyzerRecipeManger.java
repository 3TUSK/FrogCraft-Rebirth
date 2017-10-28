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

package frogcraftrebirth.common.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import frogcraftrebirth.api.recipes.IRecipeManager;
import net.minecraft.item.ItemStack;

public class PyrolyzerRecipeManger implements IRecipeManager<IPyrolyzerRecipe>{

	@Override
	public void add(IPyrolyzerRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(IPyrolyzerRecipe recipe) {
		recipes.removeIf(recipe::equals);
	}
	
	@Override
	public Collection<IPyrolyzerRecipe> getRecipes() {
		return recipes;
	}

	@Override
	public IPyrolyzerRecipe getRecipe(IFrogRecipeInput... inputs) {
		if (inputs.length == 0)
			return null;
		IFrogRecipeInput input = inputs[0];
		if (input == null)
			return null;
		List<ItemStack> list = input.getActualInputs(ItemStack.class);
		if (list.size() > 0) {
			ItemStack first = list.get(0);
			if (first.isEmpty())
				return null;

			for (IPyrolyzerRecipe r : recipes) {
				if (r.getInput().isItemEqual(first) && first.getCount() > r.getInput().getCount()) {
					return r;
				}
			}
		}
		return null;
	}

	public IPyrolyzerRecipe getRecipe(Iterable<IFrogRecipeInput> inputs) {
		IFrogRecipeInput input = inputs.iterator().next();
		if (input == null)
			return null;
		List<ItemStack> list = input.getActualInputs(ItemStack.class);
		if (list.size() > 0) {
			ItemStack first = list.get(0);
			if (first.isEmpty())
				return null;

			for (IPyrolyzerRecipe r : recipes) {
				if (r.getInput().isItemEqual(first) && first.getCount() > r.getInput().getCount()) {
					return r;
				}
			}
		}
		return null;
	}

	private final ArrayList<IPyrolyzerRecipe> recipes = new ArrayList<>();

}
