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

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.client.gui.GuiAdvChemReactor;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.MultiTypeStorage;
import frogcraftrebirth.common.lib.capability.ItemHandlerInputWrapper;
import frogcraftrebirth.common.lib.capability.ItemHandlerOutputWrapper;
import frogcraftrebirth.common.lib.capability.ItemHandlerUniversalCell;
import frogcraftrebirth.common.lib.recipes.IterableFrogRecipeInputsBackedByIItemHandler;
import frogcraftrebirth.common.lib.tile.TileEnergySink;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.*;

import javax.annotation.Nullable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TileAdvChemReactor extends TileEnergySink implements IHasGui, IHasWork, ITickable {

	private final ItemStackHandler module = new ItemStackHandler();
	//private final ItemStackHandler input = new ItemStackHandler(5);
	//private final ItemStackHandler output = new ItemStackHandler(5);
	private final MultiTypeStorage input = new MultiTypeStorage(5);
	private final MultiTypeStorage output = new MultiTypeStorage(5);
	private final ItemHandlerUniversalCell cellInput = new ItemHandlerUniversalCell();
	private final ItemHandlerUniversalCell cellOutput = new ItemHandlerUniversalCell();
	
	public int process, processMax;
	private boolean working;
	private boolean requireRefresh;
	private IAdvChemRecRecipe recipe;
	
	public TileAdvChemReactor() {
		super(2, 50000);
	}
	
	@Override
	public boolean isWorking() {
		return working;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.working = tag.getBoolean("working");
		this.process = tag.getInteger("process");
		this.processMax = tag.getInteger("processMax");
		//this.input.deserializeNBT(tag.getCompoundTag("input"));
		//this.output.deserializeNBT(tag.getCompoundTag("output"));
		this.module.deserializeNBT(tag.getCompoundTag("module"));
		this.cellInput.deserializeNBT(tag.getCompoundTag("cellInput"));
		this.cellOutput.deserializeNBT(tag.getCompoundTag("cellOutput"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setBoolean("working", this.working);
		tag.setInteger("process", this.process);
		tag.setInteger("processMax", this.processMax);
		//tag.setTag("input", this.input.serializeNBT());
		//tag.setTag("output", this.output.serializeNBT());
		tag.setTag("module", this.module.serializeNBT());
		tag.setTag("cellInput", this.cellInput.serializeNBT());
		tag.setTag("cellOutput", this.cellOutput.serializeNBT());
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
		this.process = input.readInt();
		this.processMax = input.readInt();
		this.working = input.readBoolean();
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		output.writeInt(process);
		output.writeInt(processMax);
		output.writeBoolean(working);
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
		
		if (!working || recipe == null) {
			//recipe = FrogAPI.managerACR.getRecipe(new IterableFrogRecipeInputsBackedByIItemHandler(this.input));
			
			if (checkRecipe(recipe)) {
				this.consumeIngredient(recipe.getInputs());
				this.process = 0;
				this.processMax = recipe.getTime();
				this.working = true;
				this.requireRefresh = true;
			} else {
				this.working = false;
				this.sendTileUpdatePacket(this);
				this.markDirty();
				this.requireRefresh = true;
				return;
			}
		}
		
		if (recipe != null && charge >= recipe.getEnergyRate()) {
			this.charge -= recipe.getEnergyRate();
			++process;
		}
		
		if (recipe != null && process == processMax) {
			this.produce();
			this.process = 0;
			this.processMax = 0;
			this.recipe = null;
		}
		
		this.sendTileUpdatePacket(this);
		this.markDirty();
	}

	private boolean checkRecipe(@Nullable IAdvChemRecRecipe recipe) {
		return recipe != null && this.cellInput.getCellCount() >= recipe.getRequiredCellAmount() && ItemStack.areItemsEqual(recipe.getCatalyst(), module.getStackInSlot(0));
	}

	private void consumeIngredient(Collection<IFrogRecipeInput> toBeConsumed) {
		toBeConsumed.forEach(input -> {
			for (int i = 0; i < 5; i++) {
				/*if (input.matches(this.input.getStackInSlot(i))) {
					this.input.extractItem(i, input.getSize(), false);
					return;
				}*/
			}
		});
		if (recipe.getRequiredCellAmount() > 0) {
			cellInput.decrease(recipe.getRequiredCellAmount());
		}
	}
	
	private final List<ItemStack> dropCache = new ArrayList<>();
	private void produce() {
		//recipe.getOutputs().forEach(itemStack -> dropCache.add(ItemHandlerHelper.insertItemStacked(output, itemStack.copy(), false)));
		dropCache.removeIf(ItemUtil.EMPTY_PREDICATE);
		dropCache.forEach(stack -> ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), stack));
		dropCache.clear();
		
		if (recipe.getProducedCellAmount() > 0) {
			if (cellOutput.getCellCount() <= 0) {
				cellOutput.increase(recipe.getProducedCellAmount());
			} else {
				ItemStack cell = cellOutput.getStackInSlot(0).copy();
				cell.setCount(recipe.getProducedCellAmount());
				ItemStack remain = cellOutput.insertItem(0, cell, false);
				if (!remain.isEmpty())
					ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remain);
			}
		}
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing != null) {
			switch (facing) {
				case UP:
					return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemHandlerInputWrapper(input.createHandler(IItemHandler.class)));
				case DOWN:
					return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemHandlerOutputWrapper(output.createHandler(IItemHandler.class)));
				case NORTH:
				case EAST:
					return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemHandlerInputWrapper(cellInput));
				case SOUTH:
				case WEST:
					return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemHandlerOutputWrapper(cellOutput));
			}
		}	
		return super.getCapability(capability, facing);
	}

	@Override
	public void onBlockDestroyed(World worldIn, BlockPos pos, IBlockState state) {
		ItemUtil.dropInventoryItems(worldIn, pos, module, cellInput, cellOutput);
		ItemUtil.dropInventoryItems(worldIn, pos, input.createHandler(IItemHandler.class), output.createHandler(IItemHandler.class));
	}

	@Override
	public ContainerTileFrog getGuiContainer(World world, EntityPlayer player) {
		final IItemHandlerModifiable input = this.input.createHandler(IItemHandlerModifiable.class);
		final IItemHandlerModifiable output = this.output.createHandler(IItemHandlerModifiable.class);
		return ContainerTileFrog.Builder.from(this)
				.withStandardSlot(module, 0, 147, 52)
				.withStandardSlot(cellInput, 0, 12, 22)
				.withOutputSlot(cellOutput, 0, 12, 52)
				.withStandardSlot(input, 0, 40, 22)
				.withStandardSlot(input, 1, 60, 22)
				.withStandardSlot(input, 2, 80, 22)
				.withStandardSlot(input, 3, 100, 22)
				.withStandardSlot(input, 4, 120, 22)
				.withOutputSlot(output, 0, 40, 52)
				.withOutputSlot(output, 1, 60, 52)
				.withOutputSlot(output, 2, 80, 52)
				.withOutputSlot(output, 3, 100, 52)
				.withOutputSlot(output, 4, 120, 52)
				.withPlayerInventory(player.inventory)
				.build();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiTileFrog<? extends TileFrog> getGui(World world, EntityPlayer player) {
		return new GuiAdvChemReactor(this.getGuiContainer(world, player), this);
	}

}
