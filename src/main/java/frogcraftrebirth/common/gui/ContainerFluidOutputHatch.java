package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerFluidOutputHatch extends ContainerTileFrog<TileFluidOutputHatch> {
	
	//int fluidAmount;

	public ContainerFluidOutputHatch(InventoryPlayer playerInv, TileFluidOutputHatch tile) {
		super(playerInv, tile); //DRAW SLOTS!!!!!
		//TODO
		this.registerPlayerInventory(playerInv);
	}

}
