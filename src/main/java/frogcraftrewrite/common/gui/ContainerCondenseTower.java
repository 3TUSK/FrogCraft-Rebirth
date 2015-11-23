package frogcraftrewrite.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.network.NetworkHandler;
import frogcraftrewrite.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrewrite.common.tile.TileCondenseTower;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerCondenseTower extends ContainerTileFrog<TileCondenseTower>{
	
	private int charge, tick, fluidAmount;

	public ContainerCondenseTower(InventoryPlayer playerInv, TileCondenseTower tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new Slot(tile, 0, 113, 21));
		this.addSlotToContainer(new Slot(tile, 1, 113, 56));
	}

	public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tile.charge);
        crafting.sendProgressBarUpdate(this, 1, this.tile.tick);
        crafting.sendProgressBarUpdate(this, 2, this.tile.getTankInfo()[0].capacity);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.crafters.size();++i) {
			ICrafting crafter = (ICrafting)this.crafters.get(i);
			if (this.charge != this.tile.charge)
				NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.charge), (EntityPlayerMP)crafter);
			if (this.tick != this.tile.tick)
				NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 1, this.tile.tick), (EntityPlayerMP)crafter);
			if (this.fluidAmount != this.tile.getTankInfo()[0].capacity)
				NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 2, this.tile.getTankInfo()[0].capacity), (EntityPlayerMP)crafter);
		}
		this.charge = this.tile.charge;
		this.tick = this.tile.tick;
		this.fluidAmount = this.tile.getTankInfo()[0].capacity;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.tile.charge = value;
		if (id == 1) this.tile.tick = value;
		//if (id == 2) this.tile. TODO: synchronized FluidTank
	}
}
