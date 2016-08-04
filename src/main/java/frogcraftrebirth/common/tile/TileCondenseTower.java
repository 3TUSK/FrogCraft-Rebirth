package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.api.tile.ICondenseTowerOutputHatch;
import frogcraftrebirth.api.tile.ICondenseTowerPart;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileEnergySink;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

public class TileCondenseTower extends TileEnergySink implements ICondenseTowerCore, IHasWork {
	
	private static final int INPUT_F = 0, OUTPUT_F = 1;
	
	public final ItemStackHandler inv = new ItemStackHandler(2);
	public final FrogFluidTank tank = new FrogFluidTank(8000);
	private boolean structureCompletedOnLastTick = false;
	private Set<ICondenseTowerOutputHatch> outputs = new LinkedHashSet<ICondenseTowerOutputHatch>();
	private Set<ICondenseTowerPart> structures = new HashSet<ICondenseTowerPart>();
	private ICondenseTowerRecipe recipe;
	public int process, processMax;
	private boolean working;
	
	public TileCondenseTower() {
		super(3, 10000);
	}
	
	public boolean isWorking() {
		return working;
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
			
		if (inv.getStackInSlot(INPUT_F) != null) {
			if (inv.getStackInSlot(INPUT_F).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				ItemStack result = FluidUtil.tryEmptyContainer(inv.extractItem(INPUT_F, 1, true), tank, 1000, null, true);
				if (result != null && result.stackSize > 0) {
					inv.extractItem(INPUT_F, 1, false);
					ItemStack remainder = inv.insertItem(OUTPUT_F, result, false);
					if (remainder != null && remainder.stackSize > 0)
						ItemUtil.dropItemStackAsEntityInsanely(worldObj, getPos(), remainder);
				}
			}
		}
		
		if (recipe == null) {
			recipe = FrogAPI.managerCT.<FluidStack>getRecipe(tank.getFluid());
			if (checkRecipe(recipe)) {
				processMax = recipe.getTime();
				process = 0;
				working = true;
			} else {
				this.markDirty();
				this.sendTileUpdatePacket(this);
				return;
			}
		}
		
		charge -= recipe.getEnergyPerTick();
		process++;
		
		if (process == processMax) {
			this.tank.drain(recipe.getInput().amount, true);
			Set<FluidStack> outputs = recipe.getOutput();
			for (FluidStack fluid : outputs) {
				for (ICondenseTowerOutputHatch output : this.outputs) {
					if (output.canInject(fluid)) {
						output.inject(fluid.copy(), true);
						break;
					}
				}
			}
			process = 0;
			processMax = 0;
			recipe = null;
			working = false;
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
				if (output.canInject(fluid.copy())) {
					checkPass = true;
					break;
				}
			}
			if (!checkPass) {
				return false;
			}
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
		tank.readPacketData(input);
		this.structureCompletedOnLastTick = input.readBoolean();
		this.process = input.readInt();
		this.processMax = input.readInt();
		this.working = input.readBoolean();
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		tank.writePacketData(output);
		output.writeBoolean(structureCompletedOnLastTick);
		output.writeInt(process);
		output.writeInt(processMax);
		output.writeBoolean(working);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.inv.deserializeNBT(tag.getCompoundTag("inv"));
		this.recipe = FrogAPI.managerCT.<FluidStack>getRecipe(FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("recipe")));
		this.working = tag.getBoolean("working");
		this.process = tag.getInteger("process");
		this.processMax = tag.getInteger("processMax");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		this.tank.writeToNBT(tag);
		tag.setTag("inv", inv.serializeNBT());
		tag.setTag("recipe", recipe != null ? recipe.getInput().copy().writeToNBT(new NBTTagCompound()) : new NBTTagCompound());
		tag.setBoolean("working", working);
		tag.setInteger("process", process);
		tag.setInteger("processMax", processMax);
		return super.writeToNBT(tag);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		else 
			return super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T)tank;
		else 
			return super.getCapability(capability, facing);
	}
	
	public boolean registerOutputHatch(ICondenseTowerOutputHatch output) {
		return output != null ? outputs.add(output) : false;
	}
	
	public boolean registerSturcture(ICondenseTowerPart structure) {
		return structure != null ? structures.add(structure) : false;
	}
}
