/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 4:27:29 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.item;

import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemTiberium extends ItemFrogCraft {

	public ItemTiberium() {
		super(true);
		this.setCreativeTab(FrogAPI.TAB);
		this.setMaxStackSize(16);
		this.setUnlocalizedName("tiberium");
		this.setSubNameArray("red", "blue", "green");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format("item.tiberium.info"));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+"."+nameArray[stack.getMetadata()];
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.rand.nextInt(100) == 0 && entity instanceof EntityPlayerMP) {
			List<ItemStack> inv = ((EntityPlayerMP)entity).inventory.mainInventory;
			for (ItemStack item : inv) {
				if (world.rand.nextInt(100) == 0) {
					if (!item.isEmpty() && !(item.getItem() instanceof ItemTiberium)) {
						//TODO: Index-based loop?
						inv.set(inv.indexOf(item), new ItemStack(this, item.getCount(), item.getMetadata()));
						entity.sendMessage(new TextComponentTranslation("chat.tiberiumCorrosion"));
					}
				}
			}
		}
	}

}
