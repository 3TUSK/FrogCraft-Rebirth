package frogcraftrebirth.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IFrogPacket {
	
	void writeData(DataOutputStream output) throws IOException;
	
	void readData(DataInputStream input) throws IOException;
	
	byte PACKET_INVALID = 0xFFFFFFFF;
	
	byte PACKET_TILE = 0x00000000;
	
	byte PACKET_ENTITY = 0x00000001;
	
	byte PACKET_GUI = 0x00000002;
}
