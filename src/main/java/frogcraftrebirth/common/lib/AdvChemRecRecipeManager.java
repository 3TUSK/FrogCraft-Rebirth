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

import java.util.ArrayList;
import java.util.Collection;

import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.api.recipes.IRecipeManager;
import org.apache.commons.lang3.Validate;

public class AdvChemRecRecipeManager implements IRecipeManager<IAdvChemRecRecipe> {

	@Override
	public void add(IAdvChemRecRecipe recipe) {
		Validate.notNull(recipe);
		Validate.isTrue(recipe.getEnergyRate() > 0);
		Validate.isTrue(recipe.getTime() > 0);
		recipes.add(recipe);
	}

	@Override
	public void remove(IAdvChemRecRecipe recipe) {
		recipes.remove(recipe);
	}
	
	@Override
	public Collection<IAdvChemRecRecipe> getRecipes() {
		return recipes;
	}

	@Override
	public IAdvChemRecRecipe getRecipe(IFrogRecipeInput... inputs) {
		for (IAdvChemRecRecipe recipe : recipes) {
			if (recipe.matchInputs(inputs)) {
				return recipe;
			}
		}
		return null;
	}

	@Override
	public IAdvChemRecRecipe getRecipe(Iterable<IFrogRecipeInput> inputs) {
		for (IAdvChemRecRecipe recipe : recipes) {
			if (recipe.matchInputs(inputs)) {
				return recipe;
			}
		}
		return null;
	}

	private final ArrayList<IAdvChemRecRecipe> recipes = new ArrayList<>();

}
