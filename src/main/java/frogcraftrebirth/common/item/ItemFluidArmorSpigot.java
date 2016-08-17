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
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class ItemFluidArmorSpigot extends ItemFrogCraft implements IFluidBackpackSpigot {

	public ItemFluidArmorSpigot() {
		super(false);
	}

	@Deprecated //Only when we really know the actual usage of this method, this will always be marked as deprecated 
	@Override
	public FluidStack extractFrom(ItemStack backpack) {
		return null;
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		return Arrays.asList("");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		for (ItemStack armor : playerIn.getArmorInventoryList()) {
			if (armor.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				FluidStack drained = armor.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).drain(1000, true);
				if (drained.amount >= 1000) {
					//worldIn.spawnEntityInWorld(entityIn);
				}
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.getTileEntity(pos).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
			for (ItemStack armor : playerIn.getArmorInventoryList()) {
				if (armor.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
					worldIn.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing).fill(armor.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).drain(1000, true), true);
				}
			}
		}
		
		return EnumActionResult.PASS;
	}

}
