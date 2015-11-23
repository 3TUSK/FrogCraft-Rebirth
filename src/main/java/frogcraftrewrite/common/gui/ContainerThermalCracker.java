package frogcraftrewrite.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.network.NetworkHandler;
import frogcraftrewrite.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrewrite.common.tile.TileThermalCracker;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerThermalCracker extends ContainerTileFrog<TileThermalCracker> {
	
	private int charge, process, fluidAmount;

	public ContainerThermalCracker(InventoryPlayer playerInv, TileThermalCracker tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new Slot(tile, 0, 24, 29));
		this.addSlotToContainer(new Slot(tile, 1, 76, 29));
		this.addSlotToContainer(new Slot(tile, 2, 114, 22));
		this.addSlotToContainer(new Slot(tile, 3, 114, 56));
	}

	public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tile.charge);
        crafting.sendProgressBarUpdate(this, 1, this.tile.process);
        crafting.sendProgressBarUpdate(this, 2, this.tile.getTankInfo()[0].capacity);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.crafters.size();++i) {
			ICrafting crafter = (ICrafting)this.crafters.get(i);
			if (this.charge != this.tile.charge)
				NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.charge), (EntityPlayerMP)crafter);
			if (this.process != this.tile.process)
				NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 1, this.tile.process), (EntityPlayerMP)crafter);
			if (this.fluidAmount != this.tile.getTankInfo()[0].capacity)
				NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 2, this.tile.getTankInfo()[0].capacity), (EntityPlayerMP)crafter);
		}
		this.charge = this.tile.charge;
		this.process = this.tile.process;
		this.fluidAmount = this.tile.getTankInfo()[0].capacity;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.tile.charge = value;
		if (id == 1) this.tile.process = value;
		//if (id == 2) this.fluidAmount = value; TODO: synchronized FluidTank
	}
}
