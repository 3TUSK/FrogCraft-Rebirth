/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 8:19:48 PM, Jan 25, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.tile;

import net.minecraft.item.ItemStack;

public interface IInductionalMachine {

	void adjustHeat(float factor, boolean redstone);

	boolean canWork();

	void produce(ItemStack[] inputs);

	ItemStack getOutputFrom(ItemStack input);

}
