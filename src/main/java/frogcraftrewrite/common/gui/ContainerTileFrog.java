package frogcraftrewrite.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.lib.tile.TileFrog;
import frogcraftrewrite.common.network.NetworkHandler;
import frogcraftrewrite.common.network.PacketFrog02GuiDataUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

public abstract class ContainerTileFrog<T extends TileFrog> extends Container {

	private boolean needSynchronizedFluidTank;
	
	protected T tile;
	
	private int tileInvCount;
	
	public int fluidID, fluidAmount;

	protected ContainerTileFrog(InventoryPlayer playerInv, T tile) {
		this(playerInv, tile, false);
	}
	
	protected ContainerTileFrog(InventoryPlayer playerInv, T tile, boolean needSynchronizedFluidTank) {
		if (needSynchronizedFluidTank && !(tile instanceof IFluidHandler))
			throw new IllegalArgumentException("Only those tile entity which has fluid tank can set needSynchronizedFluidTank true!!!");
		this.needSynchronizedFluidTank = needSynchronizedFluidTank;
		this.tile = tile;
	}
	
	public void addCraftingToCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		if (needSynchronizedFluidTank) {
			crafting.sendProgressBarUpdate(this, 100, this.getFluidTankContentID((IFluidHandler)tile));
			crafting.sendProgressBarUpdate(this, 101, this.getFluidTankContentAmount((IFluidHandler)tile));
		}
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
		if (needSynchronizedFluidTank) {
			for (int i=0;i<this.crafters.size();++i) {
				ICrafting crafter = (ICrafting)this.crafters.get(i);
				if (this.fluidID != getFluidTankContentID((IFluidHandler)tile))
					NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 100, getFluidTankContentID((IFluidHandler)tile)), (EntityPlayerMP)crafter);
				if (this.fluidAmount != getFluidTankContentAmount((IFluidHandler)tile))
					NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 100, getFluidTankContentAmount((IFluidHandler)tile)), (EntityPlayerMP)crafter);
			}
			this.fluidID = getFluidTankContentID((IFluidHandler)tile);
			this.fluidAmount = getFluidTankContentAmount((IFluidHandler)tile);
		}
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		super.updateProgressBar(id, value);
		if (id == 100) this.fluidID = value;
		if (id == 101) this.fluidAmount = value;
	}
	
	protected int getFluidTankContentID(IFluidHandler tank) {
		return tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.getFluidID();
	}
	
	protected int getFluidTankContentAmount(IFluidHandler tank) {
		return tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.amount;
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

}
