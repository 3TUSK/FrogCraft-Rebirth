/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:38:36 PM, Aug 13, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class CategoryChemReaction implements IRecipeCategory<RecipeChemReaction> {
	
	public CategoryChemReaction(IGuiHelper helper) {
		
	}

	@Override
	public String getUid() {
		return "frogcraftrebirth.chemreaction";
	}

	@Override
	public String getTitle() {
		return I18n.format("jei.category.chemReaction");
	}

	@Override
	public IDrawable getBackground() {
		return null;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		
	}

	@Override
	public void drawAnimations(Minecraft minecraft) {
		
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RecipeChemReaction recipeWrapper) {
		
	}

}
