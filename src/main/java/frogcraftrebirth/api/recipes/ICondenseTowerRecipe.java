/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 11:43:20 PM, Apr 9, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.recipes;

import java.util.Set;

import net.minecraftforge.fluids.FluidStack;

public interface ICondenseTowerRecipe {

	FluidStack getInput();

	Set<FluidStack> getOutput();

	int getTime();

	ICondenseTowerRecipe appendOutput(FluidStack... fluid);

}
