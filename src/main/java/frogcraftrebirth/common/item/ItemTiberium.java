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
import java.util.Random;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.block.BlockTiberium;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemTiberium extends ItemFrogCraft {
	
	public static final DamageSource TIBERIUM = new DamageSource("tiberium")
			.setDamageBypassesArmor()
			.setDamageIsAbsolute()
			.setDifficultyScaled()
			.setFireDamage();

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
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean isHolding) {
		Random rand = world.rand;
		if (rand.nextInt(10) == 1) {
			player.attackEntityFrom(TIBERIUM, 5F);
		}
		
		if (rand.nextInt(4) == 0 && player instanceof EntityPlayer) {
			int check = rand.nextInt(100);
			int type = rand.nextInt(3);
			if (check < 20) {
				int index = rand.nextInt(((EntityPlayer)player).inventory.mainInventory.length);
				int amount = ((EntityPlayer)player).inventory.mainInventory[index].stackSize;
				((EntityPlayer)player).inventory.setInventorySlotContents(index, new ItemStack(this, amount, type));
			}
		}
		
		if (rand.nextInt(20) < 3) {
			int type = rand.nextInt(3);
			if (rand.nextBoolean())
				world.setBlockState(player.getPosition(), BlockTiberium.getTiberiumWithType(type), 0b11);
		}
	}

}
