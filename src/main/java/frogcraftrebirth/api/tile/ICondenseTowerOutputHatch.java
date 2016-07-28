/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 12:40:43 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.tile;

import net.minecraftforge.fluids.FluidStack;

public interface ICondenseTowerOutputHatch extends ICondenseTowerPart {

	boolean canInject(FluidStack stack);

	void inject(FluidStack stack, boolean simluated);
	
	@Override
	default boolean isFunctional() {
		return true;
	}

}
