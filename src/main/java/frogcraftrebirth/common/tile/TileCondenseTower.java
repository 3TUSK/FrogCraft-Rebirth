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
	private Set<ICondenseTowerOutputHatch> outputs = new LinkedHashSet<ICondenseTowerOutputHatch>();
	private Set<ICondenseTowerPart> structures = new HashSet<ICondenseTowerPart>();
	private ICondenseTowerRecipe recipe;
	public int process, processMax;
	private boolean working;
	private boolean requireRefresh;
	
	public TileCondenseTower() {
		super(3, 10000);
	}
	
	public boolean isWorking() {
		return working;
	}
	
	@Override
	public boolean isCompleted() {
		return structures.size() > 2 && outputs.size() > 0;
	}
	
	@Override
	public void update() {
		if (getWorld().isRemote) {
			if (requireRefresh) {
				getWorld().markBlockRangeForRenderUpdate(getPos(), getPos());
				requireRefresh = false;
			}
			return;
		}
		super.update();
			
		if (inv.getStackInSlot(INPUT_F) != null) {
			if (inv.getStackInSlot(INPUT_F).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				ItemStack result = FluidUtil.tryEmptyContainer(inv.extractItem(INPUT_F, 1, true), tank, 1000, null, true);
				if (result != null && result.stackSize > 0) {
					inv.extractItem(INPUT_F, 1, false);
					ItemStack remainder = inv.insertItem(OUTPUT_F, result, false);
					if (remainder != null && remainder.stackSize > 0)
						ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
				}
			}
		}
		
		if (!this.isCompleted()) {
			onDestruct(this);
			return;
		}
		
		if (recipe == null) {
			recipe = FrogAPI.managerCT.<FluidStack>getRecipe(tank.getFluid());
			if (checkRecipe(recipe)) {
				processMax = recipe.getTime();
				process = 0;
				working = true;
				this.requireRefresh = true;
			} else {
				working = false; // Put working = false here, so that debug screen won't blink (hopefully)
				this.markDirty();
				this.sendTileUpdatePacket(this);
				this.requireRefresh = true;
				return;
			}
		}
		
		if (charge >= recipe.getEnergyPerTick()) {
			charge -= recipe.getEnergyPerTick();
			process++;
		}
		
		if (charge < 0)
			charge = 0;
		
		if (process == processMax) {
			this.tank.drain(recipe.getInput().amount, true);
			Set<FluidStack> outputs = recipe.getOutput();
			outputs.forEach(fluid -> this.outputs.forEach(output -> {
					if (output.canInject(fluid)) {
						output.inject(fluid.copy(), true);
						return;
					}
			}));
			process = 0;
			processMax = 0;
			recipe = null;
		}
		this.markDirty();
		this.sendTileUpdatePacket(this);
		this.requireRefresh = true;
	}
	
	@Override
	public void behave() {
		
	}
	
	@Override
	public void onPartAttached(ICondenseTowerPart part) {
		if (part instanceof ICondenseTowerOutputHatch) {
			this.outputs.add((ICondenseTowerOutputHatch)part);
		} else {
			this.structures.add(part);
		}
	}
	
	@Override
	public void onPartRemoved(ICondenseTowerPart part) {
		this.outputs.remove(part); //Let's see if someone will let this mess up
		this.structures.remove(part);
	}
	
	@Override
	public void onConstruct(ICondenseTowerCore core) {
		
	}
	
	@Override
	public void onDestruct(ICondenseTowerCore core) {
		outputs.forEach(output -> output.onDestruct(core));
		structures.forEach(part -> part.onDestruct(core));
		this.outputs.clear();
		this.structures.clear();
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
		this.process = input.readInt();
		this.processMax = input.readInt();
		this.working = input.readBoolean();
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		tank.writePacketData(output);
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
