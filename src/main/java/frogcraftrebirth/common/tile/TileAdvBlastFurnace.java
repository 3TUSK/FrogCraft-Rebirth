package frogcraftrebirth.common.tile;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputFluidStack;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputItemStack;
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
import net.minecraftforge.fluids.FluidStack;
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
	private boolean working = false;
	private IAdvBlastFurnaceRecipe recipe;

	@Override
	public boolean isWorking() {
		return working;
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
					} else {
						this.progress = 0;
						this.working = false;
						return;
					}
				}
				progress++;
				if (progress >= recipe.getTime()) {
					this.heat -= 500; // TODO Consume some heat?
					this.doSmelting();
					this.working = false;
					this.progress = 0;
					this.recipe = null;
				}
			} else {
				TileEntity tile = getWorld().getTileEntity(getPos().down());
				if (tile instanceof IHeatSource) {
					this.heat += ((IHeatSource)tile).requestHeat(EnumFacing.UP, 10);
				}
			}
		}
	}

	private boolean checkRecipe(@Nullable IAdvBlastFurnaceRecipe recipe) {
		if (recipe != null && recipe.getInput().matches(input.getStackInSlot(0)) && recipe.getInputSecondary().matches(input.getStackInSlot(1))) {
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
		input.extractItem(0, recipe.getInput().getSize(), false);
		input.extractItem(1, recipe.getInputSecondary().getSize(), false);
		inputFluid.drain(recipe.getInputFluid(), true);
		ItemStack remainder = output.insertItem(0, recipe.getOutput(), false);
		if (!remainder.isEmpty()) {
			ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
		}
		ItemStack remainderByproduct = output.insertItem(1, recipe.getByproduct(), false);
		if (!remainderByproduct.isEmpty()) {
			ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainderByproduct);
		}
	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {

	}

	@Override
	public void readPacketData(DataInputStream input) throws IOException {

	}

	@Override
	public ContainerTileFrog<? extends TileFrog> getGuiContainer(World world, EntityPlayer player) {
		return null;
	}

	@Override
	public GuiTileFrog<? extends TileFrog, ? extends ContainerTileFrog<? extends TileFrog>> getGui(World world, EntityPlayer player) {
		return null;
	}
}
