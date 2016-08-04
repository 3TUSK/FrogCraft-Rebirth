/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 4:27:29 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.item;

import java.util.Arrays;
import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemTiberium extends ItemFrogCraft {

	public ItemTiberium() {
		super(true);
		this.setCreativeTab(FrogAPI.TAB);
		this.setMaxStackSize(16);
		this.setUnlocalizedName("tiberium");
		this.setSubNameArray("red", "blue", "green");
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		return Arrays.asList("CAUTION: NOT FULLY IMPLEMENTED YET");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+"."+nameArray[stack.getMetadata()];
	}

}
