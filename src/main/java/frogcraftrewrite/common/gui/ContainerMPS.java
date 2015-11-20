package frogcraftrewrite.common.gui;

import frogcraftrewrite.common.tile.TileMobilePowerStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerMPS extends Container{
	
	protected TileMobilePowerStation tile;
	
	public ContainerMPS (TileMobilePowerStation tile) {
		this.tile = tile;
		//TODO: REWORK
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tile.isUseableByPlayer(player);
	}

}
