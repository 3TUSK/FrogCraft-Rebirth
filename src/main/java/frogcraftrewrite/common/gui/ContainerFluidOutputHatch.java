package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileFluidOutputHatch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerFluidOutputHatch extends ContainerTileFrog<TileFluidOutputHatch> {

	public ContainerFluidOutputHatch(InventoryPlayer playerInv, TileFluidOutputHatch tile) {
		super(playerInv, tile); //DRAW SLOTS!!!!!
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
