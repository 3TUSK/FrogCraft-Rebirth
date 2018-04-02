/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.common.lib.AdvChemRecRecipe;
import frogcraftrebirth.common.lib.CondenseTowerRecipe;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputItemStack;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputOreDict;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputUniversalFluidCell;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

final class FrogIMCHandler {
	
	private FrogIMCHandler() {
		throw new UnsupportedOperationException();
	}
	
	static void resolveIMCMessage(Collection<FMLInterModComms.IMCMessage> messages) {
		for (FMLInterModComms.IMCMessage message : messages) {
			if (message.isNBTMessage()) {
				NBTTagCompound theTag = message.getNBTValue();
				switch (message.key.toLowerCase(Locale.ENGLISH)) {
					case ("recipe.pyrolyzer"): {
						IFrogRecipeInput input = parse(theTag.getCompoundTag("input"));
						ItemStack output = new ItemStack(theTag.getCompoundTag("output"));
						FluidStack outputFluid = FluidStack.loadFluidStackFromNBT(theTag.getCompoundTag("fluid"));
						int time = theTag.getInteger("time");
						int energyPerTick = theTag.getInteger("energyPerTick");
						FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(input, output, outputFluid, time, energyPerTick));
						break;
					}
					case ("recipe.adv_chem_reactor"): {
						NBTTagCompound inputs = theTag.getCompoundTag("inputs"), outputs = theTag.getCompoundTag("outputs");
						ArrayList<IFrogRecipeInput> inputsArray = new ArrayList<>();
						ArrayList<ItemStack> outputsArray = new ArrayList<>();
						for (int n = 0; n < 5; n++) {
							int index = n + 1;
							inputsArray.add(parse(inputs.getCompoundTag("input" + index)));
							outputsArray.add(new ItemStack(outputs.getCompoundTag("output" + index)));
						}
						inputsArray.removeIf(IFrogRecipeInput::isEmpty);
						outputsArray.removeIf(ItemStack::isEmpty);
						int time = theTag.getInteger("time");
						int energyPerTick = theTag.getInteger("energyPerTick");
						ItemStack catalyst = new ItemStack(theTag.getCompoundTag("catalyst"));
						int cellReq = theTag.getInteger("cellReq");
						int cellProduce = theTag.getInteger("cellProduce");
						FrogAPI.managerACR.add(new AdvChemRecRecipe(inputsArray, outputsArray, catalyst, time, energyPerTick, cellReq, cellProduce));
						break;
					}
					case ("recipe.condense_tower"): {
						FluidStack input = FluidStack.loadFluidStackFromNBT(theTag.getCompoundTag("input"));
						NBTTagCompound outputs = theTag.getCompoundTag("outputs");
						FluidStack[] outputArray = new FluidStack[5]; //TODO: Remove the hardcoded 5 outputs limit
						for (int index = 0; index < 5; index++) {
							outputArray[index] = FluidStack.loadFluidStackFromNBT(outputs.getCompoundTag("output" + index));
						}
						int time = theTag.getInteger("time");
						int energyPerTick = theTag.getInteger("energyPerTick");
						FrogAPI.managerCT.add(new CondenseTowerRecipe(time, energyPerTick, input, outputArray));
						break;
					}
					case ("recipe.combustion_furnace"): {
						ItemStack input = new ItemStack(theTag.getCompoundTag("input"));
						ItemStack output = new ItemStack(theTag.getCompoundTag("output"));
						FluidStack outputFluid = FluidStack.loadFluidStackFromNBT(theTag.getCompoundTag("fluid"));
						String ore = theTag.getString("ore");
						if (!input.isEmpty()) {
							if (!output.isEmpty())
								FrogAPI.FUEL_REG.regFuelByproduct(input, output);
							if (outputFluid != null)
								FrogAPI.FUEL_REG.regFuelByproduct(input, outputFluid);
						} else if (!ore.isEmpty()) {
							if (!output.isEmpty())
								FrogAPI.FUEL_REG.regFuelByproduct(ore, output);
							if (outputFluid != null)
								FrogAPI.FUEL_REG.regFuelByproduct(ore, outputFluid);
						} else {
							FrogAPI.FROG_LOG.warn("A broken Combustion Furnace sent by %s byproduct registry is detected. Please double check the code, or report to FrogCraftRebirth immediately!", message.getSender());
						}
						break;
					}
					case ("mps"): {
						String type = theTag.getString("type");
						ItemStack item = new ItemStack((NBTTagCompound) theTag.getTag("item"));
						int value = theTag.getInteger("value");
						if (!item.isEmpty()) {
							switch (type.toLowerCase(Locale.ENGLISH)) {
								case ("solar"): {
									MPSUpgradeManager.INSTANCE.registerSolarUpgrade(item);
									break;
								}
								case ("voltage"):
								case ("transformer"): {
									MPSUpgradeManager.INSTANCE.registerVoltageUpgrades(item, value);
									break;
								}
								case ("capacity"):
								case ("storage"): {
									MPSUpgradeManager.INSTANCE.registerStorageUpgrade(item, value);
									break;
								}
							}
						} else {
							FrogAPI.FROG_LOG.warn("'%s' is trying to register Mobile Power Station with NULL ItemStack, which is not allowed.", message.getSender());
						}
					}
					default:
						break;
				}
			}
		}
	}

	private static IFrogRecipeInput parse(NBTTagCompound tag) {
		switch (tag.getString("type").toLowerCase(Locale.ENGLISH)) {
			case "itemstack": return new FrogRecipeInputItemStack(new ItemStack(tag));
			case "ore": return new FrogRecipeInputOreDict(tag.getString("ore"), tag.getInteger("count"));
			case "fluid": {
				FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(tag);
				return fluidStack == null ? FrogRecipeInputs.EMPTY : new FrogRecipeInputUniversalFluidCell(fluidStack);
			}
			default: return FrogRecipeInputs.EMPTY;
		}
	}

}
