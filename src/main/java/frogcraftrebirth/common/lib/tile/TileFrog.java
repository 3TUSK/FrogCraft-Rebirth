package frogcraftrebirth.common.lib.tile;

import static frogcraftrebirth.common.network.NetworkHandler.FROG_NETWORK;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import frogcraftrebirth.api.IFrogNetworkObject;
import frogcraftrebirth.common.network.PacketFrog00TileUpdate;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.tile.IWrenchable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public abstract class TileFrog extends TileEntity implements IWrenchable, IFrogNetworkObject {

	protected short facing, prevFacing = facing;
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.facing = tag.getShort("facing");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setShort("facing", facing);
		return super.writeToNBT(tag);
	}

	@Override
	public EnumFacing getFacing(World world, BlockPos pos) {
		return EnumFacing.VALUES[(int)facing];
	}

	@Override
	public boolean setFacing(World world, BlockPos pos, EnumFacing newDirection, EntityPlayer player) {
		if (this instanceof IEnergyTile)
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent((IEnergyTile)this));
		//this.facing = facing;
		this.sendTileUpdatePacket(this);
		this.prevFacing = facing;
		if (this instanceof IEnergyTile)
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent((IEnergyTile)this));
		return true;
	}

	@Override
	public boolean wrenchCanRemove(World world, BlockPos pos, EntityPlayer player) {
		return true;
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te, EntityPlayer player, int fortune) {
		return java.util.Arrays.<ItemStack>asList(new ItemStack(this.getBlockType(), 1, state.getBlock().getMetaFromState(state)));
	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		output.writeShort(this.facing);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		this.facing = input.readShort();
	}
	
	protected void sendTileUpdatePacket(TileFrog tile) {
		FROG_NETWORK.sendToAllAround(new PacketFrog00TileUpdate(tile), this.worldObj.provider.getDimensionType().getId(), tile.pos.getX(), tile.pos.getY(), tile.pos.getZ(), 2);
	}

}
