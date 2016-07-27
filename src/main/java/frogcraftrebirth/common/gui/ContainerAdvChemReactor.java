package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileAdvChemReactor;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAdvChemReactor extends ContainerTileFrog<TileAdvChemReactor> {

	public ContainerAdvChemReactor(InventoryPlayer playerInv, TileAdvChemReactor tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 147, 52));
		for (int i = 1; i <= 5; i++)
			this.addSlotToContainer(new SlotItemHandler(tile.inv, i, 20 + i * 20, 22));
		for (int j = 1; j <= 5; j++)
			this.addSlotToContainer(new SlotItemHandler(tile.inv, j + 5, 20 + j * 20, 52));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 11, 12, 22));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 12, 12, 52));
		this.registerPlayerInventory(playerInv);
	}

}
