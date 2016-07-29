/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 3:37:04 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common;

import java.util.Collection;
import java.util.Locale;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public final class FrogIMCHandler {
	
	private FrogIMCHandler() {
		throw new UnsupportedOperationException();
	}
	
	public static void resolveIMCMessage(Collection<FMLInterModComms.IMCMessage> messages) {
		for (FMLInterModComms.IMCMessage message : messages) {
			if (message.isNBTMessage()) {
				NBTTagCompound theTag = message.getNBTValue();
				String mode = theTag.getString("mode");

				if ("compat".equalsIgnoreCase(mode)) {
					String path = theTag.getString("modulePath");
					try {
						Class<?> clazz = Class.forName(path);
						if (frogcraftrebirth.api.ICompatModuleFrog.class.isAssignableFrom(clazz) )
							clazz.getDeclaredMethod("init").invoke(new Object());
					} catch (Exception e) {
						FrogAPI.FROG_LOG.error("Error occured when FrogCompatModule '%s' is loading. If you are not sure the origin, please report the FULL log to FrogCraft-Rebirth.", path);
						e.printStackTrace();
					}
				}

				if ("recipe".equalsIgnoreCase(mode)) {
					String machine = theTag.getString("machine").toLowerCase(Locale.ENGLISH);
						switch (machine) {
						case ("pyrolyzer"): {
							ItemStack input = ItemStack.loadItemStackFromNBT(theTag.getCompoundTag("input"));
							ItemStack output = ItemStack.loadItemStackFromNBT(theTag.getCompoundTag("output"));
							FluidStack outputFluid = FluidStack.loadFluidStackFromNBT(theTag.getCompoundTag("fluid"));
							int time = theTag.getInteger("time");
							FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(input, output, outputFluid, time));
							break;
						}
						case ("advchemreactor"):
							break;
						case ("condensetower"):
							break;
						default:
							break;
					}
				}
				
				if ("mps".equalsIgnoreCase(mode)) {
					String type = theTag.getString("type");
					ItemStack item = ItemStack.loadItemStackFromNBT((NBTTagCompound) theTag.getTag("item"));
					int value = theTag.getInteger("value");
					if (item != null) {
						switch (type.toLowerCase()) {
							case ("solar"): {
								MPSUpgradeManager.INSTANCE.registerSolarUpgrade(item);
								break;
							}
							case ("voltage"):
							case ("transformer"): {
								MPSUpgradeManager.INSTANCE.registerVoltageUpgrades(item, value);
								break;
							}
							case ("storage"): {
								MPSUpgradeManager.INSTANCE.registerStorageUpgrade(item, value);
								break;
							}
						}
					} else {
						FrogAPI.FROG_LOG.warn("'%s' is trying to register Mobile Power Station with NULL ItemStack, which is not alloed to happed.", message.getSender());
					}
				}

			}
		}
	}

}
