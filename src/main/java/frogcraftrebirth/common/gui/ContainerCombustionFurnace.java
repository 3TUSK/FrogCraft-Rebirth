package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileCombustionFurnace;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCombustionFurnace extends ContainerTileFrog<TileCombustionFurnace> {

	public ContainerCombustionFurnace(InventoryPlayer playerInv, TileCombustionFurnace tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotFuel(tile.input, 0, 24, 28));
		this.addSlotToContainer(new SlotOutput(tile.output, 0, 75, 28));
		this.addSlotToContainer(new SlotItemHandler(tile.fluidIO, 0, 113, 21));
		this.addSlotToContainer(new SlotOutput(tile.fluidIO, 1, 113, 56));
		this.registerPlayerInventory(playerInv);
	}

}
