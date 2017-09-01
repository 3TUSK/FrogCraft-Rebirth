package frogcraftrebirth.common.compat.jei;

import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

public class RecipeWrapperFactoryChemReaction implements IRecipeWrapperFactory<IAdvChemRecRecipe> {
	@Override
	public IRecipeWrapper getRecipeWrapper(IAdvChemRecRecipe recipe) {
		return new RecipeChemReaction(recipe);
	}
}
