package frogcraftrebirth.common.gui;

import java.util.Set;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotFrog extends Slot {
	
	protected Set<Item> contentLimit;

	public SlotFrog(IInventory inv, int index, int x, int y) {
		super(inv, index, x, y);
	}
	
	public boolean isItemValid(ItemStack stack) {
		return contentLimit.contains(stack.getItem());
	}
	
	protected SlotFrog setNewContentLimit(Set<Item> newContent) {
		this.contentLimit = newContent;
		return this;
	}

}
