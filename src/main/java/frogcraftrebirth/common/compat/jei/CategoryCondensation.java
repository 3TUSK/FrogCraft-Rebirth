/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:17:37 PM, Jul 28, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class CategoryCondensation implements IRecipeCategory<RecipeCondensation> {

	protected final IDrawable background;
	protected final IDrawableAnimated progressBar;
	
	public CategoryCondensation(IGuiHelper helper) {
		ResourceLocation backgroundTexture = new ResourceLocation("frogcraftrebirth", "textures/gui/GUI_CondenseTower.png");
		background = helper.createDrawable(backgroundTexture, 0, 30, 175, 100);
		IDrawableStatic progressBarBackground = helper.createDrawable(backgroundTexture, 176, 0, 24, 16);
		progressBar = helper.createAnimatedDrawable(progressBarBackground, 100, StartDirection.LEFT, false);
	}
	
	@Override
	public String getUid() {
		return "frogcraftrebirth.condensation";
	}

	@Override
	public String getTitle() {
		return I18n.format("jei.category.condensation");
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		
	}

	@Override
	public void drawAnimations(Minecraft minecraft) {
		progressBar.draw(minecraft, 40, 4);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RecipeCondensation recipeWrapper) {
		IGuiFluidStackGroup fluidStacks = recipeLayout.getFluidStacks();
		fluidStacks.init(0, true, 14, 4, 16, 16, recipeWrapper.getFluidInputs().get(0).amount, false, null);
		fluidStacks.set(0, recipeWrapper.getFluidInputs());
		final int size = recipeWrapper.getFluidOutputs().size();
		for (int index = 0; index < size; index++) {
			fluidStacks.init(index + 1, true, (18 * index) + 76, 4, 16, 16, recipeWrapper.getFluidOutputs().get(index).amount, false, null);
			fluidStacks.set(index + 1, recipeWrapper.getFluidOutputs().get(index));
		}
	}
	
}
