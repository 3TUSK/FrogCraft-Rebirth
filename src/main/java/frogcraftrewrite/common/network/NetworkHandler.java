package frogcraftrewrite.common.network;

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
import frogcraftrewrite.FrogCraftRebirth;
import frogcraftrewrite.common.lib.FrogRef;
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
		FrogCraftRebirth.frogLogger.debug("This is a debug message, please ignore. Thanks.");
	}
	
	@SubscribeEvent
	public void serverPacket(ServerCustomPacketEvent event) {
		ByteBufInputStream input = new ByteBufInputStream(event.packet.payload());
		onPacketData(input, ((NetHandlerPlayServer)event.handler).playerEntity);
		System.out.println("Something happened on server side");
	}
	
	@SubscribeEvent
	public void clientPacket(ClientCustomPacketEvent event) {
		ByteBufInputStream input = new ByteBufInputStream(event.packet.payload());
		onPacketData(input, Minecraft.getMinecraft().thePlayer);
		System.out.println("Something happened on client side");
	}
	
	public void onPacketData(InputStream input, EntityPlayer player) {
		DataInputStream data = new DataInputStream(input);
		try {
			int identity = data.readInt();
			switch(identity) {
				case 0: {
				new PacketTileUpdate().readData(data);
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
			FrogCraftRebirth.frogLogger.error("Fail to generate packet, please report to author!");
		}
		return Unpooled.wrappedBuffer(byteArray.toByteArray());
	}
	
	public static void sendToAll(IFrogPacket packet) {
		FROG_NETWORK.frogChannel.sendToAll(new FMLProxyPacket(asByteBuf(packet), FrogRef.MODID));
	}
	
	public static void sendToAllAround(IFrogPacket packet, int dim, double x, double y, double z, double range) {
		FROG_NETWORK.frogChannel.sendToAllAround(new FMLProxyPacket(asByteBuf(packet), FrogRef.MODID), new TargetPoint(dim, x, y, z, range));
	}
	
	public static void sendToDimension(IFrogPacket packet, int dim) {
		FROG_NETWORK.frogChannel.sendToDimension(new FMLProxyPacket(asByteBuf(packet), FrogRef.MODID), dim);
	}
	
	public static void sendToPlayer(IFrogPacket packet, EntityPlayerMP player) {
		FROG_NETWORK.frogChannel.sendTo(new FMLProxyPacket(asByteBuf(packet), FrogRef.MODID), player);
	}
	
	public static void sendToServer(IFrogPacket packet) {
		FROG_NETWORK.frogChannel.sendToServer(new FMLProxyPacket(asByteBuf(packet), FrogRef.MODID));
	}
	
}
