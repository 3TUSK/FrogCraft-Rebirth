/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 11:57:09 AM, Jul 22, 2016, CST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.lib.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
@Deprecated //Will switch to individual ItemHandler, instead of implementing on TileEntity
public abstract class TileFrogInventory extends TileFrog implements IItemHandler {

	protected ItemStack[] inv;
	
	protected TileFrogInventory(int slotsCount) {
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
		ItemStack toReturn = null;
		if (inv[slot].stackSize < amount)
			return toReturn;
		else if (inv[slot].stackSize == amount) {
			toReturn = inv[slot].copy();
			if (!simulate)
				inv[slot] = null;
			return toReturn;
		} else {
			toReturn = inv[slot].copy();
			toReturn.stackSize = amount;
			if (!simulate)
				inv[slot].stackSize = inv[slot].stackSize - amount;
			return toReturn;
		}
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing direction) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing direction) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)this : super.getCapability(capability, direction);
	}

}
