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

package frogcraftrebirth.common.lib;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.api.recipes.IRecipeManager;
import net.minecraftforge.fluids.FluidStack;

public class CondenseTowerRecipeManager implements IRecipeManager<ICondenseTowerRecipe> {

	@Override
	public void add(ICondenseTowerRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(ICondenseTowerRecipe recipe) {
		recipes.remove(recipe);
	}
	
	@Override
	public Collection<ICondenseTowerRecipe> getRecipes() {
		return recipes;
	}

	@Override
	public ICondenseTowerRecipe getRecipe(IFrogRecipeInput... inputs) {
		IFrogRecipeInput input = inputs[0];
		if (input == null)
			return null;
		List<FluidStack> inputFluid = input.getActualInputs(FluidStack.class);
		if (inputFluid.size() > 0) {
			FluidStack first = inputFluid.get(0);
			for (ICondenseTowerRecipe recipe : recipes) {
				if (recipe.getInput().isFluidEqual(first)) {
					return recipe;
				}
			}
		}

		return null;
	}

	@Override
	public ICondenseTowerRecipe getRecipe(Iterable<IFrogRecipeInput> inputs) {
		IFrogRecipeInput input = inputs.iterator().next();
		if (input == null)
			return null;
		List<FluidStack> inputFluid = input.getActualInputs(FluidStack.class);
		if (inputFluid.size() > 0) {
			FluidStack first = inputFluid.get(0);
			for (ICondenseTowerRecipe recipe : recipes) {
				if (recipe.getInput().isFluidEqual(first)) {
					return recipe;
				}
			}
		}

		return null;
	}

	private final Set<ICondenseTowerRecipe> recipes = new HashSet<>();

}
