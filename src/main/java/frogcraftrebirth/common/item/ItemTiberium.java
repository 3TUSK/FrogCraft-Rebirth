/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 4:27:29 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.item;

import java.util.Collections;
import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		return Collections.singletonList(I18n.format("item.tiberium.info"));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+"."+nameArray[stack.getMetadata()];
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.rand.nextInt(100) == 0 && entity instanceof EntityPlayerMP) {
			ItemStack[] inv = ((EntityPlayerMP)entity).inventory.mainInventory;
			for (int index = 0; index < 36; index++) {
				if (world.rand.nextInt(100) == 0) {
					ItemStack target = inv[index];
					if (target != null && !(target.getItem() instanceof ItemTiberium)) {
						inv[index] = new ItemStack(this, target.stackSize, stack.getMetadata());
						entity.addChatMessage(new TextComponentTranslation("chat.tiberiumCorrosion"));
					}
				}
			}
			
		}
	}

}
