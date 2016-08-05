package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerFluidOutputHatch extends ContainerTileFrog<TileFluidOutputHatch> {

	public ContainerFluidOutputHatch(InventoryPlayer playerInv, TileFluidOutputHatch tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 113, 21));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 1, 113, 56));
		this.registerPlayerInventory(playerInv);
	}

}
