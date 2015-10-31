package frogcraftrewrite.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IFrogPacket {
	
	void writeData(DataOutputStream output) throws IOException;
	
	void readData(DataInputStream input) throws IOException;
}
