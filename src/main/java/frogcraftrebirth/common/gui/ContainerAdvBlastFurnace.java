package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileAdvBlastFurnace;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAdvBlastFurnace extends ContainerTileFrog<TileAdvBlastFurnace> {

	public ContainerAdvBlastFurnace(InventoryPlayer playerInv, TileAdvBlastFurnace tile) {
		super(playerInv, tile);
		this.registerPlayerInventory(playerInv);
		this.addSlotToContainer(new SlotFrog(tile.input, 0, 33, 26));
		this.addSlotToContainer(new SlotFrog(tile.input, 1, 51, 26));
		this.addSlotToContainer(new SlotOutput(tile.output, 0, 109, 26));
		this.addSlotToContainer(new SlotOutput(tile.output, 1, 127, 26));
	}
}
