package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.api.tile.ICondenseTowerOutputHatch;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.block.BlockFrogWrenchable;
import frogcraftrebirth.common.lib.tile.TileFrog;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileFluidOutputHatch extends TileFrog implements ICondenseTowerOutputHatch {

	private ICondenseTowerCore mainBlock;
	
	public final FrogFluidTank tank = new FrogFluidTank(8000);

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		tank.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tank.writeToNBT(tag);
		return super.writeToNBT(tag);
	}

	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		tank.readPacketData(input);
	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		tank.writePacketData(output);
	}
	
	@Override
	public void behave() {
		
	}
	
	@Override
	public void onConstruct(ICondenseTowerCore core) {
		this.mainBlock = core;
	}

	@Override
	public void onDestruct(ICondenseTowerCore core) {
		this.mainBlock = null;
	}
	
	public ICondenseTowerCore getMainBlock() {
		return mainBlock;
	}

	@Override
	public boolean canInject(FluidStack stack) {
		return stack != null ? tank.getFluid() == null ? true : this.tank.fill(stack, false) != 0 : false;
	}

	@Override
	public void inject(FluidStack stack, boolean simluated) {
		this.tank.fill(stack, simluated);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			EnumFacing currectFacing = worldObj.getBlockState(getPos()).getValue(BlockFrogWrenchable.FACING_HORIZONTAL);
			return currectFacing == facing;
		}
		
		return super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			EnumFacing currectFacing = worldObj.getBlockState(getPos()).getValue(BlockFrogWrenchable.FACING_HORIZONTAL);
			return currectFacing == facing ? (T)tank : super.getCapability(capability, currectFacing);
		}
			
		return super.getCapability(capability, facing);
	}

}
