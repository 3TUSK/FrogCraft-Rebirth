/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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
import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import frogcraftrebirth.client.gui.GuiAdvBlastFurnace;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerAdvBlastFurnace;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.recipes.IterableFrogRecipeInputsBackedByIItemHandler;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.lib.util.ItemUtil;
import ic2.api.energy.tile.IHeatSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TileAdvBlastFurnace extends TileFrog implements IHasGui, IHasWork, ITickable {

	public final ItemStackHandler input = new ItemStackHandler(2);
	public final ItemStackHandler output = new ItemStackHandler(2);
	public final FrogFluidTank inputFluid = new FrogFluidTank(8000, "input_fluid");
	public final FrogFluidTank shieldGas = new FrogFluidTank(1000, "shield_gas");
	private int heat;
	private int progress;
	private int progressMax;
	private boolean working = false;
	private IAdvBlastFurnaceRecipe recipe;

	@Override
	public boolean isWorking() {
		return working;
	}

	public double getProgressRatio() {
		return progressMax == 0 ? 0D : (double)progress / progressMax;
	}

	public double getHeatFillingRatio() {
		return (double)heat / 1000;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		input.deserializeNBT(tag.getCompoundTag("input"));
		output.deserializeNBT(tag.getCompoundTag("output"));
		inputFluid.readFromNBT(tag);
		shieldGas.readFromNBT(tag);
		this.heat = tag.getInteger("heat");
		this.progress = tag.getInteger("progress");
		this.progressMax = tag.getInteger("progressMax");
		this.working = tag.getBoolean("working");
		this.recipe = FrogAPI.managerABF.getRecipe(new IterableFrogRecipeInputsBackedByIItemHandler(input));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setTag("input", input.serializeNBT());
		tag.setTag("output", output.serializeNBT());
		inputFluid.writeToNBT(tag);
		shieldGas.writeToNBT(tag);
		tag.setInteger("heat", heat);
		tag.setInteger("progress", progress);
		tag.setInteger("progressMax", progressMax);
		tag.setBoolean("working", working);
		return super.writeToNBT(tag);
	}

	@Override
	public void update() {
		if (getWorld().isRemote) {
			getWorld().markBlockRangeForRenderUpdate(getPos(), getPos());
		} else {
			if (heat >= 1000) {
				heat = 1000; // Normalize
				if (!working || this.recipe == null) {
					IAdvBlastFurnaceRecipe recipe = FrogAPI.managerABF.getRecipe(new IterableFrogRecipeInputsBackedByIItemHandler(input));
					if (checkRecipe(recipe)) {
						this.recipe = recipe;
						this.working = true;
						this.progressMax = recipe.getTime();
						input.extractItem(0, recipe.getInput().getSize(), false);
						input.extractItem(1, recipe.getInputSecondary().getSize(), false);
						inputFluid.drain(recipe.getInputFluid(), true);
					} else {
						this.progress = 0;
						this.working = false;
						return;
					}
				}
				progress++;
				if (progress >= progressMax) {
					this.heat -= recipe.getHeatConsumption();
					this.doSmelting();
					this.working = false;
					this.progress = 0;
					this.progressMax = 0;
					this.recipe = null;
				}
			} else {
				TileEntity tile = getWorld().getTileEntity(getPos().down());
				if (tile instanceof IHeatSource) {
					this.heat += ((IHeatSource)tile).drawHeat(EnumFacing.UP, 10, false);
				}
			}
		}
	}

	private boolean checkRecipe(@Nullable IAdvBlastFurnaceRecipe recipe) {
		if (recipe != null && recipe.getInput().matches(input.getStackInSlot(0)) && (recipe.getInputSecondary().isEmpty() || recipe.getInputSecondary().matches(input.getStackInSlot(1)))) {
			if (shieldGas.getFluid() == null) {
				return recipe.getShieldGas() == null;
			} else {
				return shieldGas.getFluid().getFluid() == recipe.getShieldGas();
			}
		} else {
			return false;
		}
	}

	private void doSmelting() {
		ItemStack remainder = output.insertItem(0, recipe.getOutput().copy(), false);
		if (!remainder.isEmpty()) {
			ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
		}
		ItemStack remainderByproduct = output.insertItem(1, recipe.getByproduct().copy(), false);
		if (!remainderByproduct.isEmpty()) {
			ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainderByproduct);
		}
	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		this.inputFluid.writePacketData(output);
		this.shieldGas.writePacketData(output);
		output.writeInt(heat);
		output.writeInt(progress);
		output.writeInt(progressMax);
		output.writeBoolean(working);
	}

	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		this.inputFluid.readPacketData(input);
		this.shieldGas.readPacketData(input);
		this.heat = input.readInt();
		this.progress = input.readInt();
		this.progressMax = input.readInt();
		this.working = input.readBoolean();
	}

	@Override
	public ContainerTileFrog<? extends TileFrog> getGuiContainer(World world, EntityPlayer player) {
		return new ContainerAdvBlastFurnace(player.inventory, this);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiTileFrog<? extends TileFrog, ? extends ContainerTileFrog<? extends TileFrog>> getGui(World world, EntityPlayer player) {
		return new GuiAdvBlastFurnace(player.inventory, this);
	}
}
