/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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

import java.util.List;

import javax.annotation.Nullable;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

class CategoryChemReaction implements IRecipeCategory<RecipeChemReaction> {
	
	private final IDrawable background;
	private final IDrawableAnimated progressBar;
	private final IDrawableAnimated chargeBar;
	
	CategoryChemReaction(IGuiHelper helper) {
		ResourceLocation backgroundTexture = new ResourceLocation("frogcraftrebirth", "textures/gui/gui_adv_chem_reactor.png");
		background = helper.createDrawable(backgroundTexture, 5, 5, 165, 70, 23, 88, 5, 10);
		IDrawableStatic progressBarBackground = helper.createDrawable(backgroundTexture, 176, 0, 30, 10);
		progressBar = helper.createAnimatedDrawable(progressBarBackground, 100, StartDirection.TOP, false);
		IDrawableStatic chargeBarBackground = helper.createDrawable(backgroundTexture, 176, 17, 14, 14);
		chargeBar = helper.createAnimatedDrawable(chargeBarBackground, 30, StartDirection.BOTTOM, false);
	}

	@Override
	public String getUid() {
		return "frogcraftrebirth.chemreaction";
	}

	@Override
	public String getTitle() {
		return I18n.format("jei.category.chemreaction");
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
		progressBar.draw(minecraft, 73, 58);
		chargeBar.draw(minecraft, 148, 41);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RecipeChemReaction recipeWrapper, IIngredients ingredients) {
		recipeWrapper.getIngredients(ingredients);
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		final int
		cellInput = recipeWrapper.recipe.getRequiredCellAmount(),
		cellOutput = recipeWrapper.recipe.getProducedCellAmount();
		stacks.init(0, true, 150, 69);
		stacks.set(0, recipeWrapper.recipe.getCatalyst()); //Dirty. Real McCoy. The same below.
		if (cellInput > 0) {
			stacks.init(11, true, 11, 39);
			ItemStack cells = ic2.api.item.IC2Items.getItem("fluid_cell");
			cells.setCount(cellInput);
			stacks.set(11, cells);
		}
		if (cellOutput > 0) {
			stacks.init(12, false, 11, 69);
			ItemStack cells = ic2.api.item.IC2Items.getItem("fluid_cell");
			cells.setCount(cellOutput);
			stacks.set(12, cells);
		}
		int index = 0;
		for (IFrogRecipeInput input : recipeWrapper.recipe.getInputs()) {
			stacks.init(index, true, 39 + index * 20, 39);
			stacks.set(index++, input.getActualInputs(ItemStack.class));
		}
		index = 6;
		List<List<ItemStack>> outputs = ingredients.getOutputs(ItemStack.class);
		final int outputSize = outputs.size();
		for (int i = 0; i < outputSize; i++){
			stacks.init(index, false, 39 + i * 20, 69);
			stacks.set(index++, outputs.get(i));
		}
		stacks.init(5, true, 146, 69);
		stacks.set(5, recipeWrapper.recipe.getCatalyst());
	}

}
