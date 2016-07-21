/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 7:42:11 PM, Jan 4, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.item;

import net.minecraftforge.fluids.FluidStack;
import net.minecraft.item.ItemStack;

public interface IFluidBackpackSpigot {
	
	/**Called when a launch action was executed.
	 * @param backpack The backpack item. Use capability system to access fluid tank.
	 * @return The FluidStack being launched.
	 */
	FluidStack extractFrom(ItemStack backpack);

}
