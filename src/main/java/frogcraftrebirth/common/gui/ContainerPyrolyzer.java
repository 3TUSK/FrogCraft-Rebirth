package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPyrolyzer extends ContainerTileFrog<TilePyrolyzer> {

	public ContainerPyrolyzer(InventoryPlayer playerInv, TilePyrolyzer tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.input, 0, 24, 28));
		this.addSlotToContainer(new SlotItemHandler(tile.output, 0, 75, 28));
		this.addSlotToContainer(new SlotItemHandler(tile.fluidIO, 0, 113, 21));
		this.addSlotToContainer(new SlotItemHandler(tile.fluidIO, 1, 113, 56));
		this.registerPlayerInventory(playerInv);
	}

}
