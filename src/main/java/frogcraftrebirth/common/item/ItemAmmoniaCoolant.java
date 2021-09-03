/*
 * Copyright (c) 2015 - 2020 3TUSK, et al.
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

import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemAmmoniaCoolant extends ItemFrog implements IReactorComponent {

	private final int heatStorage;
	
	public ItemAmmoniaCoolant(String type, int storage) {
		super();
		this.heatStorage = storage;
		this.setTranslationKey("frogcraftrebirth.ammonia_coolant." + type);
		this.setMaxDamage(storage);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}
	
	@Override
	public boolean canBePlacedIn(ItemStack stack, IReactor reactor) {
		return true;
	}

	@Override
	public void processChamber(ItemStack yourStack, IReactor reactor, int x, int y, boolean heatRun) {}

	@Override
	public boolean acceptUraniumPulse(ItemStack yourStack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatRun) {
		return false;
	}

	@Override
	public boolean canStoreHeat(ItemStack yourStack, IReactor reactor, int x, int y) {
		return true;
	}

	@Override
	public int getMaxHeat(ItemStack yourStack, IReactor reactor, int x, int y) {
		return heatStorage;
	}

	@Override
	public int getCurrentHeat(ItemStack yourStack, IReactor reactor, int x, int y) {
		NBTTagCompound tag = yourStack.getTagCompound();
		return tag == null ? 0 : tag.getInteger("heat");
	}

	@Override
	public int alterHeat(ItemStack yourStack, IReactor reactor, int x, int y, int heat) {
		NBTTagCompound itemTag = yourStack.getTagCompound();
		int coolantHeat = 0;
		if (itemTag != null) {
			coolantHeat = yourStack.getTagCompound().getInteger("heat");
		} else {
			itemTag = new NBTTagCompound();
			yourStack.setTagCompound(itemTag);
		}
		
		if (coolantHeat > this.heatStorage) {
			reactor.setItemAt(x, y, null);
			heat = this.heatStorage - coolantHeat + 1;
			return heat;
		}
		
		coolantHeat += heat;
		
		if (coolantHeat < 0) {
			heat = coolantHeat;
			coolantHeat = 0;
		} else {
			heat = 0;
		}
		
		yourStack.setItemDamage(this.getMaxDamage(yourStack) * coolantHeat / this.heatStorage);
		itemTag.setInteger("heat", coolantHeat);
		return heat;
	}

	@Override
	public float influenceExplosion(ItemStack yourStack, IReactor reactor) {
		return 0;
	}

}
