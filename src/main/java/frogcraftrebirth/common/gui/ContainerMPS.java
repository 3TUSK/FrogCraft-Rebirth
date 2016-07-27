package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.lib.config.ConfigMain;
import frogcraftrebirth.common.tile.TileMobilePowerStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMPS extends ContainerTileFrog<TileMobilePowerStation> {

	public ContainerMPS(InventoryPlayer playerInv, TileMobilePowerStation tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 20, 20));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 1, 38, 20));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 2, 56, 20));
		this.addSlotToContainer(new SlotCharger(tile.inv, 3, 113, 24));
		this.addSlotToContainer(new SlotDischarger(tile.inv, 4, 113, 42));
		this.registerPlayerInventory(playerInv);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		if (ConfigMain.enableAccessControl && tile instanceof TileMobilePowerStation)
			return ((TileMobilePowerStation)tile).match(player.getUniqueID());
		else
			return true;
	}

}
