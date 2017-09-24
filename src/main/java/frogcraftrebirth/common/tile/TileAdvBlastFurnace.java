package frogcraftrebirth.common.tile;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputFluidStack;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputItemStack;
import frogcraftrebirth.common.lib.tile.TileFrog;
import ic2.api.energy.tile.IHeatSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TileAdvBlastFurnace extends TileFrog implements IHasGui, IHasWork, ITickable {

	/*
	 * TODO ItemHandler for input, output; FluidHandler for input, shield
	 */
	private int heat;
	private int progress;
	private EnumFacing heatInput; // TODO a local variable or not?
	private IAdvBlastFurnaceRecipe recipe;

	@Override
	public boolean isWorking() {
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		//TODO properly implement this
		NBTTagCompound r = tag.getCompoundTag("recipe");
		ItemStack itemStack = new ItemStack(r.getCompoundTag("item"));
		FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(r.getCompoundTag("fluid"));
		this.recipe = FrogAPI.managerABF.getRecipe(new FrogRecipeInputItemStack(itemStack), new FrogRecipeInputFluidStack(fluidStack));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		NBTTagCompound r = new NBTTagCompound();
		r.setTag("item", recipe.getInput().serializeNBT());
		r.setTag("fluid", recipe.getInputFluid().writeToNBT(new NBTTagCompound()));
		tag.setTag("recipe", r);
		return super.writeToNBT(tag);
	}

	@Override
	public void update() {
		if (getWorld().isRemote) {
			getWorld().markBlockRangeForRenderUpdate(getPos(), getPos());
		} else {
			heatInput = getCurrentHeatInputFacing();
			if (heat >= 1000) {
				heat = 1000; // Normalize

				recipe = FrogAPI.managerABF.getRecipe();
				if (recipe != null) {
					System.out.println("Hey you know this is placeholder right?");
				} else {
					progress = 0;
					return;
				}
				progress++;
				if (progress >= recipe.getTime()) {
					System.out.println("Hey you know it means the smelting is done right?");
				}
			} else {
				TileEntity tile = getWorld().getTileEntity(getPos().offset(heatInput));
				if (tile instanceof IHeatSource) {
					this.heat += ((IHeatSource)tile).requestHeat(heatInput.getOpposite(), 20);
				}
			}
		}
	}

	private EnumFacing getCurrentHeatInputFacing() { //Placeholder, implemented in arbitrary manner
		return EnumFacing.VALUES[(int)(Math.random() * 1000) >> 8];
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
