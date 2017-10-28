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

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import frogcraftrebirth.api.FrogAPI;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum NetworkHandler {
	
	FROG_NETWORK;
	
	private final FMLEventChannel frogChannel;
	
	NetworkHandler() {
		(frogChannel = NetworkRegistry.INSTANCE.newEventDrivenChannel(FrogAPI.MODID)).register(this);
	}
	
	public static void init() {
		FrogAPI.FROG_LOG.debug("Network handler for FrogCraft: Rebirth has been set up."); // Trigger class loading
	}
	
	@SubscribeEvent
	public void serverPacket(ServerCustomPacketEvent event) {
		ByteBufInputStream input = new ByteBufInputStream(event.getPacket().payload());
		decodeDataServer(input, ((NetHandlerPlayServer)event.getHandler()).player);
	}
	
	@SubscribeEvent
	public void clientPacket(ClientCustomPacketEvent event) {
		ByteBufInputStream input = new ByteBufInputStream(event.getPacket().payload());
		decodeDataClient(input, Minecraft.getMinecraft().player);
	}
	
	@SideOnly(Side.CLIENT)
	private void decodeDataClient(InputStream input, EntityPlayerSP player) {
		DataInputStream data = new DataInputStream(input);
		try {
			byte identity = data.readByte();
			switch(identity) {
				case 0: {
					new PacketFrog00TileUpdate().readData(data);
					break;
				}
				case 2: {
					new PacketFrog02GuiDataUpdate().readData(data);
					break;
				}
			}
		} catch (IOException e) {
			FrogAPI.FROG_LOG.error("Fail to unpack data, please report to author!", e);
		}
	}
	
	private void decodeDataServer(InputStream input, EntityPlayerMP player) {
		DataInputStream data = new DataInputStream(input);
		try {
			byte identity = data.readByte();
			switch (identity) {
				default:
					break; // This suppresses unused warning
			}
		} catch (IOException e) {
			FrogAPI.FROG_LOG.error("Fail to unpack data, please report to author!", e);
		}
		
	}
	
	private static ByteBuf asByteBuf(IFrogPacket packet) {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(byteArray);
		try {
			packet.writeData(data);
		} catch (IOException e) {
			FrogAPI.FROG_LOG.error("Fail to generate packet, please report to author!", e);
		}
		return Unpooled.wrappedBuffer(byteArray.toByteArray());
	}
	
	public void sendToAll(IFrogPacket packet) {
		frogChannel.sendToAll(new FMLProxyPacket(new PacketBuffer(asByteBuf(packet)), FrogAPI.MODID));
	}
	
	public void sendToAllAround(IFrogPacket packet, int dim, double x, double y, double z, double range) {
		frogChannel.sendToAllAround(new FMLProxyPacket(new PacketBuffer(asByteBuf(packet)), FrogAPI.MODID), new TargetPoint(dim, x, y, z, range));
	}
	
	public void sendToDimension(IFrogPacket packet, int dim) {
		frogChannel.sendToDimension(new FMLProxyPacket(new PacketBuffer(asByteBuf(packet)), FrogAPI.MODID), dim);
	}
	
	public void sendToPlayer(IFrogPacket packet, EntityPlayerMP player) {
		frogChannel.sendTo(new FMLProxyPacket(new PacketBuffer(asByteBuf(packet)), FrogAPI.MODID), player);
	}
	
	public void sendToServer(IFrogPacket packet) {
		frogChannel.sendToServer(new FMLProxyPacket(new PacketBuffer(asByteBuf(packet)), FrogAPI.MODID));
	}
	
}
