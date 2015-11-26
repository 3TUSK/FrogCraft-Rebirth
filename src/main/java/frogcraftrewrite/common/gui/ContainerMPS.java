package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileMobilePowerStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerMPS extends ContainerTileFrog<TileMobilePowerStation>{

	public ContainerMPS(InventoryPlayer playerInv, TileMobilePowerStation tile) {
		super(playerInv, tile);
		
		this.registerPlayerInventory(playerInv);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tile.isUseableByPlayer(player);
	}

}
