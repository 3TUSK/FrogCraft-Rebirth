package frogcraftrebirth.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.common.network.PacketFrog02GuiDataUpdate;
import frogcraftrebirth.common.tile.TileHSU;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerHybridEStorage extends ContainerTileFrog<TileHSU> {

	private int charge;
	
	public ContainerHybridEStorage(InventoryPlayer playerInv, TileHSU tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new Slot(tile, 0, 113, 24));
		this.addSlotToContainer(new Slot(tile, 1, 113, 42));
	}
	
	public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tile.storedE);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i=0;i<this.crafters.size();++i) {
			ICrafting crafter = (ICrafting)this.crafters.get(i);
			if (this.charge != this.tile.storedE) {
				sendDataToClientSide(new PacketFrog02GuiDataUpdate(this.windowId, 0, this.tile.storedE), (EntityPlayerMP)crafter);
			}
		}
		this.charge = this.tile.storedE;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int value) {
		if (id == 0) this.tile.storedE = value;
	}

}
