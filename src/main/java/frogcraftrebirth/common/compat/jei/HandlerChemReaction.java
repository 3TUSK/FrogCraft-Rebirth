/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:35:51 PM, Aug 13, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HandlerChemReaction implements IRecipeHandler<RecipeChemReaction> {

	@Override
	public Class<RecipeChemReaction> getRecipeClass() {
		return RecipeChemReaction.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return "frogcraftrebirth.chemreaction";
	}

	@Override
	public String getRecipeCategoryUid(RecipeChemReaction recipe) {
		return "frogcraftrebirth.chemreaction";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeChemReaction recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(RecipeChemReaction recipe) {
		return true;
	}

}
