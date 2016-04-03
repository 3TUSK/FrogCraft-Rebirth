/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 3:37:04 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common;

import static frogcraftrebirth.FrogCraftRebirth.FROG_LOG;

import java.util.Collection;
import java.util.Locale;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

public final class FrogIMCHanlder {
	
	public static void resolveIMCMessage(Collection<IMCMessage> messages) {
		for (FMLInterModComms.IMCMessage message : messages) {
			if (message.isNBTMessage()) {
				String mode = message.getNBTValue().getString("mode");

				if ("compat".equals(mode)) {
					try {
						Class<?> clazz = Class.forName(message.getNBTValue().getString("modulePath"));
						if (clazz.getInterfaces()[0] == frogcraftrebirth.api.ICompatModuleFrog.class)
							clazz.getDeclaredMethod("init").invoke(new Object());
					} catch (Exception e) {
						FROG_LOG.error(
								"Error occured when one FrogCompatModule is loading. If you are not sure the origin, please report the FULL log to FrogCraft-Rebirth.");
						e.printStackTrace();
					}
				}

				if ("recipe".equals(mode)) {
					String machine = message.getNBTValue().getString("machine").toLowerCase(Locale.ENGLISH);
					switch (machine) {
					case ("pyrolyzer"):
						break;
					case ("advchemreactor"):
						break;
					case ("condensetower"):
						break;
					case ("liquidier"):
						break;
					default:
						break;
					}
				}

			}
		}
	}

}
