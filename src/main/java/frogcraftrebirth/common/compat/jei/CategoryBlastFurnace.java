/*
 * Copyright (c) 2015 - 2019 3TUSK, et al.
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

import frogcraftrebirth.api.FrogAPI;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

class CategoryBlastFurnace implements IRecipeCategory<RecipeBlastFurnace> {

	private final IDrawable background;
	private final IDrawableAnimated progressBar;
	private final IDrawableAnimated heatBar;

	CategoryBlastFurnace(IGuiHelper helper) {
		ResourceLocation backgroundTexture = new ResourceLocation(FrogAPI.MODID, "textures/gui/gui_adv_blast_furnace.png");
		background = helper.drawableBuilder(backgroundTexture, 5, 5, 165, 70).addPadding(23, 88, 5, 10).build();
		IDrawableStatic progressBarBackground = helper.createDrawable(backgroundTexture, 176, 59, 24, 18);
		progressBar = helper.createAnimatedDrawable(progressBarBackground, 100, IDrawableAnimated.StartDirection.LEFT, false);
		IDrawableStatic chargeBarBackground = helper.createDrawable(backgroundTexture, 176, 53, 24, 6);
		heatBar = helper.createAnimatedDrawable(chargeBarBackground, 30, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public String getUid() {
		return "frogcraftrebirth.blastfurnace";
	}

	@Override
	public String getTitle() {
		return I18n.format("jei.category.blastfurnace");
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
	public void drawExtras(Minecraft minecraft) {
		progressBar.draw(minecraft, 76, 45);
		heatBar.draw(minecraft, 76, 69);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RecipeBlastFurnace recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
		List<List<ItemStack>> listsInputs = ingredients.getInputs(VanillaTypes.ITEM);
		itemStackGroup.init(0, true, 32, 43);
		itemStackGroup.set(0, listsInputs.get(0));
		itemStackGroup.init(1, true, 50, 43);
		itemStackGroup.set(1, listsInputs.get(1));
		itemStackGroup.init(2, false, 108, 43);
		List<List<ItemStack>> listsOutputs = ingredients.getOutputs(VanillaTypes.ITEM);
		itemStackGroup.set(2, listsOutputs.get(0));
		itemStackGroup.init(3, false, 126, 43);
		itemStackGroup.set(3, listsOutputs.get(1));
		List<List<FluidStack>> listsInputsFluid = ingredients.getInputs(VanillaTypes.FLUID);
		IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
		fluidStackGroup.init(0, true, 8, 39, 16, 47, 8000, true, null);
		fluidStackGroup.set(0, listsInputsFluid.get(0).get(0));
		if (recipeWrapper.recipe.getShieldGas() != null) {
			fluidStackGroup.init(1, true, 152, 39, 16, 47, 1000, true, null);
			fluidStackGroup.set(1, listsInputsFluid.get(1).get(0));
		}
	}
}
