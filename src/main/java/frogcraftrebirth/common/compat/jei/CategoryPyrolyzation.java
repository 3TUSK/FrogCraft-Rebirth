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
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

class CategoryPyrolyzation implements IRecipeCategory<RecipePyrolyzation> {

	private final IDrawable background;
	private final IDrawable tankOverlay;
	private final IDrawable fireOverlay;
	private final IDrawableAnimated electricBar;
	private final IDrawableAnimated progressBar;
	
	public CategoryPyrolyzation(IGuiHelper helper) {
		ResourceLocation backgroundTexture = new ResourceLocation("frogcraftrebirth", "textures/gui/GUI_Pyrolyzer.png");
		background = helper.drawableBuilder(backgroundTexture, 5, 5, 165, 70)
				.addPadding(23, 88, 5, 10)
				.build();
		tankOverlay = helper.createDrawable(backgroundTexture, 176, 0, 16, 47);
		fireOverlay = helper.createDrawable(backgroundTexture, 176, 66, 14, 14);
		IDrawableStatic progressBarOverlay = helper.createDrawable(backgroundTexture, 176, 80, 24, 17);
		progressBar = helper.createAnimatedDrawable(progressBarOverlay, 100, StartDirection.LEFT, false);
		IDrawableStatic electricBarOverlay = helper.createDrawable(backgroundTexture, 176, 52, 14, 14);
		electricBar = helper.createAnimatedDrawable(electricBarOverlay, 30, StartDirection.BOTTOM, false);
	}
	
	@Override
	public String getUid() {
		return "frogcraftrebirth.pyrolyzation";
	}

	@Override
	public String getTitle() {
		return I18n.format("jei.category.pyrolyzation");
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
		fireOverlay.draw(minecraft, 24, 68);
		progressBar.draw(minecraft, 45, 47);
		electricBar.draw(minecraft, 81, 75);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RecipePyrolyzation recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup items = recipeLayout.getItemStacks();
		IGuiFluidStackGroup fluids = recipeLayout.getFluidStacks();
		items.init(0, true, 23, 45);
		items.set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
		items.init(1, false, 74, 45);
		items.set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
		fluids.init(0, false, 143, 41, 16, 47, ingredients.getOutputs(VanillaTypes.FLUID).get(0).get(0).amount, false, tankOverlay);
		fluids.set(0, ingredients.getOutputs(VanillaTypes.FLUID).get(0));
	}

}
