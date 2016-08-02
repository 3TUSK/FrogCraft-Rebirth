/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 3:37:04 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.OreStack;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.common.lib.AdvChemRecRecipe;
import frogcraftrebirth.common.lib.CondenseTowerRecipe;
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
							clazz.getDeclaredMethod("init").invoke(clazz.newInstance());
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
							int energyPerTick = theTag.getInteger("energyPerTick");
							FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(input, output, outputFluid, time, energyPerTick));
							break;
						}
						case ("advchemreactor"): {
							NBTTagCompound inputs = theTag.getCompoundTag("inputs"), outputs = theTag.getCompoundTag("outputs");
							ArrayList<OreStack> inputsArray = new ArrayList<OreStack>();
							ArrayList<ItemStack> outputsArray = new ArrayList<ItemStack>();
							for (int n = 0; n < 5; n++) {
								int index = n + 1;
								inputsArray.add(OreStack.loadFromNBT(inputs.getCompoundTag("input" + index)));
								outputsArray.add(ItemStack.loadItemStackFromNBT(outputs.getCompoundTag("output" + index)));
							}
							inputsArray.removeIf((OreStack stack) -> {
								return stack == null;
							});
							outputsArray.removeIf((ItemStack stack) -> {
								return stack == null;
							});
							int time = theTag.getInteger("time");
							int energyPerTick = theTag.getInteger("energyPerTick");
							String catalyst = theTag.getString("catalyst");
							int cellReq = theTag.getInteger("cellReq");
							int cellProduce = theTag.getInteger("cellProduce");
							FrogAPI.managerACR.add(new AdvChemRecRecipe(inputsArray, outputsArray, catalyst, time, energyPerTick, cellReq, cellProduce));
							break;
						}
						case ("condensetower"): {
							FluidStack input = FluidStack.loadFluidStackFromNBT(theTag.getCompoundTag("input"));
							NBTTagCompound outputs = theTag.getCompoundTag("outputs");
							FluidStack[] outputArray = new FluidStack[5];
							for (int index = 0; index < 5; index++) {
								outputArray[index] = FluidStack.loadFluidStackFromNBT(outputs.getCompoundTag("output" + index));
							}
							int time = theTag.getInteger("time");
							int energyPerTick = theTag.getInteger("energyPerTick");
							FrogAPI.managerCT.add(new CondenseTowerRecipe(time, energyPerTick, input, outputArray));
							break;
						}
						case ("combustionfurnace"): {
							ItemStack input = ItemStack.loadItemStackFromNBT(theTag.getCompoundTag("input"));
							ItemStack output = ItemStack.loadItemStackFromNBT(theTag.getCompoundTag("output"));
							FluidStack outputFluid = FluidStack.loadFluidStackFromNBT(theTag.getCompoundTag("fluid"));
							String ore = theTag.getString("ore");
							if (input != null) {
								if (output != null)
									FrogAPI.FUEL_REG.regFuelByproduct(input, output);
								if (outputFluid != null)
									FrogAPI.FUEL_REG.regFuelByproduct(input, outputFluid);
							} else if (ore != null && (!ore.equals(""))) {
								if (output != null)
									FrogAPI.FUEL_REG.regFuelByproduct(ore, output);
								if (outputFluid != null)
									FrogAPI.FUEL_REG.regFuelByproduct(ore, outputFluid);
							} else {
								FrogAPI.FROG_LOG.warn("A broken Combustion Furnace sent by %s byproduct registry is detected. Please double check the code, or report to FrogCraftRebirth immediately!", message.getSender());
							}
							break;
						}
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
						FrogAPI.FROG_LOG.warn("'%s' is trying to register Mobile Power Station with NULL ItemStack, which is not allowed.", message.getSender());
					}
				}

			}
		}
	}

}
