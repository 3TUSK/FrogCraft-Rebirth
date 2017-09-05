/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:17:37 PM, Jul 28, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import javax.annotation.Nullable;

import frogcraftrebirth.api.FrogAPI;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class CategoryCondensation implements IRecipeCategory<RecipeCondensation> {

	protected final IDrawable background;
	protected final IDrawableAnimated progressBar;
	
	public CategoryCondensation(IGuiHelper helper) {
		ResourceLocation backgroundTexture = new ResourceLocation("frogcraftrebirth", "textures/gui/gui_condense_tower.png");
		background = helper.createDrawable(backgroundTexture, 5, 30, 165, 100, 0,0,5,10);
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
	public String getModName() {
		return FrogAPI.NAME;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	@Nullable
	public IDrawable getIcon() {
		return null; //Delegate to JEI
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		progressBar.draw(minecraft, 40, 4);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RecipeCondensation recipeWrapper, IIngredients ingredients) {
		IGuiFluidStackGroup fluidStacks = recipeLayout.getFluidStacks();
		fluidStacks.init(0, true, 14, 4, 16, 16, ingredients.getInputs(FluidStack.class).get(0).get(0).amount, false, null);
		fluidStacks.set(0, ingredients.getInputs(FluidStack.class).get(0));
		final int size = ingredients.getOutputs(FluidStack.class).size();
		for (int index = 0; index < size; index++) {
			fluidStacks.init(index + 1, true, (18 * index) + 76, 4, 16, 16, ingredients.getOutputs(FluidStack.class).get(index).get(0).amount, false, null);
			fluidStacks.set(index + 1, ingredients.getOutputs(FluidStack.class).get(index));
		}
	}

}
