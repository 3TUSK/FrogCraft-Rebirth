/*
 * Copyright (c) 2015 - 2019 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.api.tile.ICondenseTowerOutputHatch;
import frogcraftrebirth.api.tile.ICondenseTowerPart;
import frogcraftrebirth.client.gui.GuiCondenseTower;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputFluidStack;
import frogcraftrebirth.common.lib.tile.TileEnergySink;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileCondenseTower extends TileEnergySink implements ICondenseTowerCore, IHasGui, IHasWork, ITickable {
	
	private static final int INPUT_F = 0, OUTPUT_F = 1;
	
	private final ItemStackHandler inv = new ItemStackHandler(2);
	public final FrogFluidTank tank = new FrogFluidTank(8000);
	private boolean previousStructureCompleteness = false;
	private final Set<ICondenseTowerOutputHatch> outputs = Collections.newSetFromMap(new IdentityHashMap<>());
	private ICondenseTowerRecipe recipe;
	private int process;
	private int processMax;
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
		return previousStructureCompleteness ? outputs.size() > 0 : checkStructure();
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
			
		if (!inv.getStackInSlot(INPUT_F).isEmpty()) {
			IFluidHandlerItem fluidIn = FluidUtil.getFluidHandler(inv.extractItem(INPUT_F, 1, true));
			if (fluidIn != null) {
				FluidStack transferResult = FluidUtil.tryFluidTransfer(this.tank, fluidIn, Integer.MAX_VALUE, true);
				if (transferResult != null) {
					inv.extractItem(INPUT_F, 1, false);
					ItemStack remainder = inv.insertItem(OUTPUT_F, fluidIn.getContainer(), false);
					if (!remainder.isEmpty() && remainder.getCount()> 0) {
						ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
					}
				}
			}
		}
		
		if (!this.isCompleted() || tank.getFluid() == null) {
			return;
		}
		
		if (recipe == null) {
			recipe = FrogAPI.managerCT.getRecipe(new FrogRecipeInputFluidStack(tank.getFluid()));
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
			recipe.getOutput().forEach(fluid -> {
				for (ICondenseTowerOutputHatch output : this.outputs) {
					if (output.canInject(fluid)) {
						output.inject(fluid.copy(), true);
						return;
					}
				}
			});
			process = 0;
			processMax = 0;
			recipe = null;
		}
		this.markDirty();
		this.sendTileUpdatePacket(this);
		this.requireRefresh = true;
	}

	@Override
	public void onPartAttached(ICondenseTowerPart part) {
		if (part instanceof ICondenseTowerOutputHatch) {
			this.registerOutputHatch((ICondenseTowerOutputHatch)part);
		}
		part.setMainBlock(this);
	}
	
	@Override
	public void onPartRemoved(ICondenseTowerPart part) {
		this.previousStructureCompleteness = checkStructure();
	}
	
	@Override
	public void onDestruction() {
		outputs.forEach(output -> output.setMainBlock(null));
		this.outputs.clear();
		TileEntity structure1 = getWorld().getTileEntity(getPos().up(1));
		TileEntity structure2 = getWorld().getTileEntity(getPos().up(2));
		if (structure1 instanceof ICondenseTowerPart) {
			((ICondenseTowerPart)structure1).setMainBlock(null);
		}
		if (structure2 instanceof ICondenseTowerPart) {
			((ICondenseTowerPart)structure2).setMainBlock(null);
		}
	}

	private boolean checkStructure() {
		this.outputs.clear();
		TileEntity structure1 = getWorld().getTileEntity(getPos().up(1));
		TileEntity structure2 = getWorld().getTileEntity(getPos().up(2));
		if (isValidStructure(structure1) && isValidStructure(structure2)) {
			BlockPos.MutableBlockPos posTmp = new BlockPos.MutableBlockPos(this.getPos().up(3));
			TileEntity outletCheck;
			while ((outletCheck = world.getTileEntity(posTmp)) != null) {
				if (outletCheck instanceof ICondenseTowerOutputHatch) {
					this.registerOutputHatch((ICondenseTowerOutputHatch) outletCheck);
					posTmp.move(EnumFacing.UP);
				} else {
					break;
				}
			}
			((ICondenseTowerPart)structure1).setMainBlock(this);
			((ICondenseTowerPart)structure2).setMainBlock(this);
			return this.outputs.size() > 0;
		} else {
			return false;
		}
	}

	private boolean isValidStructure(@Nullable TileEntity tileEntity) {
		return tileEntity instanceof ICondenseTowerPart && !((ICondenseTowerPart) tileEntity).isFunctional();
	}

	private boolean checkRecipe(@Nullable ICondenseTowerRecipe aRecipe) {
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

	@Override
	public void onLoad() {
		super.onLoad();
		this.previousStructureCompleteness = checkStructure();
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
		FluidStack inputFluid = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("recipe"));
		if (inputFluid != null) {
			this.recipe = FrogAPI.managerCT.getRecipe(new FrogRecipeInputFluidStack(inputFluid));
		}
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
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ?
				CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank) : super.getCapability(capability, facing);
	}
	
	private void registerOutputHatch(@Nullable ICondenseTowerOutputHatch output) {
		if (output != null) {
			outputs.add(output);
		}
	}

	@Override
	public void onBlockDestroyed(World worldIn, BlockPos pos, IBlockState state) {
		ItemUtil.dropInventoryItems(worldIn, pos, inv);
		this.onDestruction();
	}

	@Override
	public ContainerTileFrog getGuiContainer(World world, EntityPlayer player) {
		return ContainerTileFrog.Builder.from(this)
				.withStandardSlot(inv, 0, 113, 21)
				.withOutputSlot(inv, 1, 113, 56)
				.withPlayerInventory(player.inventory)
				.build();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiTileFrog<? extends TileFrog> getGui(World world, EntityPlayer player) {
		return new GuiCondenseTower(this.getGuiContainer(world, player), this);
	}
}
