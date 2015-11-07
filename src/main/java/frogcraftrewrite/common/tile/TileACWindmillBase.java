package frogcraftrewrite.common.tile;

import frogcraftrewrite.common.network.NetworkHandler;
import frogcraftrewrite.common.network.PacketTileUpdate;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileACWindmillBase extends TileFrogGenerator {
	
	boolean canGenEnergy;

	public TileACWindmillBase() {
		super(0, "TileAcademyCityWindmillBase", 1, 32);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote) return;
		
		TileEntity turbine = worldObj.getTileEntity(xCoord, yCoord+7, zCoord);
		if (turbine instanceof TileACWindmillTurbine) {
			this.canGenEnergy = ((TileACWindmillTurbine)turbine).canGenEnergy;
		}
		
		NetworkHandler.sendToAll(new PacketTileUpdate(this));
		
		markDirty();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.canGenEnergy = tag.getBoolean("canGenEnergy");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setBoolean("canGenEnergy", canGenEnergy);
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return direction == ForgeDirection.DOWN;
	}

	@Override
	public double getOfferedEnergy() {
		return canGenEnergy ? super.getOfferedEnergy() : 0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return false;
	}

}
