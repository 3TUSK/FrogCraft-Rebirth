package frogcraftrewrite.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.tile.TileHSU;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerHybridEStorage extends ContainerTileFrog<TileHSU> {

	int charge;
	
	public ContainerHybridEStorage(InventoryPlayer playerInv, TileHSU tile) {
		super(playerInv, tile);
		//Draw HSU charge/discharge slot, player slots is drawn by ContainerTileFrog<T extends TileFrog>
		this.addSlotToContainer(new Slot(tile, 0, 113, 24));
        this.addSlotToContainer(new Slot(tile, 1, 113, 42));
	}
	
	public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tile.storedE);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tile.isUseableByPlayer(player);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.crafters.size();++i) {
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
			if (this.charge != this.tile.storedE) {
				icrafting.sendProgressBarUpdate(this, 0, this.tile.storedE);
			}
		}
		this.charge = this.tile.storedE;
	}
	
	//TODO IT SHALL BE REWRITE!!!!
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int aSlot) {
		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(aSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (aSlot == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (aSlot != 1 && aSlot != 0) {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityFurnace.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                }
                else if (aSlot >= 3 && aSlot < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (aSlot >= 30 && aSlot < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
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
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.tile.storedE = value;
	}

}
