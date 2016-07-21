/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 9:57:13 AM, Jun 21, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemResources extends ItemFrogCraft {
	
	private final String mainTextureDomain;

	public ItemResources(boolean hasSubType, String textureMain) {
		super(hasSubType);
		this.mainTextureDomain = textureMain;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean f3PlusB) {
		List<String> tooltips = new ArrayList<String>();
		tooltips.add(I18n.format("item." + mainTextureDomain + "." + nameArray[stack.getItemDamage()] + ".info"));
		return new ArrayList<String>();
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+"."+nameArray[stack.getItemDamage()];
	}

}
