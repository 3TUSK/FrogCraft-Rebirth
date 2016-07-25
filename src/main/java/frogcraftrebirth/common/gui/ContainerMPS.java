package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileMobilePowerStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMPS extends ContainerTileFrog<TileMobilePowerStation>{

	public ContainerMPS(InventoryPlayer playerInv, TileMobilePowerStation tile) {
		super(playerInv, tile);
		// TODO add x, y coordinate
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 0, 0));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 1, 0, 0));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 2, 0, 0));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 3, 0, 0));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 4, 0, 0));
		this.registerPlayerInventory(playerInv);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
