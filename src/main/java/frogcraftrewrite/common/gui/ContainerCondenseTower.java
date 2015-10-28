package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileCondenseTower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerCondenseTower extends ContainerTileFrog<TileCondenseTower>{

	public ContainerCondenseTower(InventoryPlayer playerInv, TileCondenseTower tile) {
		super(playerInv, tile);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

}
