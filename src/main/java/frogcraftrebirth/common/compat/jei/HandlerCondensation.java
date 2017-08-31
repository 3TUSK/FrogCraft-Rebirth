/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:11:02 PM, Jul 28, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HandlerCondensation implements IRecipeHandler<RecipeCondensation> {

	@Override
	public Class<RecipeCondensation> getRecipeClass() {
		return RecipeCondensation.class;
	}

	@Override
	public String getRecipeCategoryUid(RecipeCondensation recipe) {
		return "frogcraftrebirth.condensation";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeCondensation recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(RecipeCondensation recipe) {
		return true;
	}

}
