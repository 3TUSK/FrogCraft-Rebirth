/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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

package frogcraftrebirth.client;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogConstants;
import frogcraftrebirth.api.FrogRegistees;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.block.BlockCondenseTower;
import frogcraftrebirth.common.block.BlockMPS;
import frogcraftrebirth.common.block.BlockMachine;
import frogcraftrebirth.common.block.BlockMachine2;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = FrogAPI.MODID, value = Side.CLIENT)
public class FrogTextures {

	@SubscribeEvent
	public static void regModel(ModelRegistryEvent event) {

		RegHelper.registerModel(FrogRegistees.AMMONIA_COOLANT_60K, "ammonia_coolant_60k");
		RegHelper.registerModel(FrogRegistees.AMMONIA_COOLANT_180K, "ammonia_coolant_180k");
		RegHelper.registerModel(FrogRegistees.AMMONIA_COOLANT_360K, "ammonia_coolant_360k");
		RegHelper.registerModel(FrogRegistees.DECAY_BATTERY_URANIUM, "uranium_decay_battery");
		RegHelper.registerModel(FrogRegistees.DECAY_BATTERY_THORIUM, "thorium_decay_battery");
		RegHelper.registerModel(FrogRegistees.DECAY_BATTERY_PLOTONIUM, "plutonium_decay_battery");
		RegHelper.registerModel(FrogRegistees.JINKELA, "jinkela");

		RegHelper.registerModel(FrogRegistees.REACTION_MODULE, 0, "module_heating");
		RegHelper.registerModel(FrogRegistees.REACTION_MODULE, 1, "module_electrolysis");
		RegHelper.registerModel(FrogRegistees.REACTION_MODULE, 2, "module_ammonia");
		RegHelper.registerModel(FrogRegistees.REACTION_MODULE, 3, "module_sulfuric_acid");

		for (int i = 0; i < FrogConstants.ORE_TYPES.length; i++) {
			RegHelper.registerModel(FrogRegistees.ORE_CRUSHED, i, "ore/crushed/" + FrogConstants.ORE_TYPES[i]);
			RegHelper.registerModel(FrogRegistees.ORE_PURIFIED, i, "ore/purified/" + FrogConstants.ORE_TYPES[i]);
			RegHelper.registerModel(FrogRegistees.ORE_DUST, i, "ore/dust/" + FrogConstants.ORE_TYPES[i]);
			RegHelper.registerModel(FrogRegistees.ORE_DUST_TINY, i, "ore/dust_tiny/" + FrogConstants.ORE_TYPES[i]);
		}
		for (int i = 0; i < FrogConstants.METALLIC_MATERIAL_TYPES.length; i++) {
			RegHelper.registerModel(FrogRegistees.METAL_INGOT, i, "metal/ingot/" + FrogConstants.METALLIC_MATERIAL_TYPES[i]);
			RegHelper.registerModel(FrogRegistees.METAL_CASING, i, "metal/casing/" + FrogConstants.METALLIC_MATERIAL_TYPES[i]);
			RegHelper.registerModel(FrogRegistees.METAL_PLATE, i, "metal/plate/" + FrogConstants.METALLIC_MATERIAL_TYPES[i]);
			RegHelper.registerModel(FrogRegistees.METAL_PLATE_DENSE, i, "metal/plate_dense/" + FrogConstants.METALLIC_MATERIAL_TYPES[i]);
			RegHelper.registerModel(FrogRegistees.METAL_DUST, i, "metal/dust/" + FrogConstants.METALLIC_MATERIAL_TYPES[i]);
			RegHelper.registerModel(FrogRegistees.METAL_DUST_TNIY, i, "metal/dust_tiny/" + FrogConstants.METALLIC_MATERIAL_TYPES[i]);
		}
		for (int i = 0; i < FrogConstants.NON_METAL_MATERIAL_TYPES.length; i++) {
			RegHelper.registerModel(FrogRegistees.NON_METAL_DUST, i, "non_metal/dust/" + FrogConstants.NON_METAL_MATERIAL_TYPES[i]);
			RegHelper.registerModel(FrogRegistees.NON_METAL_DUST_TINY, i, "non_metal/dust_tiny/" +  FrogConstants.NON_METAL_MATERIAL_TYPES[i]);
		}
		for (int i = 0; i < FrogConstants.INTERMEDIATE_TYPES.length; i++) {
			RegHelper.registerModel(FrogRegistees.INTERMEDIATE, i, "intermediate/" + FrogConstants.INTERMEDIATE_TYPES[i]);
		}
		for (int i = 0; i < FrogConstants.INFLAMMABLE.length; i++) {
			RegHelper.registerModel(FrogRegistees.INFLAMMABLE, i, "inflammable/" + FrogConstants.INFLAMMABLE[i]);
		}
		
		RegHelper.registerModel(FrogRegistees.FLUID_ARMOR, "fluid_armor");

		RegHelper.registerModel(FrogRegistees.ORE, 0, "carnallite");
		RegHelper.registerModel(FrogRegistees.ORE, 1, "dewalquite");
		RegHelper.registerModel(FrogRegistees.ORE, 2, "fluorapatite");

		ModelLoader.setCustomStateMapper(FrogRegistees.MPS, new StateMap.Builder().ignore(BlockMPS.LEVEL).build());
		RegHelper.registerModel(FrogRegistees.MPS, "mobile_power_station");
		
		ModelLoader.setCustomStateMapper(FrogRegistees.CONDENSE_TOWER, new StateMap.Builder().withSuffix("_condense_tower").withName(BlockCondenseTower.TYPE).build());
		RegHelper.registerModel(FrogRegistees.CONDENSE_TOWER, 0, "condensetower/core");
		RegHelper.registerModel(FrogRegistees.CONDENSE_TOWER, 1, "condensetower/cylinder");
		RegHelper.registerModel(FrogRegistees.CONDENSE_TOWER, 2, "condensetower/outlet");
		
		ModelLoader.setCustomStateMapper(FrogRegistees.MACHINE, new StateMap.Builder().withName(BlockMachine.TYPE).build());
		RegHelper.registerModel(FrogRegistees.MACHINE, 0, "machine/advanced_chemical_reactor");
		RegHelper.registerModel(FrogRegistees.MACHINE, 1, "machine/air_pump");
		RegHelper.registerModel(FrogRegistees.MACHINE, 2, "machine/pyrolyzer");
		RegHelper.registerModel(FrogRegistees.MACHINE, 3, "machine/liquefier");

		ModelLoader.setCustomStateMapper(FrogRegistees.MACHINE2, new StateMap.Builder().withName(BlockMachine2.TYPE).build());
		RegHelper.registerModel(FrogRegistees.MACHINE2, 0, "machine/advanced_blast_furnace");

		ModelLoader.setCustomStateMapper(FrogRegistees.GENERATOR, new StateMap.Builder().build());
		RegHelper.registerModel(FrogRegistees.GENERATOR, "combustion_furnace");
		
		RegHelper.registerModel(FrogRegistees.HSU, 0, "hybrid_storage_unit");
		RegHelper.registerModel(FrogRegistees.HSU, 1, "ultra_hybrid_storage_unit");

		RegHelper.regFluidBlockTexture(FrogFluids.ammonia);
		RegHelper.regFluidBlockTexture(FrogFluids.argon);
		RegHelper.regFluidBlockTexture(FrogFluids.benzene);
		RegHelper.regFluidBlockTexture(FrogFluids.bromine);
		RegHelper.regFluidBlockTexture(FrogFluids.carbonDioxide);
		RegHelper.regFluidBlockTexture(FrogFluids.carbonOxide);
		RegHelper.regFluidBlockTexture(FrogFluids.coalTar);
		RegHelper.regFluidBlockTexture(FrogFluids.fluorine);
		RegHelper.regFluidBlockTexture(FrogFluids.glycerol);
		RegHelper.regFluidBlockTexture(FrogFluids.methane);
		RegHelper.regFluidBlockTexture(FrogFluids.nitricAcid);
		RegHelper.regFluidBlockTexture(FrogFluids.nitrogen);
		RegHelper.regFluidBlockTexture(FrogFluids.nitrogenOxide);
		RegHelper.regFluidBlockTexture(FrogFluids.oleum);
		RegHelper.regFluidBlockTexture(FrogFluids.sulfuricAcid);
		RegHelper.regFluidBlockTexture(FrogFluids.sulfurDioxide);
		RegHelper.regFluidBlockTexture(FrogFluids.sulfurTrioxide);
	}

}
