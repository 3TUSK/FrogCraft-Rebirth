/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
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
					worldIn.spawnEntity(new EntitySnowball(worldIn));
				}
				return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
			}
		}
		
		return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile != null && tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
			for (ItemStack armor : playerIn.getArmorInventoryList()) {
				if (armor.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
					ItemStack stack = playerIn.getHeldItem(hand);
					boolean mode = stack.hasTagCompound() && stack.getTagCompound().getBoolean("extraction");
					if (mode)
						armor.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).fill(worldIn.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing).drain(1000, true), true);
					else
						tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing).fill(armor.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).drain(1000, true), true);
					return EnumActionResult.SUCCESS;
				}
			}
		}
		
		return EnumActionResult.PASS;
	}

}
