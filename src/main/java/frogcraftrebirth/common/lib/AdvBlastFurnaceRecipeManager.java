/*
 * Copyright (c) 2015 - 2019 3TUSK, et al.
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

import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.api.recipes.IRecipeManager;
import org.apache.commons.lang3.Validate;

import javax.annotation.Nullable;
import java.util.*;

public class AdvBlastFurnaceRecipeManager implements IRecipeManager<IAdvBlastFurnaceRecipe> {

	private final Set<IAdvBlastFurnaceRecipe> recipes = new HashSet<>(8);

	@Override
	public void add(IAdvBlastFurnaceRecipe recipe) {
		Validate.isTrue(!recipe.getInput().isEmpty());
		Validate.isTrue(!recipe.getOutput().isEmpty());
		this.recipes.add(recipe);
	}

	@Override
	public void remove(IAdvBlastFurnaceRecipe recipe) {
		this.recipes.remove(recipe);
	}

	@Override
	public Collection<IAdvBlastFurnaceRecipe> getRecipes() {
		return Collections.unmodifiableSet(recipes);
	}

	@Nullable
	@Override
	public IAdvBlastFurnaceRecipe getRecipe(IFrogRecipeInput... input) {
		for (IAdvBlastFurnaceRecipe recipe : recipes) {
			if (recipe.getInput().matches(input[0]) && (recipe.getInputSecondary().isEmpty() || recipe.getInputSecondary().matches(input[1]))) {
				return recipe;
			}
		}
		return null;
	}

	@Nullable
	@Override
	public IAdvBlastFurnaceRecipe getRecipe(Iterable<IFrogRecipeInput> input) {
		Iterator<IFrogRecipeInput> itr = input.iterator();
		IFrogRecipeInput primary = itr.next();
		IFrogRecipeInput secondary = itr.next();
		for (IAdvBlastFurnaceRecipe recipe : recipes) {
			if (recipe.getInput().matches(primary) && (recipe.getInputSecondary().isEmpty() || recipe.getInputSecondary().matches(secondary))) {
				return recipe;
			}
		}
		return null;
	}
}
