package frogcraftrewrite.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.network.NetworkHandler;
import frogcraftrewrite.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrewrite.common.tile.TileAdvChemReactor;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerAdvChemReactor extends ContainerTileFrog<TileAdvChemReactor> {
	
	private int charge, process;
	
	public ContainerAdvChemReactor(InventoryPlayer playerInv, TileAdvChemReactor tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new Slot(tile, 0, 147, 52));
		for (int i=1;i<=5;i++)
			this.addSlotToContainer(new Slot(tile, i, 20+i*20, 22));
		for (int j=6;j<=10;j++)
			this.addSlotToContainer(new Slot(tile, j, 20+j*20, 52));
		this.addSlotToContainer(new Slot(tile, 11, 12, 22));
		this.addSlotToContainer(new Slot(tile, 12, 12, 52));	
	}
	
	public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tile.charge);
        crafting.sendProgressBarUpdate(this, 1, this.tile.process);
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
		}
		this.charge = this.tile.charge;
		this.process = this.tile.process;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.charge = value;
		if (id == 1) this.process = value;
	}

}
