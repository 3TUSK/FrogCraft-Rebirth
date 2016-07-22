/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 11:46:39 PM, Apr 9, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IPyrolyzerRecipe {

	ItemStack getInput();

	ItemStack getOutput();

	FluidStack getOutputFluid();

	int getTime();

}
