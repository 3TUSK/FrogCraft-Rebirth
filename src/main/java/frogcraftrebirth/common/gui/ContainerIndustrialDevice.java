package frogcraftrebirth.common.gui;

import java.util.Iterator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.common.tile.TileFrogInductionalDevice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerIndustrialDevice extends ContainerTileFrog<TileFrogInductionalDevice> {
	
	public ContainerIndustrialDevice(InventoryPlayer playerInv, TileFrogInductionalDevice tile) {
		super(playerInv, tile);
        this.registerPlayerInventory(playerInv);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer aPlayer) {
		return true;
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, tile.charge);
		crafting.sendProgressBarUpdate(this, 1, tile.process);
		crafting.sendProgressBarUpdate(this, 2, tile.tick);
		crafting.sendProgressBarUpdate(this, 3, tile.heat);
	}
	
	@Override
    public void detectAndSendChanges() {
    	super.detectAndSendChanges();
    	@SuppressWarnings("rawtypes")
		Iterator iter = this.crafters.iterator();
    	while (iter.hasNext()) {
    		ICrafting crafter = (ICrafting)iter.next();
    		crafter.sendProgressBarUpdate(this, 0, tile.charge);
    		crafter.sendProgressBarUpdate(this, 1, tile.process);
    		crafter.sendProgressBarUpdate(this, 2, tile.tick);
    		crafter.sendProgressBarUpdate(this, 3, tile.heat);
    	}
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) tile.charge = value;
		if (id == 1) tile.process = value;
		if (id == 2) tile.heat = value;
	}

}
