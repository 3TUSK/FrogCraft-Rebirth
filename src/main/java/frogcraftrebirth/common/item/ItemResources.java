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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

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
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int damage) {
		return iconArray[damage];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister reg) {
		for (int n=0;n<nameArray.length;n++) {
			iconArray[n] = reg.registerIcon(TEXTURE_MAIN + mainTextureDomain + "/" + nameArray[n]);
		}
	}
	
	public ItemResources initNamespace(String... nameArray) {
		this.setSubNameArray(nameArray);
		this.iconArray = new IIcon[nameArray.length];
		return this;
	}

}
