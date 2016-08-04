/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 12:53:01 PM, Aug 2, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class CategoryPyrolyzation implements IRecipeCategory<RecipePyrolyzation> {

	protected final IDrawable background;
	protected final IDrawable tankOverlay;
	protected final IDrawable fireOverlay;
	protected final IDrawableAnimated electricBar;
	protected final IDrawableAnimated progressBar;
	
	public CategoryPyrolyzation(IGuiHelper helper) {
		ResourceLocation backgroundTexture = new ResourceLocation("frogcraftrebirth", "textures/gui/GUI_Pyrolyzer.png");
		background = helper.createDrawable(backgroundTexture, 0, 5, 175, 70, 23, 88, 0, 0);
		tankOverlay = helper.createDrawable(backgroundTexture, 176, 0, 16, 47);
		fireOverlay = helper.createDrawable(backgroundTexture, 176, 66, 14, 14);
		IDrawableStatic progressBarOverlay = helper.createDrawable(backgroundTexture, 176, 80, 24, 17);
		progressBar = helper.createAnimatedDrawable(progressBarOverlay, 100, StartDirection.LEFT, false);
		IDrawableStatic electricBarOverlay = helper.createDrawable(backgroundTexture, 176, 52, 14, 14);
		electricBar = helper.createAnimatedDrawable(electricBarOverlay, 60, StartDirection.BOTTOM, false);
	}
	
	@Override
	public String getUid() {
		return "frogcraftrebirth.pyrolyzation";
	}

	@Override
	public String getTitle() {
		return I18n.format("gui.jei.category.pyrolyzation");
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		fireOverlay.draw(minecraft, 24, 68);
	}

	@Override
	public void drawAnimations(Minecraft minecraft) {
		progressBar.draw(minecraft, 45, 47);
		electricBar.draw(minecraft, 81, 75);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RecipePyrolyzation recipeWrapper) {
		IGuiItemStackGroup items = recipeLayout.getItemStacks();
		IGuiFluidStackGroup fluids = recipeLayout.getFluidStacks();
		items.init(0, true, 23, 45);
		items.set(0, recipeWrapper.getInputs());
		items.init(1, false, 74, 45);
		items.set(1, recipeWrapper.getOutputs());
		fluids.init(0, false, 143, 41, 16, 47, recipeWrapper.getFluidOutputs().get(0).amount, false, tankOverlay);
		fluids.set(0, recipeWrapper.getFluidOutputs());
	}

}
