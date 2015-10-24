package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileFrog;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class ContainerTileFrog<T extends TileFrog> extends Container {
	
	protected T tile;
	
	protected ContainerTileFrog(InventoryPlayer playerInv, T tile) {
		this.tile = tile;
		for (int i=0;i<3;++i) {
			for (int j=0;j<9;++j) {
				this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
        for (int i=0;i<9;++i) {
        	this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
        }
	}

}
