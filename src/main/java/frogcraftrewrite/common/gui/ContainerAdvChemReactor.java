package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileAdvChemReactor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAdvChemReactor extends ContainerTileFrog<TileAdvChemReactor> {
	
	public ContainerAdvChemReactor(InventoryPlayer playerInv, TileAdvChemReactor tile) {
		super(playerInv, tile);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
