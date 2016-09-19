/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:04:31 PM, Aug 14, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.capability;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class CapabilityHandler {
	
	CapabilityHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public void onWorldCapLoad(AttachCapabilitiesEvent.World event) {
		//event.addCapability(key, cap);
		//TODO CapProvider 
	}

}
