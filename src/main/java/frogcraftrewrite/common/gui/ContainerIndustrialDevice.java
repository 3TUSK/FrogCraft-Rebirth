package frogcraftrewrite.common.gui;

import java.util.Iterator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.tile.TileFrogInductionalDevice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerIndustrialDevice extends Container {

	public TileFrogInductionalDevice tileEntity;
	
	public ContainerIndustrialDevice(InventoryPlayer inventoryPlayer, TileFrogInductionalDevice te) {
        tileEntity = te;
        //Bind input & output start.
        for (int i = 0; i < 2; i++) {
        	for (int j = 0; j < 3; j++) {
        		addSlotToContainer(new Slot(tileEntity, j + i * 3, 8 + j * 18, 23 + i * 18));
        	}
        }
        for (int i = 0; i < 2; i++) {
        	for (int j = 0; j < 3; j++) {
        		addSlotToContainer(new Slot(tileEntity, 6 + j + i * 3, 116 + j * 18, 23 + i * 18));
        	}
        }
        //Bind player inventory start.
        for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer aPlayer) {
		return tileEntity.isUseableByPlayer(aPlayer);
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, tileEntity.energy);
		crafting.sendProgressBarUpdate(this, 1, tileEntity.process);
		crafting.sendProgressBarUpdate(this, 2, tileEntity.tick);
		crafting.sendProgressBarUpdate(this, 3, tileEntity.heat);
	}
	
	@Override
    public void detectAndSendChanges() {
		//TODO adjust the code
    	super.detectAndSendChanges();
    	@SuppressWarnings("rawtypes")
		Iterator iter = this.crafters.iterator();
    	while (iter.hasNext()) {
    		ICrafting var2 = (ICrafting)iter.next();
    		
    		var2.sendProgressBarUpdate(this, 0, tileEntity.energy);
    		var2.sendProgressBarUpdate(this, 1, tileEntity.process);
    		var2.sendProgressBarUpdate(this, 2, tileEntity.tick);
    		var2.sendProgressBarUpdate(this, 3, tileEntity.heat);
    	}
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) tileEntity.energy = value;
		if (id == 1) tileEntity.process = value;
		if (id == 2) tileEntity.heat = value;
	}

}
