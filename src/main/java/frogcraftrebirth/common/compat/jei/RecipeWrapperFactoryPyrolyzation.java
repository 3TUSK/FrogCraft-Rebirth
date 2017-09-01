package frogcraftrebirth.common.compat.jei;

import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

public class RecipeWrapperFactoryPyrolyzation implements IRecipeWrapperFactory<IPyrolyzerRecipe> {

	@Override
	public IRecipeWrapper getRecipeWrapper(IPyrolyzerRecipe recipe) {
		return new RecipePyrolyzation(recipe);
	}
}
