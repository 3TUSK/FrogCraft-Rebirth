package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileAirPump;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAirPump extends ContainerTileFrog<TileAirPump> {

	public ContainerAirPump(InventoryPlayer playerInv, TileAirPump tile) {
		super(playerInv, tile);
		this.registerPlayerInventory(playerInv);
	}

}
