/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:44:23 AM, Aug 5, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.techreborn;

import frogcraftrebirth.common.gui.ContainerTileFrog;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPneumaticCompressor extends ContainerTileFrog<TilePneumaticCompressor> {

	public ContainerPneumaticCompressor(InventoryPlayer playerInv, TilePneumaticCompressor tile) {
		super(playerInv, tile); //TODO where are those damnit slots
		this.addSlotToContainer(new SlotItemHandler(tile.input, 0, 0, 0));
		this.addSlotToContainer(new SlotItemHandler(tile.output, 0, 0, 0));
		this.addSlotToContainer(new SlotItemHandler(tile.output, 1, 0, 0));
		this.registerPlayerInventory(playerInv);
	}

}
