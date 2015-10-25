package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileAirPump;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAirPump extends ContainerTileFrog<TileAirPump> {
	
	public ContainerAirPump(InventoryPlayer playerInv, TileAirPump tile) {
		super(playerInv, tile);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
