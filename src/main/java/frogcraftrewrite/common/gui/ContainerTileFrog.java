package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.lib.tile.TileFrog;
import frogcraftrewrite.common.network.IFrogPacket;
import frogcraftrewrite.common.network.NetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerTileFrog<T extends TileFrog> extends Container {
	
	protected T tile;
	
	private int tileInvCount;
	
	public ContainerTileFrog(InventoryPlayer playerInv, T tile) {
		this.tile = tile;
	}
	
	public void addCraftingToCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
	}
	
	@Override
	public Slot addSlotToContainer(Slot slot) {
		if (!(slot.inventory instanceof InventoryPlayer))
			tileInvCount++;
		
		return super.addSlotToContainer(slot);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int aSlot) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(aSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (aSlot < this.tileInvCount) {
				if (!this.mergeItemStack(itemstack1, this.tileInvCount, 36 + this.tileInvCount, true)) { //question
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
	
	protected void registerPlayerInventory(InventoryPlayer playerInv) {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
		}
	}

	protected void sendDataToClientSide(IFrogPacket packet, EntityPlayerMP player) {
		NetworkHandler.FROG_NETWORK.sendToPlayer(packet, player);
	}
}
