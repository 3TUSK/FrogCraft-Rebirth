package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrebirth.common.tile.TileAirPump;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerAirPump extends ContainerTileFrog<TileAirPump> {
	
	public int charge, air;
	
	public ContainerAirPump(InventoryPlayer playerInv, TileAirPump tile) {
		super(playerInv, tile);
		this.registerPlayerInventory(playerInv);
	}
	
	@Override
	public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendProgressBarUpdate(this, 0, this.tile.charge);
        listener.sendProgressBarUpdate(this, 1, this.tile.airAmount());
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.listeners.size();++i) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			if (this.charge != this.tile.charge)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.charge), (EntityPlayerMP)listener);
			if (this.air != this.tile.airAmount())
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 1, this.tile.airAmount()), (EntityPlayerMP)listener);
		}
		this.charge = this.tile.charge;
		this.air = this.tile.airAmount();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.tile.charge = value;
		if (id == 1) this.tile.setAirAmount(value);
	}

}
