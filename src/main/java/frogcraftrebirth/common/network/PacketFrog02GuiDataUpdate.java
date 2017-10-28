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

package frogcraftrebirth.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.common.gui.ContainerTileFrog;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.client.FMLClientHandler;

public class PacketFrog02GuiDataUpdate implements IFrogPacket {
	
	private ContainerTileFrog<?> guiContainer;
	
	PacketFrog02GuiDataUpdate() {}
	
	public PacketFrog02GuiDataUpdate(ContainerTileFrog<?> guiContainer) {
		this.guiContainer = guiContainer;
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(PACKET_GUI);
		output.writeInt(guiContainer.windowId);
		guiContainer.writeDataForSync(output);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		int gui = input.readInt();
		EntityPlayerSP player = FMLClientHandler.instance().getClient().player;
		if (player.openContainer.windowId == gui && player.openContainer instanceof ContainerTileFrog)
			((ContainerTileFrog<?>)player.openContainer).updateContainer(input);
	}

}
