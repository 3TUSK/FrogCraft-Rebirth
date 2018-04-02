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
import java.util.Iterator;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import frogcraftrebirth.api.recipes.IRecipeManager;

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
		if (input == null || input.isEmpty())
			return null;

		for (IPyrolyzerRecipe r : recipes) {
			if (r.getActualInput().matches(input) && input.getSize() >= r.getActualInput().getSize()) {
				return r;
			}
		}
		return null;
	}

	public IPyrolyzerRecipe getRecipe(Iterable<IFrogRecipeInput> inputs) {
		Iterator<IFrogRecipeInput> iterator = inputs.iterator();

		if (!iterator.hasNext()) {
			return null;
		}

		IFrogRecipeInput input = iterator.next();
		if (input == null || input.isEmpty())
			return null;

		for (IPyrolyzerRecipe r : recipes) {
			if (r.getActualInput().matches(input) && input.getSize() >= r.getActualInput().getSize()) {
				return r;
			}
		}

		return null;
	}

	private final ArrayList<IPyrolyzerRecipe> recipes = new ArrayList<>();

}
