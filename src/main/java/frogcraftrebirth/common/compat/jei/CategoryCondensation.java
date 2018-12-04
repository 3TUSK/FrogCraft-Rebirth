/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

class CategoryCondensation implements IRecipeCategory<RecipeCondensation> {

	private final IDrawable background;
	private final IDrawableAnimated progressBar;
	
	public CategoryCondensation(IGuiHelper helper) {
		ResourceLocation backgroundTexture = new ResourceLocation("frogcraftrebirth", "textures/gui/gui_condense_tower.png");
		background = helper.drawableBuilder(backgroundTexture, 5, 30, 165, 100)
				.addPadding( 0,0,5,10)
				.build();
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
		fluidStacks.init(0, true, 14, 4, 16, 16, ingredients.getInputs(VanillaTypes.FLUID).get(0).get(0).amount, false, null);
		fluidStacks.set(0, ingredients.getInputs(VanillaTypes.FLUID).get(0));
		final int size = ingredients.getOutputs(VanillaTypes.FLUID).size();
		for (int index = 0; index < size; index++) {
			fluidStacks.init(index + 1, true, (18 * index) + 76, 4, 16, 16, ingredients.getOutputs(VanillaTypes.FLUID).get(index).get(0).amount, false, null);
			fluidStacks.set(index + 1, ingredients.getOutputs(VanillaTypes.FLUID).get(index));
		}
	}

}
