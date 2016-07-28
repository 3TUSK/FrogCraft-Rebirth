/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 3:08:25 PM, Apr 2, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileLiquefier;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerLiquefier extends ContainerTileFrog<TileLiquefier> {

	public ContainerLiquefier(InventoryPlayer playerInv, TileLiquefier tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 113, 21));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 1, 113, 56));
		this.registerPlayerInventory(playerInv);
	}

}
