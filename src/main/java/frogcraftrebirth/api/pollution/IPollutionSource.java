/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:56:14 PM, Aug 31, 2015
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.pollution;

import java.util.Collection;

import net.minecraft.entity.Entity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Deprecated
public interface IPollutionSource {

	/**
	 * Acquire a radius for {@link IAntiPollutionObject} use
	 * @return The radius of affected area.
	 */
	int radius();

	/**
	 * Get potion effects based on entity type
	 * @param entity the target
	 * @return A collection of applicable {@link PotionEffect}
	 */
	Collection<PotionEffect> effects(Entity entity);

	/**
	 * Pollution. The origin of sin.
	 * @param world The world of contamination object being in
	 * @param pos The position of contamination object
	 */
	void pollute(World world, BlockPos pos);

}
