/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:56:14 PM, Aug 31, 2015
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public interface IPollutionSource {

	// Debug info.
	String[] info();

	// Effect radius
	int radius();

	PotionEffect effects(EntityLiving entity);

	// Pollution. The origin of sin.
	void pollute(World world, Entity operator, int originX, int originY, int originZ);

}
