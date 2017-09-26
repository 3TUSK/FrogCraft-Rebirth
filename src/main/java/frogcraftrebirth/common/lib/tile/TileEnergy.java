/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:00:58 PM, Dec 14, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.lib.tile;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraftforge.common.MinecraftForge;

public abstract class TileEnergy extends TileFrog implements IEnergyTile {
	
	private boolean isInEnergyNet;
	
	@Override
	public void invalidate() {
		if (!getWorld().isRemote && isInEnergyNet) {
			isInEnergyNet = false;
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
		}
		super.invalidate();
	}
	
	@Override
	public void validate() {
		if (!getWorld().isRemote && !isInEnergyNet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInEnergyNet = true;
		}
	}

}
