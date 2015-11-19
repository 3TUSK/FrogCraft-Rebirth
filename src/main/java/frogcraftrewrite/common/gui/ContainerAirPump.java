package frogcraftrewrite.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.network.NetworkHandler;
import frogcraftrewrite.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrewrite.common.tile.TileAirPump;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerAirPump extends ContainerTileFrog<TileAirPump> {
	
	int charge, air;
	
	public ContainerAirPump(InventoryPlayer playerInv, TileAirPump tile) {
		super(playerInv, tile);
	}
	
	public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tile.charge);
        crafting.sendProgressBarUpdate(this, 1, this.tile.airAmount());
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.crafters.size();++i) {
			ICrafting crafter = (ICrafting)this.crafters.get(i);
			if (this.charge != this.tile.charge)
				NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.charge), (EntityPlayerMP)crafter);
			if (this.air != this.tile.airAmount())
				NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 1, this.tile.airAmount()), (EntityPlayerMP)crafter);
		}
		this.charge = this.tile.charge;
		this.air = this.tile.airAmount();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.charge = value;
		if (id == 1) this.air = value;
	}

}
