package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrebirth.common.tile.TileAdvChemReactor;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAdvChemReactor extends ContainerTileFrog<TileAdvChemReactor> {
	
	private int charge, process;
	
	public ContainerAdvChemReactor(InventoryPlayer playerInv, TileAdvChemReactor tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 147, 52));
		for (int i=1;i<=5;i++)
			this.addSlotToContainer(new SlotItemHandler(tile.inv, i, 20+i*20, 22));
		for (int j=1;j<=5;j++)
			this.addSlotToContainer(new SlotItemHandler(tile.inv, j+5, 20+j*20, 52));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 11, 12, 22));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 12, 12, 52));
		this.registerPlayerInventory(playerInv);
	}
	
	@Override
	public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendProgressBarUpdate(this, 0, this.tile.charge);
        listener.sendProgressBarUpdate(this, 1, this.tile.process);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.listeners.size();++i) {
			IContainerListener listeners = (IContainerListener)this.listeners.get(i);
			if (this.charge != this.tile.charge)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.charge), (EntityPlayerMP)listeners);
			if (this.process != this.tile.process)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 1, this.tile.process), (EntityPlayerMP)listeners);
		}
		this.charge = this.tile.charge;
		this.process = this.tile.process;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.tile.charge = value;
		if (id == 1) this.tile.process = value;
	}

}
