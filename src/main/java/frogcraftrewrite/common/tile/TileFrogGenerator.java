package frogcraftrewrite.common.tile;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileFrogGenerator extends TileFrogInventory implements ISidedInventory, IEnergySource {

	public int energy, sourceTier, output;
	protected boolean isInENet;
	
	public TileFrogGenerator (int sourceTier, int output) {
		this.sourceTier = sourceTier;
		this.output = output;
	}
	
	@Override
	public void invalidate() {
		if (isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();	
		if (!isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}
	}
	
	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return true;
	}

	@Override
	public double getOfferedEnergy() {
		return Math.min(energy, output);
	}

	@Override
	public void drawEnergy(double amount) {
		this.energy -= amount;
		this.energy = energy < 0 ? 0 : energy;
	}

	@Override
	public int getSourceTier() {
		return sourceTier;
	}

	@Override
	public abstract int[] getAccessibleSlotsFromSide(int side);

	@Override
	public abstract boolean canInsertItem(int slot, ItemStack item, int side);

	@Override
	public abstract boolean canExtractItem(int slot, ItemStack item, int side);
	
	@Override
	public ItemStack decrStackSize(int slot, int decrNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub
		
	}

}
