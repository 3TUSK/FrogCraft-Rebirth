package frogcraftrebirth.common.tile;

import static frogcraftrebirth.api.FrogAPI.FUEL_REG;
import static net.minecraft.tileentity.TileEntityFurnace.getItemBurnTime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileEnergyGenerator;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileCombustionFurnace extends TileEnergyGenerator {

	private static final int CHARGE_MAX = 5000;
	/** Index: 0 input; 1 output; 2 fluid container input; 3 fluid container output.*/
	public final ItemStackHandler inv = new ItemStackHandler(4);
	public final FrogFluidTank tank = new FrogFluidTank(8000);
	
	public boolean working = false;
	public int time = 0, timeMax = 0;
	private ItemStack burning;

	public TileCombustionFurnace() {
		super(1, 16);
	}

	@Override
	public void update() {
		if (worldObj.isRemote)
			return;
		super.update();
		
		//Don't stuck the inventory
		if (inv.getStackInSlot(1) != null && inv.getStackInSlot(1).stackSize >= inv.getStackInSlot(1).getMaxStackSize()) {
			this.sendTileUpdatePacket(this);
			this.markDirty();
			return;
		}
		
		if (working) {
			this.charge += 10;
			this.time--;
		} else if (inv.extractItem(0, 1, true) != null && getItemBurnTime(inv.getStackInSlot(0)) > 0) {	
			this.working = true;
			this.burning = inv.extractItem(0, 1, false);
			this.timeMax = getItemBurnTime(burning) / 4;
			this.time = getItemBurnTime(burning) / 4;
		}
		//Overflowed power will be voided 
		if (this.charge > CHARGE_MAX)
			this.charge = CHARGE_MAX;

		if (this.time <= 0) {
			this.timeMax = 0;
			if (working && burning != null)
				bonus(burning);
			burning = null;
			this.working = false;
		}
		
		if (tank.getFluidAmount() != 0 && inv.getStackInSlot(2) != null) {
			if (inv.getStackInSlot(2).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				ItemStack result = FluidUtil.tryFillContainer(inv.extractItem(2, 1, true), tank, 1000, null, true);
				if (result != null && result.stackSize > 0) {
					inv.extractItem(2, 1, false);
					ItemStack remainder = inv.insertItem(3, result, false);
					if (remainder != null && remainder.stackSize > 0)
						ItemUtil.dropItemStackAsEntityInsanely(worldObj, getPos(), remainder);
				}
			}
		}
		
		this.sendTileUpdatePacket(this);
		this.markDirty();
	}
	
	private void bonus(ItemStack input) {
		if (input == null)
			return;
		
		int[] oreIDs = OreDictionary.getOreIDs(input);
		if (oreIDs.length != 0) { //Why is 0? According to my experience, having more than one ore dict entry is very very rare.
			String oreName = OreDictionary.getOreName(oreIDs[0]);
			if (!oreName.equals("Unknown")) {
				//Feature: if there is no space for byproduct, they just go disappear
				inv.insertItem(1, FUEL_REG.getItemByproduct(oreName), false);
				tank.fill(FUEL_REG.getFluidByproduct(oreName), true);
			}
		} else {
			inv.insertItem(2, FUEL_REG.getItemByproduct(input), false);
			tank.fill(FUEL_REG.getFluidByproduct(input), true);
		}	
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.working = tag.getBoolean("working");
		this.time = tag.getInteger("time");
		this.timeMax = tag.getInteger("timeMax");
		this.inv.deserializeNBT(tag.getCompoundTag("inv"));
		this.burning = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("burning"));
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
		tag.setTag("burning", this.burning != null ? burning.writeToNBT(new NBTTagCompound()) : new NBTTagCompound());
		return super.writeToNBT(tag);
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
