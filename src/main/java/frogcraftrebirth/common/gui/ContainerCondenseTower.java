package frogcraftrebirth.common.gui;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import frogcraftrebirth.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrebirth.common.tile.TileCondenseTower;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;

public class ContainerCondenseTower extends ContainerTileFrog<TileCondenseTower>{
	
	private int charge, tick, fluidAmount;

	public ContainerCondenseTower(InventoryPlayer playerInv, TileCondenseTower tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new Slot(tile, 0, 113, 21));
		this.addSlotToContainer(new Slot(tile, 1, 113, 56));
		this.registerPlayerInventory(playerInv);
	}

	@Override
	public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendProgressBarUpdate(this, 0, this.tile.charge);
        listener.sendProgressBarUpdate(this, 1, this.tile.tick);
        listener.sendProgressBarUpdate(this, 2, this.tile.getTankInfo(null)[0].capacity);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.listeners.size();++i) {
			IContainerListener crafter = (IContainerListener)this.listeners.get(i);
			if (this.charge != this.tile.charge)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.charge), (EntityPlayerMP)crafter);
			if (this.tick != this.tile.tick)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 1, this.tile.tick), (EntityPlayerMP)crafter);
			if (this.fluidAmount != this.tile.getTankInfo(null)[0].capacity)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 2, this.tile.getTankInfo(null)[0].capacity), (EntityPlayerMP)crafter);
		}
		this.charge = this.tile.charge;
		this.tick = this.tile.tick;
		this.fluidAmount = this.tile.getTankInfo(null)[0].capacity;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.tile.charge = value;
		if (id == 1) this.tile.tick = value;
		//if (id == 2) this.tile. TODO: synchronized FluidTank
	}
}
