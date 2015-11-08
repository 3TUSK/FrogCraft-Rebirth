package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileCombustionFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerCombustionFurnace extends ContainerTileFrog<TileCombustionFurnace> {

	public ContainerCombustionFurnace(InventoryPlayer playerInv, TileCombustionFurnace tile) {
		super(playerInv, tile);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
