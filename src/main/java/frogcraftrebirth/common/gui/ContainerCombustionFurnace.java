package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrebirth.common.tile.TileCombustionFurnace;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCombustionFurnace extends ContainerTileFrog<TileCombustionFurnace> {

	private int charge, time;
	
	public ContainerCombustionFurnace(InventoryPlayer playerInv, TileCombustionFurnace tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 24, 28));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 1, 75, 28));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 2, 113, 21));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 3, 113, 56));
		this.registerPlayerInventory(playerInv);
	}
	
	@Override
	public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendProgressBarUpdate(this, 0, this.tile.charge);
        listener.sendProgressBarUpdate(this, 1, this.tile.time);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.listeners.size();++i) {
			IContainerListener crafter = (IContainerListener)this.listeners.get(i);
			if (this.charge != this.tile.charge)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.charge), (EntityPlayerMP)crafter);
			if (this.time != this.tile.time)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 1, this.tile.time), (EntityPlayerMP)crafter);
		}
		this.charge = this.tile.charge;
		this.time = this.tile.time;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.tile.charge = value;
		if (id == 1) this.tile.time = value;
	}
}
