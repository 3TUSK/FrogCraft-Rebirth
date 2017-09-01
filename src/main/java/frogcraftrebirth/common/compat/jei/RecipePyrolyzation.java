/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 9:42:53 PM, Jul 23, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipePyrolyzation implements IRecipeWrapper {
	
	private final IPyrolyzerRecipe recipe;
	
	public RecipePyrolyzation(@Nonnull IPyrolyzerRecipe recipe) {
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(ItemStack.class, recipe.getInput());
		ingredients.setOutput(ItemStack.class, recipe.getOutput());
		ingredients.setOutput(FluidStack.class, recipe.getOutputFluid());
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRenderer.drawString(I18n.format("jei.euTotal", recipe.getTime() * recipe.getEnergyPerTick()), recipeWidth - 160, recipeHeight - 80, 0x404040);
		minecraft.fontRenderer.drawString(I18n.format("jei.euTick", recipe.getEnergyPerTick()), recipeWidth - 160, recipeHeight - 70, 0x404040);
		minecraft.fontRenderer.drawString(I18n.format("jei.tick", recipe.getTime()), recipeWidth - 160, recipeHeight - 60, 0x404040);
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
