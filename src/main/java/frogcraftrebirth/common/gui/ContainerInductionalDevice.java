package frogcraftrebirth.common.gui;

import java.util.Iterator;

import frogcraftrebirth.common.tile.TileFrogInductionalDevice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@Deprecated
public class ContainerInductionalDevice extends ContainerTileFrog<TileFrogInductionalDevice> {
	
	public ContainerInductionalDevice(InventoryPlayer playerInv, TileFrogInductionalDevice tile) {
		super(playerInv, tile);
        this.registerPlayerInventory(playerInv);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer aPlayer) {
		return true;
	}
	
	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendProgressBarUpdate(this, 0, tile.charge);
		listener.sendProgressBarUpdate(this, 1, tile.process);
		listener.sendProgressBarUpdate(this, 2, tile.tick);
		listener.sendProgressBarUpdate(this, 3, tile.heat);
	}
	
	@Override
    public void detectAndSendChanges() {
    	super.detectAndSendChanges();
    	@SuppressWarnings("rawtypes")
		Iterator iter = this.listeners.iterator();
    	while (iter.hasNext()) {
    		IContainerListener listener = (IContainerListener)iter.next();
    		listener.sendProgressBarUpdate(this, 0, tile.charge);
    		listener.sendProgressBarUpdate(this, 1, tile.process);
    		listener.sendProgressBarUpdate(this, 2, tile.tick);
    		listener.sendProgressBarUpdate(this, 3, tile.heat);
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
