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
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileCombustionFurnace extends TileFrogGenerator implements IFluidHandler {

	public boolean working = false;
	protected FrogFluidTank tank = new FrogFluidTank(8000);
	public int time = 0, timeMax = 0;
	private static final int CHARGE_MAX = 5000;

	public TileCombustionFurnace() {
		super(4, "TileEntityCombustionFurnace", 1, 16);
		// 0 input 1 output 2 fluid container input 3 fluid container output
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;
		super.updateEntity();
		
		this.working = this.time >= 0;
		
		if (!working && this.inv[0] != null && getItemBurnTime(this.inv[0]) > 0) {
			this.working = true;
			this.decrStackSize(0, 1);
			this.bonus(FUEL_REG.getItemByproduct(inv[0]));
			this.timeMax = getItemBurnTime(this.inv[0]);
			this.time = getItemBurnTime(this.inv[0]);
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
		if (bonus == null)
			return;
		if (this.getStackInSlot(1) == null)
			this.setInventorySlotContents(1, bonus);
		else if (inv[1].isItemEqual(bonus)) {
			this.setInventorySlotContents(1, new ItemStack(inv[1].getItem(), inv[1].stackSize + bonus.stackSize, inv[1].getItemDamage()));
		}
		
		if (FUEL_REG.getFluidByproduct(inv[0]) != null)
			this.tank.fill(FUEL_REG.getFluidByproduct(inv[0]), !worldObj.isRemote);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.working = tag.getBoolean("working");
		this.time = tag.getInteger("time");
		this.timeMax = tag.getInteger("timeMax");
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
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		this.tank.writeToNBT(tag);
		tag.setBoolean("working", this.working);
		tag.setInteger("time", this.time);
		tag.setInteger("timeMax", this.timeMax);
	}

	@Override
	public double getOfferedEnergy() {
		return working ? super.getOfferedEnergy() : 0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 1 ? new int[] { 0 } : null; // fuel slot
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return side == 1;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return side == 0 && slot == 2;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection direction) {
		return new FluidTankInfo[] { tank.getInfo() };
	}

	@Override
	public int fill(ForgeDirection direction, FluidStack resource, boolean doFill) {
		return this.tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
			return null;
		}
		return this.tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection direction, int maxDrain, boolean doDrain) {
		return this.tank.drain(maxDrain, doDrain);
	}

}
