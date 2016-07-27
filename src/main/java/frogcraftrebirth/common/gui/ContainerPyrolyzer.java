package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPyrolyzer extends ContainerTileFrog<TilePyrolyzer> {

	public ContainerPyrolyzer(InventoryPlayer playerInv, TilePyrolyzer tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 24, 28));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 1, 75, 28));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 2, 113, 21));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 3, 113, 56));
		this.registerPlayerInventory(playerInv);
	}

}
