package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.api.tile.ICondenseTowerOutputHatch;
import frogcraftrebirth.api.tile.ICondenseTowerPart;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

public class TileCondenseTower extends TileEnergySink implements ICondenseTowerCore {
	
	public final ItemStackHandler inv = new ItemStackHandler(2);
	public final FrogFluidTank tank = new FrogFluidTank(8000);
	private boolean structureCompletedOnLastTick = false;
	private ArrayList<ICondenseTowerOutputHatch> outputs = new ArrayList<ICondenseTowerOutputHatch>();
	private ArrayList<ICondenseTowerPart> structures = new ArrayList<ICondenseTowerPart>();
	
	public TileCondenseTower() {
		super(3, 10000);
	}
	
	@Override
	public boolean checkStructure() {
		TileEntity tile;
		tile = worldObj.getTileEntity(getPos().up(1));
		if (tile instanceof ICondenseTowerPart && !((ICondenseTowerPart)tile).isFunctional()) {
			((ICondenseTowerPart)tile).onConstruct(this);
			this.registerSturcture((ICondenseTowerPart)tile);
		} else {
			return false;
		}
		tile = worldObj.getTileEntity(getPos().up(2));
		if (tile instanceof ICondenseTowerPart && !((ICondenseTowerPart)tile).isFunctional()) {
			((ICondenseTowerPart)tile).onConstruct(this);
			this.registerSturcture((ICondenseTowerPart)tile);
		} else {
			return false;
		}
		for (int i = 3; i < 7; i++) {
			tile = worldObj.getTileEntity(this.getPos().up(i));
			if (tile instanceof ICondenseTowerOutputHatch) {
				((ICondenseTowerOutputHatch)tile).onConstruct(this);
				this.registerOutputHatch((ICondenseTowerOutputHatch)tile);
				continue;
			} else
				return false;
		}
		return true;
	}
	
	@Override
	public boolean isCompleted() {
		return this.structureCompletedOnLastTick;
	}
	
	@Override
	public void update() {
		if (worldObj.isRemote) 
			return;
		super.update();
		
		boolean check = checkStructure();
		if (structureCompletedOnLastTick && !check) {
			onDestruct(this);
			structureCompletedOnLastTick = false;
			return;
		} else if (check) {
			onConstruct(this);
			structureCompletedOnLastTick = true;
		}
			
		if (tank.getFluid() == null)
			return;
		
		ICondenseTowerRecipe recipe = FrogAPI.managerCT.<FluidStack>getRecipe(tank.getFluid());
		if (checkRecipe(recipe)) {
			this.tank.drain(recipe.getInput().amount, !worldObj.isRemote);
		} else 
			return;
		
		charge -= 500;
		
		java.util.Set<FluidStack> outputs = recipe.getOutput();
		for (FluidStack fluid : outputs) {
			for (ICondenseTowerOutputHatch output : this.outputs) {
				if (output.canInject(fluid)) {
					output.inject(fluid, !worldObj.isRemote);
					break;
				}
			}
		}
		
		this.markDirty();
		this.sendTileUpdatePacket(this);
	}
	
	@Override
	public void behave() {
		
	}
	
	@Override
	public void onConstruct(ICondenseTowerCore core) {
		
	}
	
	@Override
	public void onDestruct(ICondenseTowerCore core) {
		for (ICondenseTowerOutputHatch output : outputs) {
			output.onDestruct(core);
		}
	
		for (ICondenseTowerPart part : structures) {
			part.onDestruct(core);
		}
		this.outputs.clear();
		this.structures.clear();
	}

	@Override
	public ICondenseTowerCore getMainBlock() {
		return this;
	}

	private boolean checkRecipe(ICondenseTowerRecipe aRecipe) {
		if (aRecipe == null)
			return false;
		for (FluidStack fluid : aRecipe.getOutput()) {
			boolean checkPass = false;
			for (ICondenseTowerOutputHatch output : outputs) {
				if (output.canInject(fluid)) {
					checkPass = true;
					break;
				}
			}
			if (!checkPass)
				return false;
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		tank.readPacketData(input);
		this.charge = input.readInt();
		this.structureCompletedOnLastTick = input.readBoolean();
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		tank.writePacketData(output);
		output.writeInt(charge);
		output.writeBoolean(structureCompletedOnLastTick);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.inv.deserializeNBT(tag.getCompoundTag("inv"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		this.tank.writeToNBT(tag);
		tag.setTag("inv", inv.serializeNBT());
		return super.writeToNBT(tag);
	}

	//TODO: ItemHandler
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		else return super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T)tank;
		else return super.getCapability(capability, facing);
	}
	
	public boolean registerOutputHatch(ICondenseTowerOutputHatch output) {
		return output != null ? outputs.add(output) : false;
	}
	
	public boolean registerSturcture(ICondenseTowerPart structure) {
		return structure != null ? structures.add(structure) : false;
	}
}
