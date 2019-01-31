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

package frogcraftrebirth.common.lib.tile;

import frogcraftrebirth.common.network.IFrogNetworkObject;
import frogcraftrebirth.common.network.NetworkHandler;
import frogcraftrebirth.common.network.PacketFrog00TileUpdate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class TileFrog extends TileEntity implements IFrogNetworkObject {

	/**
	 * @deprecated NetworkHandler will be removed; use {@link #syncToTrackingClients()} instead.
	 * @param tile the tile entity to be synced
	 */
	@Deprecated
	protected final void sendTileUpdatePacket(TileFrog tile) {
		NetworkHandler.FROG_NETWORK.sendToAll(new PacketFrog00TileUpdate(tile));
	}

	protected final void syncToTrackingClients() {
		if (!this.world.isRemote) {
			SPacketUpdateTileEntity packet = this.getUpdatePacket();
			PlayerChunkMapEntry trackingEntry = ((WorldServer)this.world).getPlayerChunkMap().getEntry(this.pos.getX() >> 4, this.pos.getZ() >> 4);
			if (trackingEntry != null) {
				for (EntityPlayerMP player : trackingEntry.getWatchingPlayers()) {
					player.connection.sendPacket(packet);
				}
			}
		}
	}

	@Override
	public final SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, -1, this.writePacketData(new NBTTagCompound()));
	}

	@Override
	public final void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet) {
		this.readPacketData(packet.getNbtCompound());
	}

	protected void readPacketData(NBTTagCompound data) {
		// Default to no-op
	}

	protected NBTTagCompound writePacketData(NBTTagCompound data) {
		return data;
	}

	/**
	 * Called when {@link Block#breakBlock(World, BlockPos, IBlockState)} is called
	 *
	 * @param worldIn the World instance
	 * @param pos     the position
	 * @param state   the current BlockState, use this one instead of querying via {@link TileEntity#getWorld()}
	 */
	public abstract void onBlockDestroyed(World worldIn, BlockPos pos, IBlockState state);
}
