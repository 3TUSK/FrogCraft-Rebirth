package frogcraftrebirth.common.lib;

import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.api.recipes.IRecipeManager;

import javax.annotation.Nullable;
import java.util.*;

public class AdvBlastFurnaceRecipeManager implements IRecipeManager<IAdvBlastFurnaceRecipe> {

	private final Set<IAdvBlastFurnaceRecipe> recipes = new HashSet<>(8);

	@Override
	public void add(IAdvBlastFurnaceRecipe recipe) {
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
