/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
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

package frogcraftrebirth.common.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public final class ContainerTileFrog extends Container {
	
	private final TileFrog tile;
	
	private final int tileInvCount;
	
	private ContainerTileFrog(TileFrog tile, final int customSlotCount) {
		this.tile = tile;
		this.tileInvCount = customSlotCount;
	}

	// This is overridden to ensure that the ::addSlotToContainer call below has access to this method
	@Override
	protected Slot addSlotToContainer(Slot slot) {
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
			if (listener instanceof EntityPlayerMP) {
				sendDataToClientSide(this, (EntityPlayerMP) listener);
			}
		}
	}

	// TODO Fix the weird stack transfer
	@Nonnull
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (/*index >= 0 && */index <= this.tileInvCount) {
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
	
	private void registerPlayerInventory(InventoryPlayer playerInv) {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
		}
	}

	private void sendDataToClientSide(ContainerTileFrog container, EntityPlayerMP player) {
		IFrogPacket pkt = new PacketFrog02GuiDataUpdate(container);
		NetworkHandler.FROG_NETWORK.sendToPlayer(pkt, player);
	}
	
	public void writeDataForSync(DataOutputStream output) throws IOException {
		tile.writePacketData(output);
	}

	public void updateContainer(DataInputStream input) throws IOException {
		tile.readPacketData(input);
	}

	public static final class Builder {

		public static Builder from(TileFrog tile) {
			return new Builder(tile);
		}

		private final TileFrog tile;
		private InventoryPlayer inventoryPlayer;
		private List<Slot> slots = new ArrayList<>(16); // Consider about the fact that A.C.R. has 13 slots
		private int nonPlayerSlotCounter = 0;

		private Builder(final TileFrog tile) {
			this.tile = tile;
		}

		public Builder withPlayerInventory(InventoryPlayer inv) {
			this.inventoryPlayer = inv;
			return this;
		}

		public Builder withStandardSlot(IItemHandlerModifiable itemHandler, int index, int x, int y) {
			nonPlayerSlotCounter++;
			slots.add(new SlotItemHandler(itemHandler, index, x, y));
			return this;
		}

		public Builder withOutputSlot(IItemHandlerModifiable itemHandler, int index, int x, int y) {
			nonPlayerSlotCounter++;
			slots.add(new SlotOutput(itemHandler, index, x, y));
			return this;
		}

		public Builder withChargerSlot(IItemHandlerModifiable itemHandler, int index, int x, int y) {
			nonPlayerSlotCounter++;
			slots.add(new SlotCharger(itemHandler, index, x, y));
			return this;
		}

		public Builder withDischargeSlot(IItemHandlerModifiable itemHandler, int index, int x, int y) {
			nonPlayerSlotCounter++;
			slots.add(new SlotDischarger(itemHandler, index, x, y));
			return this;
		}

		public ContainerTileFrog build() {
			final ContainerTileFrog container = new ContainerTileFrog(this.tile, this.nonPlayerSlotCounter);
			if (inventoryPlayer != null) {
				container.registerPlayerInventory(inventoryPlayer);
			}
			slots.forEach(container::addSlotToContainer);
			slots = null;
			return container;
		}

	}

}
