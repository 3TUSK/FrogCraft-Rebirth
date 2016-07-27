package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrebirth.common.tile.TileHSU;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerHybridEStorage extends ContainerTileFrog<TileHSU> {

	public ContainerHybridEStorage(InventoryPlayer playerInv, TileHSU tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 113, 24));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 1, 113, 42));
		this.registerPlayerInventory(playerInv);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (IContainerListener listener : listeners) {
			if (listener instanceof EntityPlayerMP)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.storedE), (EntityPlayerMP)listener);
		}
	}

}
