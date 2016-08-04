package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileAdvChemReactor;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAdvChemReactor extends ContainerTileFrog<TileAdvChemReactor> {

	public ContainerAdvChemReactor(InventoryPlayer playerInv, TileAdvChemReactor tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotFrog(tile.module, 0, 147, 52));
		for (int i = 0; i < 5; i++) {
			this.addSlotToContainer(new SlotFrog(tile.input, i, 40 + i * 20, 22));
			this.addSlotToContainer(new SlotFrog(tile.output, i, 40 + i * 20, 52));
		}
		this.addSlotToContainer(new SlotFrog(tile.cellInput, 0, 12, 22));
		this.addSlotToContainer(new SlotFrog(tile.cellOutput, 0, 12, 52));
		this.registerPlayerInventory(playerInv);
	}

}
