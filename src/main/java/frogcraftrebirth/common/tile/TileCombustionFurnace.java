package frogcraftrebirth.common.tile;

import static frogcraftrebirth.api.FrogAPI.FUEL_REG;
import static net.minecraft.tileentity.TileEntityFurnace.getItemBurnTime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileFrogGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileCombustionFurnace extends TileFrogGenerator implements ITickable {

	private static final int CHARGE_MAX = 5000;
	/** Index: 0 input; 1 output; 2 fluid container input; 3 fluid container output.*/
	public final ItemStackHandler inv = new ItemStackHandler(4);
	
	public boolean working = false;
	protected FrogFluidTank tank = new FrogFluidTank(8000);
	public int time = 0, timeMax = 0;

	public TileCombustionFurnace() {
		super("TileEntityCombustionFurnace", 1, 16);
	}

	@Override
	public void update() {
		if (worldObj.isRemote)
			return;
		
		this.working = this.time >= 0;
		
		if (!working && inv.extractItem(0, 1, true) != null && getItemBurnTime(inv.getStackInSlot(0)) > 0) {
			this.working = true;
			inv.extractItem(0, 1, false);
			this.bonus(FUEL_REG.getItemByproduct(inv.getStackInSlot(0)));
			this.timeMax = getItemBurnTime(inv.getStackInSlot(0));
			this.time = getItemBurnTime(inv.getStackInSlot(0));
			this.markDirty();
		}

		if (this.working) {
			this.charge += 10;
			this.time--;
		}
		
		if (this.charge > CHARGE_MAX)
			this.charge = CHARGE_MAX;

		if (this.time == 0) {
			this.timeMax = 0;
			this.working = false;
		}
		
		this.sendTileUpdatePacket(this);
	}
	
	private void bonus(ItemStack bonus) {
		//To be rewrited
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.working = tag.getBoolean("working");
		this.time = tag.getInteger("time");
		this.timeMax = tag.getInteger("timeMax");
		this.inv.deserializeNBT(tag.getCompoundTag("inv"));
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
		tank.readPacketData(input);
		this.time = input.readInt();
		this.timeMax = input.readInt();
		this.working = input.readBoolean();
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		tank.writePacketData(output);
		output.writeInt(time);
		output.writeInt(timeMax);
		output.writeBoolean(working);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		this.tank.writeToNBT(tag);
		tag.setBoolean("working", this.working);
		tag.setInteger("time", this.time);
		tag.setInteger("timeMax", this.timeMax);
		tag.setTag("inv", this.inv.serializeNBT());
		return super.writeToNBT(tag);
	}

	@Override
	public double getOfferedEnergy() {
		return working ? super.getOfferedEnergy() : 0;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		switch (facing) {
			case DOWN:
			case UP: return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
			default: return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		switch (facing) {
			case DOWN:
			case UP: return (T)inv;
			default: return (T)tank;
		}
	}

}
