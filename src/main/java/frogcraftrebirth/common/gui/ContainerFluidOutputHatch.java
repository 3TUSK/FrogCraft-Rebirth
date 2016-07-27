package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerFluidOutputHatch extends ContainerTileFrog<TileFluidOutputHatch> {

	public ContainerFluidOutputHatch(InventoryPlayer playerInv, TileFluidOutputHatch tile) {
		super(playerInv, tile);
		// TODO
		this.registerPlayerInventory(playerInv);
	}

}
