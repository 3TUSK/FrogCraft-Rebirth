/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 6:39:57 PM, Dec 9, 2015, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.minetweaker;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.PyrolyzerRecipe;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.frogcraft.Pyrolyzer")
public class Pyrolyzer {
	
	@ZenMethod
	public static void addRecipe(IItemStack output, ILiquidStack outputFluid, IItemStack input, int time) {
		MineTweakerAPI.apply(new AddPyrolyzerRecipe(input, output, outputFluid, time));
	}
	
	public static class AddPyrolyzerRecipe implements IUndoableAction {
		
		final ItemStack input, output;
		final FluidStack outputFluid;
		final int time;
		
		public AddPyrolyzerRecipe(IItemStack input, IItemStack output, ILiquidStack fluid, int time) {
			this.input = MineTweakerMC.getItemStack(input);
			this.output = MineTweakerMC.getItemStack(output);
			this.outputFluid = MineTweakerMC.getLiquidStack(fluid);
			this.time = time;
		}

		@Override
		public void apply() {
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(input, output, outputFluid, time));
		}

		@Override
		public boolean canUndo() {
			return true;
		}

		@Override
		public String describe() {
			return "";
		}

		@Override
		public String describeUndo() {
			return "";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}

		@Override
		public void undo() {
			
		}
		
	}

}
