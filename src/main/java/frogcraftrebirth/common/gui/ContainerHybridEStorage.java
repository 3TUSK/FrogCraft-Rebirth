package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrebirth.common.tile.TileHSU;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerHybridEStorage extends ContainerTileFrog<TileHSU> {

	private int charge;
	
	public ContainerHybridEStorage(InventoryPlayer playerInv, TileHSU tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new Slot(tile, 0, 113, 24));
		this.addSlotToContainer(new Slot(tile, 1, 113, 42));
	}
	
	@Override
	public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendProgressBarUpdate(this, 0, this.tile.storedE);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.listeners.size();++i) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			if (this.charge != this.tile.storedE) {
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.storedE), (EntityPlayerMP)listener);
			}
		}
		this.charge = this.tile.storedE;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.tile.storedE = value;
	}

}
