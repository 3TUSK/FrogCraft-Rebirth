package frogcraftrewrite.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
/**
 * The Academy Windmill base block.
 * */
public class TileACWindmillBase extends TileEntity implements IEnergySource{

	boolean isInENet, canGenEnergy;
	
	@Override
	public void invalidate() {
		if (isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
		super.invalidate();
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote)
			return;
		
		if (!isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}
		
		TileEntity turbine = worldObj.getTileEntity(xCoord, yCoord+7, zCoord);
		if (turbine instanceof TileACWindmillTurbine) {
			this.canGenEnergy = ((TileACWindmillTurbine)turbine).canGenEnergy;
		}
		markDirty();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.canGenEnergy = tag.getBoolean("canGenEnergy");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setBoolean("canGenEnergy", canGenEnergy);
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return direction != ForgeDirection.UP;
	}

	@Override
	public double getOfferedEnergy() {
		return canGenEnergy ? 32 : 0;
	}

	@Override
	public void drawEnergy(double amount) {}

	@Override
	public int getSourceTier() {
		return 1;
	}

}
