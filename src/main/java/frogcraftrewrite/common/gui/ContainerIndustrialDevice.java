package frogcraftrewrite.common.gui;

import java.util.Iterator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.lib.tile.TileFrogInductionalDevice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerIndustrialDevice extends ContainerTileFrog<TileFrogInductionalDevice> {
	
	public ContainerIndustrialDevice(InventoryPlayer playerInv, TileFrogInductionalDevice tile) {
		super(playerInv, tile);
        //Bind input & output start.
        for (int i = 0; i < 2; i++) {
        	for (int j = 0; j < 3; j++) {
        		addSlotToContainer(new Slot(tile, j + i * 3, 8 + j * 18, 23 + i * 18));
        	}
        }
        for (int i = 0; i < 2; i++) {
        	for (int j = 0; j < 3; j++) {
        		addSlotToContainer(new Slot(tile, 6 + j + i * 3, 116 + j * 18, 23 + i * 18));
        	}
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer aPlayer) {
		return true;
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, tile.energy);
		crafting.sendProgressBarUpdate(this, 1, tile.process);
		crafting.sendProgressBarUpdate(this, 2, tile.tick);
		crafting.sendProgressBarUpdate(this, 3, tile.heat);
	}
	
	@Override
    public void detectAndSendChanges() {
		//TODO adjust the code
    	super.detectAndSendChanges();
    	@SuppressWarnings("rawtypes")
		Iterator iter = this.crafters.iterator();
    	while (iter.hasNext()) {
    		ICrafting var2 = (ICrafting)iter.next();
    		
    		var2.sendProgressBarUpdate(this, 0, tile.energy);
    		var2.sendProgressBarUpdate(this, 1, tile.process);
    		var2.sendProgressBarUpdate(this, 2, tile.tick);
    		var2.sendProgressBarUpdate(this, 3, tile.heat);
    	}
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) tile.energy = value;
		if (id == 1) tile.process = value;
		if (id == 2) tile.heat = value;
	}

}
