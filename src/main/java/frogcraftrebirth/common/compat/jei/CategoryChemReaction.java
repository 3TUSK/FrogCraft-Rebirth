/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:38:36 PM, Aug 13, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import java.util.List;

import javax.annotation.Nullable;

import frogcraftrebirth.api.OreStack;
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

public class CategoryChemReaction implements IRecipeCategory<RecipeChemReaction> {
	
	protected final IDrawable background;
	protected final IDrawableAnimated progressBar;
	protected final IDrawableAnimated chargeBar;
	
	public CategoryChemReaction(IGuiHelper helper) {
		ResourceLocation backgroundTexture = new ResourceLocation("frogcraftrebirth", "textures/gui/GUI_AdvanceChemicalReactor.png");
		background = helper.createDrawable(backgroundTexture, 0, 5, 175, 70, 23, 88, 0, 0);
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
		return I18n.format("jei.category.chemReaction");
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

	@Deprecated //As deprecated in JEI
	@Override
	public void drawAnimations(Minecraft minecraft) {}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RecipeChemReaction recipeWrapper) {
		//No-op
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
			cells.stackSize = cellInput;
			stacks.set(11, cells);
		}
		if (cellOutput > 0) {
			stacks.init(12, false, 11, 69);
			ItemStack cells = ic2.api.item.IC2Items.getItem("fluid_cell");
			cells.stackSize = cellOutput;
			stacks.set(12, cells);
		}
		int index = 0;
		for (OreStack ore : recipeWrapper.recipe.getInputs()) {
			stacks.init(index, true, 39 + index * 20, 39);
			stacks.set(index++, ore.toStacks());
		}
		index = 6;
		List<ItemStack> outputs = ingredients.getOutputs(ItemStack.class);
		final int outputSize = outputs.size();
		for (int i = 0; i < outputSize; i++){
			stacks.init(index, false, 39 + i * 20, 69);
			stacks.set(index++, outputs.get(i));
		}
	}

}
