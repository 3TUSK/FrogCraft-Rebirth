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

package frogcraftrebirth.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.common.lib.tile.TileFrog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketFrog00TileUpdate implements IFrogPacket {
	
	private TileFrog tile;
	
	PacketFrog00TileUpdate() {}
	
	public PacketFrog00TileUpdate(TileFrog tile) {
		this.tile = tile;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(PACKET_TILE);
		output.writeInt(tile.getPos().getX());
		output.writeInt(tile.getPos().getY());
		output.writeInt(tile.getPos().getZ());
		tile.writePacketData(output);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void readData(DataInputStream input) throws IOException {
		BlockPos aPos = new BlockPos(input.readInt(), input.readInt(), input.readInt());
		WorldClient world = Minecraft.getMinecraft().world;
		if (world == null) {
			return; // BTM Moon: you will understand that this is possible during joining server
		}

		TileEntity tile = world.getTileEntity(aPos);
		
		if (tile instanceof IFrogNetworkObject)
			((IFrogNetworkObject) tile).readPacketData(input);
		
	}

}
