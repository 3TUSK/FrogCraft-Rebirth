package frogcraftrewrite.common.tile;

import ic2.api.energy.event.EnergyTileLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileUHSU extends TileHSU {
	
	public TileUHSU() {
		super(1000000000, 8192, ForgeDirection.DOWN, false);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote)
			return;
		
		if (!worldObj.isRemote && !loaded) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.loaded = true;
		}
		
		this.markDirty();
	}
	//What.
	@Override
	public int getTier() {
		return 5;
	}

}
