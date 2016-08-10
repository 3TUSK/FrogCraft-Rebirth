/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 5:23:31 AM, Aug 11, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.item;

import java.util.Arrays;
import java.util.List;

import frogcraftrebirth.api.item.IFluidBackpackSpigot;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class ItemFluidArmorSpigot extends ItemFrogCraft implements IFluidBackpackSpigot {

	public ItemFluidArmorSpigot() {
		super(false);
	}

	@Override
	public FluidStack extractFrom(ItemStack backpack) {
		return null;
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		return Arrays.asList("");
	}

}
