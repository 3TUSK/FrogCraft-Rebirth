package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.lib.tile.TileFrog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerTileFrog<T extends TileFrog> extends Container {

	protected T tile;

	protected ContainerTileFrog(InventoryPlayer playerInv, T tile) {
		this.tile = tile;

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int aSlot) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(aSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (aSlot < this.inventorySlots.size()) {
				if (!this.mergeItemStack(itemstack1, this.inventorySlots.size(), 45, true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, this.inventorySlots.size(), false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack)null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

}
