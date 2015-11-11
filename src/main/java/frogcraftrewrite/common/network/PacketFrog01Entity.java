package frogcraftrewrite.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;

public class PacketFrog01Entity implements IFrogPacket {
	
	Entity e;
	
	public PacketFrog01Entity() {}
	
	public PacketFrog01Entity(Entity e) {
		this.e = e;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(PACKET_ENTITY);
		output.writeInt(e.dimension);
		/*output.writeDouble(e.posX);
		output.writeDouble(e.posY);
		output.writeDouble(e.posZ);*/
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		//byte id = input.readByte();
		//int dimID = input.readInt(); 
		//double posX = input.readDouble(), posY = input.readDouble(), posZ = input.readDouble();
		//FMLServerHandler.instance().getServer().getEntityWorld().entit
		
	}

}
