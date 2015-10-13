package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileAdvChemReactor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerAdvChemReactor extends Container {

	TileAdvChemReactor tile;
	
	public ContainerAdvChemReactor(InventoryPlayer playerInv, TileAdvChemReactor tile) {
		this.tile = (TileAdvChemReactor)tile;
		
		for (int i=0;i<3;++i) {
			for (int j=0;j<9;++j) {
				this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
        }
        for (int i=0;i<9;++i) {
        	this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
