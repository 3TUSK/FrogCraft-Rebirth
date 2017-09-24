package frogcraftrebirth.common.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.network.IFrogPacket;
import frogcraftrebirth.common.network.NetworkHandler;
import frogcraftrebirth.common.network.PacketFrog02GuiDataUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerTileFrog<T extends TileFrog> extends Container {
	
	protected T tile;
	
	private int tileInvCount;
	
	public ContainerTileFrog(InventoryPlayer playerInv, T tile) {
		this.tile = tile;
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
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (IContainerListener listener : listeners) {
			if (listener instanceof EntityPlayerMP)
				sendDataToClientSide(this, (EntityPlayerMP)listener);
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index >= 0 && index <= this.tileInvCount) {
				if (!this.mergeItemStack(itemstack1, this.tileInvCount, 27 + this.tileInvCount, true)) {
					return ItemStack.EMPTY;
				}
			} else if (index > this.tileInvCount && index < 27 + this.tileInvCount) {
				if (!this.mergeItemStack(itemstack1, this.inventorySlots.size() - 9, this.inventorySlots.size(), false)) {
					return ItemStack.EMPTY;
				}
			}

			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
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

	protected void sendDataToClientSide(ContainerTileFrog<?> container, EntityPlayerMP player) {
		IFrogPacket pkt = new PacketFrog02GuiDataUpdate(container);
		NetworkHandler.FROG_NETWORK.sendToPlayer(pkt, player);
	}
	
	public void writeDataForSync(DataOutputStream output) throws IOException {
		tile.writePacketData(output);
	}

	public void updateContainer(DataInputStream input) throws IOException {
		tile.readPacketData(input);
	}

}
