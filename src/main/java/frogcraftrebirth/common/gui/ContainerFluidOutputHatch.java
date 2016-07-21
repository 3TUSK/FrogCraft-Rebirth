package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerFluidOutputHatch extends ContainerTileFrog<TileFluidOutputHatch> {
	
	int fluidAmount;

	public ContainerFluidOutputHatch(InventoryPlayer playerInv, TileFluidOutputHatch tile) {
		super(playerInv, tile); //DRAW SLOTS!!!!!
		
		this.registerPlayerInventory(playerInv);
	}

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
        listener.sendProgressBarUpdate(this, 0, this.tile.getTankInfo()[0].capacity);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.listeners.size();++i) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			if (this.fluidAmount != this.tile.getTankInfo()[0].capacity)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.getTankInfo()[0].capacity), (EntityPlayerMP)listener);
		}
		this.fluidAmount = this.tile.getTankInfo()[0].capacity;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		//if (id == 0) this.fluidAmount = value; TODO: synchronized FluidTank
	}
}
