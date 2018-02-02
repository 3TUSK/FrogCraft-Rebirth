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

import frogcraftrebirth.common.block.BlockMPS;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import java.util.List;

public class ItemMPS extends ItemBlock implements IElectricItem {

	public ItemMPS(BlockMPS block) {
		super(block);
		setUnlocalizedName(block.getUnlocalizedName()); // TODO Make sure this works
		setHasSubtypes(false);
		setMaxStackSize(1);
	}

	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return true;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		normalize(itemStack);
		return itemStack.getTagCompound().getInteger("maxCharge");
	}

	@Override
	public int getTier(ItemStack itemStack) {
		normalize(itemStack);
		return itemStack.getTagCompound().getInteger("tier");
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		normalize(itemStack);
		return itemStack.getTagCompound().getInteger("tier") * 32;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (this.isInCreativeTab(tab)) {
			// Add necessary NBT data so that we won't get NPE.
			ItemStack discharged = normalize(new ItemStack(this, 1, 0));
			list.add(discharged.copy());
			ElectricItem.manager.charge(discharged, 60000, 1, true, false);
			list.add(discharged);
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> aList, ITooltipFlag flag) {
		aList.add(I18n.format("tile.mobilePowerStation.info"));
	}
	
	public static ItemStack normalize(ItemStack stack) {
		if (stack.getItem() instanceof ItemMPS && !stack.hasTagCompound()) {
			NBTTagCompound newTag = new NBTTagCompound();
			newTag.setInteger("charge", 0);
			newTag.setInteger("maxCharge", 60000);
			newTag.setInteger("tier", 1);
			stack.setTagCompound(newTag);
			return stack;
		} else
			return stack; // Prevent attaching unnecessary data to other item
	}

}
