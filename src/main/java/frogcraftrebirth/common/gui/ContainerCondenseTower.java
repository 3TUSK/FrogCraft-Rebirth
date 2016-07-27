package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileCondenseTower;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCondenseTower extends ContainerTileFrog<TileCondenseTower> {

	public ContainerCondenseTower(InventoryPlayer playerInv, TileCondenseTower tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 113, 21));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 1, 113, 56));
		this.registerPlayerInventory(playerInv);
	}

}
