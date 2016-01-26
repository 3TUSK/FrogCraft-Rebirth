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
import net.minecraftforge.fluids.IFluidContainerItem;

public interface IFluidBackpackSpigot {
	
	//NOT IMPLEMENTED YET, MAY CHANGE IN FUTURE
	//Called when a launch action was executed, return the fluid being launched.
	FluidStack extractFrom(IFluidContainerItem backpack);

}
