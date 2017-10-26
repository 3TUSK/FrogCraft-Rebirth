package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileAdvBlastFurnace;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAdvBlastFurnace extends ContainerTileFrog<TileAdvBlastFurnace> {

	public ContainerAdvBlastFurnace(InventoryPlayer playerInv, TileAdvBlastFurnace tile) {
		super(playerInv, tile);
		this.registerPlayerInventory(playerInv);
		this.addSlotToContainer(new SlotFrog(tile.input, 0, 34, 27));
		this.addSlotToContainer(new SlotFrog(tile.input, 1, 52, 27));
		this.addSlotToContainer(new SlotOutput(tile.output, 0, 110, 27));
		this.addSlotToContainer(new SlotOutput(tile.output, 1, 128, 27));
	}
}
