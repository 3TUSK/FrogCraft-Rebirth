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

import java.util.List;

import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemResources extends ItemFrogCraft {

	public ItemResources(String unlocalizedName, String... subNameArray) {
		this(true, unlocalizedName, subNameArray);
	}
	
	public ItemResources(boolean hasSubType, String unlocalizedName, String... subNameArray) {
		super(hasSubType);
		setUnlocalizedName(unlocalizedName);
		if (hasSubType && subNameArray.length > 0)
			setSubNameArray(subNameArray);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format(getUnlocalizedName(stack) + ".info"));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return nameArray != null ? super.getUnlocalizedName()+"."+nameArray[stack.getMetadata()] : super.getUnlocalizedName(stack);
	}

}
