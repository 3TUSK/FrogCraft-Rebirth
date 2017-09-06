package frogcraftrebirth.api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 * Implementing this interface marks the target has ability to synchronizing
 * data between server and client. Note: this interface is not considered as
 * completed yet.
 * @deprecated Not worth to be part of the public API, will be moved into
 *             internal package in next breaking change.
 * @author 3TUSK
 */
@Deprecated
public interface IFrogNetworkObject {

	void writePacketData(DataOutputStream output) throws IOException;

	void readPacketData(DataInputStream input) throws IOException;

}
