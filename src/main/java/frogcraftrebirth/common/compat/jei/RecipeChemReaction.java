/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:32:11 PM, Aug 13, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputs;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class RecipeChemReaction implements IRecipeWrapper {
	
	final IAdvChemRecRecipe recipe;
	
	public RecipeChemReaction(IAdvChemRecRecipe recipe) {
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, ImmutableList.copyOf(recipe.getInputs().stream().map(FrogRecipeInputs.MAP_TO_ITEM).collect(Collectors.toList())));
		ingredients.setOutputs(ItemStack.class, ImmutableList.copyOf(recipe.getOutputs()));
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		
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
