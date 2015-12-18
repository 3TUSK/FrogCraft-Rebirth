package frogcraftrewrite.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrewrite.common.tile.TilePyrolyzer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerPyrolyzer extends ContainerTileFrog<TilePyrolyzer> {
	
	private int charge, process, processMax;
	private boolean working;

	public ContainerPyrolyzer(InventoryPlayer playerInv, TilePyrolyzer tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new Slot(tile, 0, 24, 28));
		this.addSlotToContainer(new SlotOutput(tile, 1, 75, 28));
		this.addSlotToContainer(new Slot(tile, 2, 113, 21));
		this.addSlotToContainer(new SlotOutput(tile, 3, 113, 56));
		this.registerPlayerInventory(playerInv);
	}

	public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tile.charge);
        crafting.sendProgressBarUpdate(this, 1, this.tile.process);
        crafting.sendProgressBarUpdate(this, 2, this.tile.processMax);
        crafting.sendProgressBarUpdate(this, 3, this.tile.working ? 1 : 0);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.crafters.size();++i) {
			ICrafting crafter = (ICrafting)this.crafters.get(i);
			if (this.charge != this.tile.charge)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.charge), (EntityPlayerMP)crafter);
			if (this.process != this.tile.process)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 1, this.tile.process), (EntityPlayerMP)crafter);
			if (this.processMax != this.tile.processMax)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 2, this.tile.processMax), (EntityPlayerMP)crafter);
			if (this.working != this.tile.working)
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 3, this.tile.working ? 1 : 0), (EntityPlayerMP)crafter);
		}
		this.charge = this.tile.charge;
		this.process = this.tile.process;
		this.processMax = this.tile.processMax;
		this.working = this.tile.working;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.tile.charge = value;
		if (id == 1) this.tile.process = value;
		if (id == 2) this.tile.processMax = value;
		if (id == 3) this.tile.working = value == 0 ? false : true;
	}
}
