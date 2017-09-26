/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TSUK at 2:37:47 PM, Jul 21, 2016, CST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.client;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogRegistees;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.block.BlockCondenseTower;
import frogcraftrebirth.common.block.BlockMPS;
import frogcraftrebirth.common.block.BlockMachine;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = FrogAPI.MODID, value = Side.CLIENT)
public class FrogTextures {

	@SubscribeEvent
	public static void regModel(ModelRegistryEvent event) {
		OBJLoader.INSTANCE.addDomain(FrogAPI.MODID);

		RegHelper.registerModel(FrogRegistees.AMMONIA_COOLANT_60K);
		RegHelper.registerModel(FrogRegistees.AMMONIA_COOLANT_180K);
		RegHelper.registerModel(FrogRegistees.AMMONIA_COOLANT_360K);
		RegHelper.registerModel(FrogRegistees.DECAY_BATTERY_URANIUM);
		RegHelper.registerModel(FrogRegistees.DECAY_BATTERY_THORIUM);
		RegHelper.registerModel(FrogRegistees.DECAY_BATTERY_PLOTONIUM);
		RegHelper.registerModel(FrogRegistees.ION_CANNON);
		RegHelper.registerModel(FrogRegistees.ION_CANNON_FRAME);
		RegHelper.registerModel(FrogRegistees.JINKELA);
		
		RegHelper.registerModel(FrogRegistees.TIBERIUM_ITEM, 0, "tiberium_red");
		RegHelper.registerModel(FrogRegistees.TIBERIUM_ITEM, 1, "tiberium_blue");
		RegHelper.registerModel(FrogRegistees.TIBERIUM_ITEM, 2, "tiberium_green");
		
		RegHelper.registerModel(FrogRegistees.REACTION_MODULE, 0, "module_heating");
		RegHelper.registerModel(FrogRegistees.REACTION_MODULE, 1, "module_electrolysis");
		RegHelper.registerModel(FrogRegistees.REACTION_MODULE, 2, "module_ammonia");
		RegHelper.registerModel(FrogRegistees.REACTION_MODULE, 3, "module_sulfuric_acid");
		
		RegHelper.registerModel(FrogRegistees.INGOT, 0, "potassium");
		RegHelper.registerModel(FrogRegistees.INGOT, 1, "phosphorus");
		RegHelper.registerModel(FrogRegistees.INGOT, 2, "natural_gas_hydrate");
		RegHelper.registerModel(FrogRegistees.INGOT, 3, "briquette");
		RegHelper.registerModel(FrogRegistees.INGOT, 4, "shattered_coal_coke");
		final String[] damnitSubNames = {"aluminium_oxide", "calcium_fluoride", "calcium_oxide", "calcium_hydroxide", "carnallite", "calcium_silicate", "dewalquite", "fluorapatite", "potassium_chloride", "magnalium", "magnesium_bromide", "ammonium_nitrate", "titanium_iv_oxide", "urea", "vanadium_v_oxide"};
		for (int index = 0; index < damnitSubNames.length; index++) {
			RegHelper.registerModel(FrogRegistees.DUST, index, "dust/" + damnitSubNames[index]);
		}
		final String[] damnitSubNames2 = {"carnallite", "dewalquite", "fluorapatite"};
		for (int index = 0; index < damnitSubNames2.length; index++) {
			RegHelper.registerModel(FrogRegistees.CRUSHED_DUST, index, "dust/" +  damnitSubNames2[index] + "_crushed");
			RegHelper.registerModel(FrogRegistees.PURIFIED_DUST, index, "dust/" +  damnitSubNames2[index] + "_purified");
			RegHelper.registerModel(FrogRegistees.SMALL_PILE_DUST, index, "dust/" +  damnitSubNames2[index] + "_small");
		}
		
		RegHelper.registerModel(FrogRegistees.FLUID_ARMOR, "fluid_armor");

		RegHelper.registerModel(FrogRegistees.ORE, 0, "carnallite");
		RegHelper.registerModel(FrogRegistees.ORE, 1, "dewalquite");
		RegHelper.registerModel(FrogRegistees.ORE, 2, "fluorapatite");
		RegHelper.registerModel(FrogRegistees.TIBERIUM, 0);
		RegHelper.registerModel(FrogRegistees.TIBERIUM, 1);
		RegHelper.registerModel(FrogRegistees.TIBERIUM, 2);
		
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
		RegHelper.regFluidBlockTexture(FrogFluids.methane);
		RegHelper.regFluidBlockTexture(FrogFluids.nitricAcid);
		RegHelper.regFluidBlockTexture(FrogFluids.nitrogen);
		RegHelper.regFluidBlockTexture(FrogFluids.nitrogenOxide);
		RegHelper.regFluidBlockTexture(FrogFluids.oleum);
		RegHelper.regFluidBlockTexture(FrogFluids.oxygen);
		RegHelper.regFluidBlockTexture(FrogFluids.sulfuricAcid);
		RegHelper.regFluidBlockTexture(FrogFluids.sulfurDioxide);
		RegHelper.regFluidBlockTexture(FrogFluids.sulfurTrioxide);
	}

}
