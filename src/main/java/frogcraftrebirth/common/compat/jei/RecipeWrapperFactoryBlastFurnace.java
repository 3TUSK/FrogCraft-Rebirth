package frogcraftrebirth.common.compat.jei;

import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

public class RecipeWrapperFactoryBlastFurnace implements IRecipeWrapperFactory<IAdvBlastFurnaceRecipe> {
	@Override
	public IRecipeWrapper getRecipeWrapper(IAdvBlastFurnaceRecipe recipe) {
		return new RecipeBlastFurnace(recipe);
	}
}
