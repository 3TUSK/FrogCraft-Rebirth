/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 11:33:49 AM, Jul 26, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.pollution;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IAntiPollutionObject {
	
	/**
	 * Restrain the contamination in certain area.
	 * @param world 
	 * @param pos 
	 * @param radius Affected area
	 * @return
	 */
	boolean restrain(World world, BlockPos pos, int radius);

}
