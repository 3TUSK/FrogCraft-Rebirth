/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 11:23:21 AM, Jul 26, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.pollution;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPollutionVictim {
	
	/**
	 * A getter method for resistance of object (e.g. EntityPlayer) against contamination 
	 * @return The resistance value.
	 */
	int getResistance();
	
	/**
	 * Be sinful.
	 * @param worldIn The world of this victim is in
	 * @param pos The position of this victim
	 * @param entity The victim entity, if applicable
	 * @param stack The victim item, wrapped in an item stack, if applicable
	 */
	void victimize(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nullable Entity entity, @Nullable ItemStack stack);

}
