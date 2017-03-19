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
	
	private static transient boolean processingEnergyTileLoading = false;
	
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
		if (processingEnergyTileLoading) {
			// This will ensure that chunk loading won't stuck
			// I could not think about another way to prevent this happening
			// This will definitely slow down chunk loading somehow, 
			// hope it won't slow it down too much
			return;
		}
		if (!getWorld().isRemote && !isInEnergyNet) {
			processingEnergyTileLoading = true;
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			processingEnergyTileLoading = false;
			isInEnergyNet = true;
		}
	}
	
	public boolean hasBeenInEnergyNet() {
		return this.isInEnergyNet;
	}

}
