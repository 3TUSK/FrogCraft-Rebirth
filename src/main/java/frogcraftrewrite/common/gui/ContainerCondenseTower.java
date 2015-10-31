package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileCondenseTower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerCondenseTower extends ContainerTileFrog<TileCondenseTower>{

	public ContainerCondenseTower(InventoryPlayer playerInv, TileCondenseTower tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new Slot(tile, 0, 113, 37));
		this.addSlotToContainer(new Slot(tile, 1, 113, 56));
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

}
