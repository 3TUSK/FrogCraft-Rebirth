package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileCombustionFurnace;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCombustionFurnace extends ContainerTileFrog<TileCombustionFurnace> {

	public ContainerCombustionFurnace(InventoryPlayer playerInv, TileCombustionFurnace tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 24, 28));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 1, 75, 28));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 2, 113, 21));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 3, 113, 56));
		this.registerPlayerInventory(playerInv);
	}

}
