package frogcraftrebirth.common.compat.jei;

import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

public class RecipeWrapperFactoryCondensation implements IRecipeWrapperFactory<ICondenseTowerRecipe> {
	@Override
	public IRecipeWrapper getRecipeWrapper(ICondenseTowerRecipe recipe) {
		return new RecipeCondensation(recipe);
	}
}
