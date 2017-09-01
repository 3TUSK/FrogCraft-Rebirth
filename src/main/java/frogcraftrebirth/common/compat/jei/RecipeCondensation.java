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
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeCondensation implements IRecipeWrapper {
	
	private final ICondenseTowerRecipe recipe;
	
	public RecipeCondensation(ICondenseTowerRecipe recipe) {
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(FluidStack.class, ImmutableList.of(recipe.getInput()));
		ingredients.setOutputs(FluidStack.class, ImmutableList.copyOf(recipe.getOutput()));
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRenderer.drawString(I18n.format("jei.euTotal", recipe.getTime() * recipe.getEnergyPerTick()), recipeWidth - 160, recipeHeight - 70, 0x404040);
		minecraft.fontRenderer.drawString(I18n.format("jei.euTick", recipe.getEnergyPerTick()), recipeWidth - 160, recipeHeight - 60, 0x404040);
		minecraft.fontRenderer.drawString(I18n.format("jei.tick", recipe.getTime()), recipeWidth - 160, recipeHeight - 50, 0x404040);
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
