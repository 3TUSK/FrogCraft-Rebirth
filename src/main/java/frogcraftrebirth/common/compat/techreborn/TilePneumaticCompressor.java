/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 8:45:32 AM, Aug 5, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.techreborn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import frogcraftrebirth.api.air.IAirPump;
import frogcraftrebirth.common.lib.tile.TileEnergySink;
import frogcraftrebirth.common.lib.util.ItemUtil;
import frogcraftrebirth.common.tile.IHasWork;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.energy.tile.IMetaDelegate;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import reborncore.api.recipe.IBaseRecipeType;
import reborncore.api.recipe.RecipeHandler;
import techreborn.api.Reference;
import techreborn.tiles.multiblock.MultiblockChecker;

public class TilePneumaticCompressor extends TileEnergySink implements IHasWork, IMetaDelegate, ITickable {
	
	public final ItemStackHandler input = new ItemStackHandler();
	public final ItemStackHandler output = new ItemStackHandler(2);
	//Here, we are assuming that recipe list is unchanged after the TileEntity is created.
	public final List<IBaseRecipeType> list = RecipeHandler.getRecipeClassFromName(Reference.implosionCompressorRecipe);
	private IBaseRecipeType recipe;
	private boolean isWorking;
	private int process;
	private int processMax;
	private int power; // i.e. EU consumption per tick
	public MultiblockChecker multiblock;
	public boolean isFormed;

	protected TilePneumaticCompressor() {
		super(2, 20000);
		multiblock = new MultiblockChecker(worldObj, pos.add(0, -3, 0));
	}
	
	public boolean isWorking() {
		return isWorking;
	}
	
	@Override
	public void update() {
		if (worldObj.isRemote)
			return;
		super.update();
		
		isFormed = checkStructure();
		if (!isFormed)
			return;
		
		if (input.getStackInSlot(0) == null) {
			this.recipe = null;
			this.isWorking = false;
			sendTileUpdatePacket(this);
			return;
		}
		
		if (recipe == null) {
			for (IBaseRecipeType recipe : list) {
				if (recipe.getInputs().get(0).equals(input.getStackInSlot(0))) {
					this.recipe = recipe;
					this.isWorking = true;
					this.process = 0;
					this.processMax = recipe.tickTime();
					this.power = recipe.euPerTick();
					break;
				}	
			}
		}
		
		this.charge -= this.power;
		this.process++;
		
		if (process == processMax) {
			ItemStack remainder1 = ItemHandlerHelper.insertItemStacked(output, recipe.getOutput(0), false);
			ItemStack remainder2 = ItemHandlerHelper.insertItemStacked(output, recipe.getOutput(1), false);
			if (remainder1 != null)
				ItemUtil.dropItemStackAsEntityInsanely(worldObj, getPos(), remainder1);
			if (remainder2 != null)
				ItemUtil.dropItemStackAsEntityInsanely(worldObj, getPos(), remainder2);
			processMax = 0;
			process = 0;
			power = 0;
			recipe = null;
		}
		
		sendTileUpdatePacket(this);
		markDirty();
	}
	
	private boolean checkStructure() {
		if (!multiblock.checkCasing(1, 0, 0, MultiblockChecker.CASING_REINFORCED))
			return false;
		if (!multiblock.checkCasing(-1, 0, 0, MultiblockChecker.CASING_REINFORCED))
			return false;
		if (!multiblock.checkCasing(0, 0, -1, MultiblockChecker.CASING_REINFORCED))
			return false;
		if (!multiblock.checkCasing(0, 0, 1, MultiblockChecker.CASING_REINFORCED))
			return false;
		if (!multiblock.checkCasing(0, 0, 0, MultiblockChecker.CASING_REINFORCED))
			return false;
		if (!multiblock.checkCasing(1, 0, 1, MultiblockChecker.CASING_ADVANCED))
			return false;
		if (!multiblock.checkCasing(1, 0, -1, MultiblockChecker.CASING_ADVANCED))
			return false;
		if (!multiblock.checkCasing(-1, 0, 1, MultiblockChecker.CASING_ADVANCED))
			return false;
		if (!multiblock.checkCasing(-1, 0, 1, MultiblockChecker.CASING_ADVANCED))
			return false;
		
		if (!multiblock.checkCasing(1, 2, 0, MultiblockChecker.CASING_REINFORCED))
			return false;
		if (!multiblock.checkCasing(-1, 2, 0, MultiblockChecker.CASING_REINFORCED))
			return false;
		if (!multiblock.checkCasing(0, 2, -1, MultiblockChecker.CASING_REINFORCED))
			return false;
		if (!multiblock.checkCasing(0, 2, 1, MultiblockChecker.CASING_REINFORCED))
			return false;
		if (!multiblock.checkCasing(0, 2, 0, MultiblockChecker.CASING_REINFORCED))
			return false;
		if (!multiblock.checkCasing(1, 2, 1, MultiblockChecker.CASING_ADVANCED))
			return false;
		if (!multiblock.checkCasing(1, 2, -1, MultiblockChecker.CASING_ADVANCED))
			return false;
		if (!multiblock.checkCasing(-1, 2, 1, MultiblockChecker.CASING_ADVANCED))
			return false;
		if (!multiblock.checkCasing(-1, 2, 1, MultiblockChecker.CASING_ADVANCED))
			return false;
		
		return multiblock.checkRingYHollow(1, 1, MultiblockChecker.CASING_REINFORCED, new BlockPos(0, 1, 0));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		input.deserializeNBT(tag.getCompoundTag("input"));
		output.deserializeNBT(tag.getCompoundTag("output"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setTag("input", input.serializeNBT());
		tag.setTag("output", output.serializeNBT());
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? true : super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			switch (facing) {
				case UP:
					return (T) input;
				case NORTH:
				case EAST:
				case SOUTH:
				case WEST:
					return (T) output;
				default:
					break;
			}
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public List<IEnergyTile> getSubTiles() {
		List<IEnergyTile> list = new ArrayList<IEnergyTile>(4);
		BlockPos[] posArray = new BlockPos[] {pos.add(2, -3, 2), pos.add(-2, -3, 2), pos.add(-2, -3, -2), pos.add(2, -3, -2)};
		for (BlockPos aPos : posArray) {
			TileEntity tile = worldObj.getTileEntity(aPos);
			if (tile instanceof IEnergyTile && tile instanceof IAirPump)
				list.add((IEnergyTile)tile);
		}
		return null;
	}

}
