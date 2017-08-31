/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:02:42 PM, Jul 23, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HandlerPyrolyzation implements IRecipeHandler<RecipePyrolyzation> {

	@Nonnull
	@Override
	public Class<RecipePyrolyzation> getRecipeClass() {
		return RecipePyrolyzation.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid(RecipePyrolyzation recipe) {
		return "frogcraftrebirth.pyrolyzation";
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(RecipePyrolyzation recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(RecipePyrolyzation recipe) {
		return true;
	}

}
