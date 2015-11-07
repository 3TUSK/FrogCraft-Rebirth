package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileThermalCracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerThermalCracker extends ContainerTileFrog<TileThermalCracker> {

	public ContainerThermalCracker(InventoryPlayer playerInv, TileThermalCracker tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new Slot(tile, 0, 24, 29));
		this.addSlotToContainer(new Slot(tile, 1, 76, 29));
		this.addSlotToContainer(new Slot(tile, 2, 114, 22));
		this.addSlotToContainer(new Slot(tile, 3, 114, 56));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return false;
	}
}
