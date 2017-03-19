/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 5:23:31 AM, Aug 11, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.item;

import frogcraftrebirth.api.item.IFluidBackpackSpigot;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
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
	
	// TODO Add tooltip for mode
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (playerIn.isSneaking()) {
			if (!itemStackIn.hasTagCompound())
				itemStackIn.setTagCompound(new NBTTagCompound());
			if (!itemStackIn.getTagCompound().hasKey("extraction")) {
				itemStackIn.getTagCompound().setBoolean("extraction", true);
				return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
			} else {
				boolean mode = itemStackIn.getTagCompound().getBoolean("extraction");
				itemStackIn.getTagCompound().setBoolean("extraction", !mode);
				return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
			}
		}
		
		for (ItemStack armor : playerIn.getArmorInventoryList()) {
			if (armor.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				FluidStack drained = armor.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).drain(1000, true);
				if (drained != null && drained.amount >= 1000) {
					worldIn.spawnEntityInWorld(new EntitySnowball(worldIn));
				}
				return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
			}
		}
		
		return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile != null && tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
			for (ItemStack armor : playerIn.getArmorInventoryList()) {
				if (armor.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
					boolean mode = stack.hasTagCompound() && stack.getTagCompound().getBoolean("extraction");
					if (mode)
						armor.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).fill(worldIn.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing).drain(1000, true), true);
					else
						tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing).fill(armor.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).drain(1000, true), true);
				}
				return EnumActionResult.SUCCESS;
			}
		}
		
		return EnumActionResult.PASS;
	}

}
