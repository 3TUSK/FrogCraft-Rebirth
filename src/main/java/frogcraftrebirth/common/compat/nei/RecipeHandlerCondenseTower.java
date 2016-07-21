/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 9:18:04 PM, Apr 10, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.nei;

import java.util.List;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
//import net.minecraftforge.fluids.FluidStack;

public class RecipeHandlerCondenseTower extends TemplateRecipeHandler {

	@Override
	public String getRecipeName() {
		return "FrogCraft Condensation";
	}

	@Override
	public String getGuiTexture() {
		return "";
	}
	
	@Override
	public int recipiesPerPage() {
		return 1;
	}
	
	public class CachedCondenseTowerRecipe extends CachedRecipe {
		
		/*private final FluidStack input;
		private final List<FluidStack> outputs;
		
		public CachedCondenseTowerRecipe(FluidStack input, List<FluidStack> outputs) {
			this.input = input;
			this.outputs = outputs;
		}*/
		
		@Override
		public PositionedStack getIngredient() {
			return null;
		}

		@Override
		public PositionedStack getResult() {
			return null;
		}
		
		@Override
		public List<PositionedStack> getOtherStacks() {
			return null;
		}
		
	}

}
