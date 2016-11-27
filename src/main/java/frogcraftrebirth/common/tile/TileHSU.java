package frogcraftrebirth.common.tile;

import frogcraftrebirth.common.lib.tile.TileEnergyStorage;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileHSU extends TileEnergyStorage implements ITickable {
	
	public final ItemStackHandler inv = new ItemStackHandler(2);
	
	public TileHSU() {
		super(100000000, 2048, 5, true);
	}
	
	protected TileHSU(int maxEnergy, int output, int tier, boolean allowTelep) {
		super(maxEnergy, output, tier, allowTelep);
	}

	@Override
	public void update() {
		if (getWorld().isRemote)
			return;
		super.update();
		
		if (inv.getStackInSlot(1) != null && inv.getStackInSlot(1).getItem() instanceof IElectricItem) {
			this.storedE += ElectricItem.manager.discharge(inv.getStackInSlot(1), output, getSourceTier(), true, false, false);
		}
		
		if (inv.getStackInSlot(0) != null && inv.getStackInSlot(0).getItem() instanceof IElectricItem) {
			this.storedE -= ElectricItem.manager.charge(inv.getStackInSlot(0), this.getOutputEnergyUnitsPerTick(), getSourceTier(), false, false);
		}
		
		sendTileUpdatePacket(this);	
		markDirty();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		inv.deserializeNBT(tag.getCompoundTag("inv"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setTag("inv", inv.serializeNBT());
		return super.writeToNBT(tag);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inv : super.getCapability(capability, facing);
	}

}
