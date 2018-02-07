/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
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

import static frogcraftrebirth.common.network.NetworkHandler.FROG_NETWORK;

import frogcraftrebirth.common.network.IFrogNetworkObject;
import frogcraftrebirth.common.network.PacketFrog00TileUpdate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TileFrog extends TileEntity implements IFrogNetworkObject {

	protected final void sendTileUpdatePacket(TileFrog tile) {
		FROG_NETWORK.sendToAll(new PacketFrog00TileUpdate(tile));
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
