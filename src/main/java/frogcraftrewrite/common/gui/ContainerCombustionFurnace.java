package frogcraftrewrite.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.network.NetworkHandler;
import frogcraftrewrite.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrewrite.common.tile.TileCombustionFurnace;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerCombustionFurnace extends ContainerTileFrog<TileCombustionFurnace> {

	private int charge, time;
	
	public ContainerCombustionFurnace(InventoryPlayer playerInv, TileCombustionFurnace tile) {
		super(playerInv, tile, 4);
	}
	
	public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tile.charge);
        crafting.sendProgressBarUpdate(this, 1, this.tile.time);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.crafters.size();++i) {
			ICrafting crafter = (ICrafting)this.crafters.get(i);
			if (this.charge != this.tile.charge)
				NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.charge), (EntityPlayerMP)crafter);
			if (this.time != this.tile.time)
				NetworkHandler.sendToPlayer(new PacketFrog02GuiDataUpdate(this.windowId, 1, this.tile.time), (EntityPlayerMP)crafter);
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
