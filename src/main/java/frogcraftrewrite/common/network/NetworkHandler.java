package frogcraftrewrite.common.network;

import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;

public class NetworkHandler {
	
	FMLEventChannel frogChannel;
	
	public NetworkHandler() {
		if(frogChannel == null)
			this.frogChannel = NetworkRegistry.INSTANCE.newEventDrivenChannel("FrogCraft");
		
		frogChannel.register(this);
	}

}
