package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileMobilePowerStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerMPS extends Container{
	
	protected TileMobilePowerStation tile;
	
	public ContainerMPS (TileMobilePowerStation tile) {
		this.tile = tile;
		//Charging slots
		addSlotToContainer(new Slot(tile, 0, 44, 20));//Charging Slot 1
		addSlotToContainer(new Slot(tile, 1, 62, 20));//Charging Slot 2
		addSlotToContainer(new Slot(tile, 2, 80, 20));//Charging Slot 3
		addSlotToContainer(new Slot(tile, 3, 98, 20));//Charging Slot 4
		//Discharging slots
		addSlotToContainer(new Slot(tile, 4, 44, 46));//Discharging Slot 1
		addSlotToContainer(new Slot(tile, 5, 62, 46));//Discharging Slot 2
		addSlotToContainer(new Slot(tile, 6, 80, 46));//Discharging Slot 3
		addSlotToContainer(new Slot(tile, 7, 98, 46));//Discharging Slot 4
		//upgrades slots
		addSlotToContainer(new Slot(tile, 8, 152, 8));
		addSlotToContainer(new Slot(tile, 9, 152, 26));
		addSlotToContainer(new Slot(tile, 10, 152, 44));
		addSlotToContainer(new Slot(tile, 11, 152, 62));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tile.profile.getId() == player.getGameProfile().getId();
	}
	
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) tile.storedEnergy = value;
	}

}
