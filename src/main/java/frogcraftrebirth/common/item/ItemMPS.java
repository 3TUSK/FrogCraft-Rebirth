/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
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

import frogcraftrebirth.common.block.BlockMPS;
import frogcraftrebirth.common.lib.util.NBTUtil;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMPS extends ItemFrogBlock implements IElectricItem {

	private static final double DEFAULT_MAX_CHARGE = 60000.0;

	public ItemMPS(BlockMPS block) {
		super(block);
		setUnlocalizedName(block.getUnlocalizedName()); // TODO Make sure this works
		setMaxStackSize(1);
	}

	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return true;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		return NBTUtil.getDoubleOrDefault(itemStack.getTagCompound(), "maxCharge", DEFAULT_MAX_CHARGE);
	}

	@Override
	public int getTier(ItemStack itemStack) {
		return NBTUtil.getIntegerOrDefault(itemStack.getTagCompound(), "tier", 1);
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		return NBTUtil.getIntegerOrDefault(itemStack.getTagCompound(), "tier", 1) * 32;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (this.isInCreativeTab(tab)) {
			// Add necessary NBT data so that we won't get NPE.
			NBTTagCompound mpsData = new NBTTagCompound();
			mpsData.setInteger("charge", 0);
			mpsData.setInteger("maxCharge", 60000);
			mpsData.setInteger("tier", 1);
			ItemStack mps = new ItemStack(this, 1, 0, mpsData);
			list.add(mps.copy());
			ElectricItem.manager.charge(mps, 60000, 1, true, false);
			list.add(mps);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> aList, ITooltipFlag flag) {
		aList.add(I18n.format("block.frogcraftrebirth.mobile_power_station.info"));
	}

}
