/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 11:57:09 AM, Jul 22, 2016, CST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.lib;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class FrogInv implements IItemHandler {

	protected final ItemStack[] inv;
	
	public FrogInv(int slotsCount) {
		this.inv = new ItemStack[slotsCount];
	}
	
	@Override
	public int getSlots() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public ItemStack insertItem(int slot, final ItemStack stack, boolean simulate) {
		if (slot >= inv.length)
			return stack;
		if (inv[slot].isItemEqual(stack)) {
			int num = inv[slot].stackSize + stack.stackSize;
			if (num > inv[slot].getMaxStackSize()) {
				ItemStack newStack = stack.copy();
				newStack.stackSize = num - stack.stackSize;
				if (!simulate) 
					inv[slot].stackSize = inv[slot].getMaxStackSize();
				return stack;
			} else {
				if (!simulate) 
					inv[slot].stackSize = num;
				return null;
			}
		} else 
			return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (slot >= inv.length)
			return null;
		if (inv[slot].stackSize < amount)
			return null;
		else  {
			ItemStack toReturn;
			if (!simulate) {
				toReturn = inv[slot].splitStack(amount);
				if (inv[slot].stackSize <= 0)
					inv[slot] = null;
			} else {
				toReturn = inv[slot].copy();
				toReturn.stackSize = amount;
			}
			return toReturn;
		}
	}

}
