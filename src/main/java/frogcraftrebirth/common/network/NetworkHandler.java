package frogcraftrebirth.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.FrogRef;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class NetworkHandler {
	
	public static final NetworkHandler FROG_NETWORK = new NetworkHandler();
	
	private final FMLEventChannel frogChannel;
	
	private NetworkHandler() {
		frogChannel = NetworkRegistry.INSTANCE.newEventDrivenChannel(FrogRef.MODID);
		frogChannel.register(this);
	}
	
	public static void init() {
		FrogCraftRebirth.FROG_LOG.debug("Initializing FrogCraft Network...");
	}
	
	@SubscribeEvent
	public void serverPacket(ServerCustomPacketEvent event) {
		ByteBufInputStream input = new ByteBufInputStream(event.packet.payload());
		decodeData(input, ((NetHandlerPlayServer)event.handler).playerEntity);
	}
	
	@SubscribeEvent
	public void clientPacket(ClientCustomPacketEvent event) {
		ByteBufInputStream input = new ByteBufInputStream(event.packet.payload());
		decodeData(input, Minecraft.getMinecraft().thePlayer);
	}
	
	private void decodeData(InputStream input, EntityPlayer player) {
		DataInputStream data = new DataInputStream(input);
		try {
			byte identity = data.readByte();
			switch(identity) {
				case 0: {
					new PacketFrog00TileUpdate().readData(data);
					break;
				}
				case 1: {
					new PacketFrog01Entity().readData(data);
					break;
				}
				case 2: {
					new PacketFrog02GuiDataUpdate().readData(data);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static ByteBuf asByteBuf(IFrogPacket packet) {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(byteArray);
		try {
			packet.writeData(data);
		} catch (IOException e) {
			FrogCraftRebirth.FROG_LOG.error("Fail to generate packet, please report to author!");
		}
		return Unpooled.wrappedBuffer(byteArray.toByteArray());
	}
	
	public void sendToAll(IFrogPacket packet) {
		frogChannel.sendToAll(new FMLProxyPacket(asByteBuf(packet), FrogRef.MODID));
	}
	
	public void sendToAllAround(IFrogPacket packet, int dim, double x, double y, double z, double range) {
		frogChannel.sendToAllAround(new FMLProxyPacket(asByteBuf(packet), FrogRef.MODID), new TargetPoint(dim, x, y, z, range));
	}
	
	public void sendToDimension(IFrogPacket packet, int dim) {
		frogChannel.sendToDimension(new FMLProxyPacket(asByteBuf(packet), FrogRef.MODID), dim);
	}
	
	public void sendToPlayer(IFrogPacket packet, EntityPlayerMP player) {
		frogChannel.sendTo(new FMLProxyPacket(asByteBuf(packet), FrogRef.MODID), player);
	}
	
	public void sendToServer(IFrogPacket packet) {
		frogChannel.sendToServer(new FMLProxyPacket(asByteBuf(packet), FrogRef.MODID));
	}
	
}
