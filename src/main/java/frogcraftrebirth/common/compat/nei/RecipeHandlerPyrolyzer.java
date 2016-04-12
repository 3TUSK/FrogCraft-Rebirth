/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 6:42:33 PM, Apr 11, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import frogcraftrebirth.client.gui.GuiPyrolyzer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

public class RecipeHandlerPyrolyzer extends TemplateRecipeHandler {

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiPyrolyzer.class;
	}

	@Override
	public String getRecipeName() {
		return "FrogCraft Pyrolyzer";
	}

	@Override
	public String getGuiTexture() {
		return "";
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result) {
		
	}
	
	public class CachedPyrolyzerRecipe extends CachedRecipe {
		
		private final ItemStack input, output;
		
		public CachedPyrolyzerRecipe(IPyrolyzerRecipe recipe) {
			this.input = recipe.getInput();
			this.output = recipe.getOutput();
		}
		
		@Override
		public PositionedStack getIngredient() { //stack, x, y
			return new PositionedStack(this.input, 0, 0);
		}

		@Override
		public PositionedStack getResult() { //stack, x, y
			return new PositionedStack(this.output, 0, 0);
		}
		
	}

}
