/*
 * Copyright (c) 2015 - 2019 3TUSK, et al.
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

package frogcraftrebirth.api.mps;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.ItemStack;

public enum MPSUpgradeManager {
	
	INSTANCE;
	
	private final Set<ItemStack> validSolarUpgrades = new LinkedHashSet<>();
	
	private final Map<ItemStack, Integer> validStorageUpgrades = new LinkedHashMap<>();
	
	private final Map<ItemStack, Integer> validVoltageUpgrades = new LinkedHashMap<>();
	
	public boolean isSolarUpgradeValid(ItemStack stack) { 
		if (stack.isEmpty())
			return false;
		for (ItemStack aStack : validSolarUpgrades) {
			if (aStack.isItemEqual(stack))
				return true;
		}
		return false;
	}

	public int getEnergyStoreIncrementOf(ItemStack stack) {
		if (stack.isEmpty())
			return 0;
		for (Map.Entry<ItemStack, Integer> entry : validStorageUpgrades.entrySet()) {
			if (entry.getKey().isItemEqual(stack)) {
				return stack.getCount() / entry.getKey().getCount() * entry.getValue();
			}
		}
		return 0;
	}

	public int getVoltageIncrementOf(ItemStack stack) {
		if (stack.isEmpty())
			return 0;
		for (Map.Entry<ItemStack, Integer> entry : validVoltageUpgrades.entrySet()) {
			if (entry.getKey().isItemEqual(stack)) {
				return stack.getCount() / entry.getKey().getCount() * entry.getValue();
			}
		}
		return 0;
	}

	/**
	 * @param stack The upgrade item being registered
	 */
	public void registerSolarUpgrade(ItemStack stack) {
		validSolarUpgrades.add(stack);
	}
	
	/**
	 * @param stack The upgrade item being registered
	 * @param incrQuantity The quantity increased of energy storage, measured in EU
	 */
	public void registerStorageUpgrade(ItemStack stack, int incrQuantity) {
		validStorageUpgrades.put(stack, incrQuantity);
	}
	
	/**
	 * @param stack The upgrade item being registered
	 * @param incrQuantity The quantity increased of voltage level. 1 is LV, 2 is MV, 3 is HV, and so on.
	 * @see ic2.api.energy.tile.IEnergySource#getSourceTier
	 */
	public void registerVoltageUpgrades(ItemStack stack, int incrQuantity) {
		validVoltageUpgrades.put(stack, incrQuantity);
	}
	
	/**
	 * Clear all existed registry info.
	 */
	public void resetRegistry() {
		validSolarUpgrades.clear();
		validStorageUpgrades.clear();
		validVoltageUpgrades.clear();
	}
}
