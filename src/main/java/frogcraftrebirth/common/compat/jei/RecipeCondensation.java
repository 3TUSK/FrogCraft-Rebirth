/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:05:57 PM, Jul 28, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeCondensation implements IRecipeWrapper {
	
	public static List<RecipeCondensation> getWrappedRecipeList() {
		List<RecipeCondensation> recipesToReturn = new ArrayList<RecipeCondensation>();
		for (ICondenseTowerRecipe recipe : FrogAPI.managerCT.getRecipes()) {
			recipesToReturn.add(new RecipeCondensation(recipe));
		}
		return recipesToReturn;
	}
	
	private final ICondenseTowerRecipe recipe;
	
	public RecipeCondensation(ICondenseTowerRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public List<ItemStack> getInputs() {
		return null;
	}

	@Override
	public List<ItemStack> getOutputs() {
		return null;
	}

	@Override
	public List<FluidStack> getFluidInputs() {
		return Arrays.asList(recipe.getInput());
	}

	@Override
	public List<FluidStack> getFluidOutputs() {
		return new ArrayList<FluidStack>(recipe.getOutput());
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRendererObj.drawString(I18n.format("jei.euTotal", recipe.getTime() * recipe.getEnergyPerTick()), recipeWidth - 160, recipeHeight - 70, 0x404040);
		minecraft.fontRendererObj.drawString(I18n.format("jei.euTick", recipe.getEnergyPerTick()), recipeWidth - 160, recipeHeight - 60, 0x404040);
		minecraft.fontRendererObj.drawString(I18n.format("jei.tick", recipe.getTime()), recipeWidth - 160, recipeHeight - 50, 0x404040);
	}

	@Override
	public void drawAnimations(Minecraft minecraft, int recipeWidth, int recipeHeight) {
		
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return null;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}

}
