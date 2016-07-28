/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 8:31:28 PM, Jul 28, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.air;

import net.minecraft.util.EnumFacing;

public interface IAirConsumer {
	
	int inject(EnumFacing facing, int amount, boolean doInject);

}
