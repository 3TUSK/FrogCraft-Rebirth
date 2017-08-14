/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TSUK at 2:37:47 PM, Jul 21, 2016, CST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.client;

import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.block.BlockCondenseTower;
import frogcraftrebirth.common.block.BlockMPS;
import frogcraftrebirth.common.block.BlockMachine;
import frogcraftrebirth.common.registry.RegFrogItemsBlocks;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class FrogTextures {

	public static void regModel(ModelRegistryEvent event) {

	}
	
	static void initFrogItemsTexture() {
		RegHelper.registerModel(RegFrogItemsBlocks.AMMONIA_COOLANT_60K);
		RegHelper.registerModel(RegFrogItemsBlocks.AMMONIA_COOLANT_180K);
		RegHelper.registerModel(RegFrogItemsBlocks.AMMONIA_COOLANT_360K);
		RegHelper.registerModel(RegFrogItemsBlocks.DECAY_BATTERY_URANIUM);
		RegHelper.registerModel(RegFrogItemsBlocks.DECAY_BATTERY_THORIUM);
		RegHelper.registerModel(RegFrogItemsBlocks.DECAY_BATTERY_PLOTONIUM);
		RegHelper.registerModel(RegFrogItemsBlocks.ION_CANNON);
		RegHelper.registerModel(RegFrogItemsBlocks.ION_CANNON_FRAME);
		RegHelper.registerModel(RegFrogItemsBlocks.JINKELA);
		
		RegHelper.registerModel(RegFrogItemsBlocks.TIBERIUM_ITEM, 0, "tiberium_red");
		RegHelper.registerModel(RegFrogItemsBlocks.TIBERIUM_ITEM, 1, "tiberium_blue");
		RegHelper.registerModel(RegFrogItemsBlocks.TIBERIUM_ITEM, 2, "tiberium_green");
		
		RegHelper.registerModel(RegFrogItemsBlocks.REACTION_MODULE, 0, "module_heating");
		RegHelper.registerModel(RegFrogItemsBlocks.REACTION_MODULE, 1, "module_electrolysis");
		RegHelper.registerModel(RegFrogItemsBlocks.REACTION_MODULE, 2, "module_ammonia");
		RegHelper.registerModel(RegFrogItemsBlocks.REACTION_MODULE, 3, "module_sulfuric_acid");
		
		RegHelper.registerModel(RegFrogItemsBlocks.INGOT, 0, "potassium");
		RegHelper.registerModel(RegFrogItemsBlocks.INGOT, 1, "phosphorus");
		RegHelper.registerModel(RegFrogItemsBlocks.INGOT, 2, "natural_gas_hydrate");
		RegHelper.registerModel(RegFrogItemsBlocks.INGOT, 3, "briquette");
		RegHelper.registerModel(RegFrogItemsBlocks.INGOT, 4, "shattered_coal_coke");
		final String[] damnitSubNames = {"aluminium_oxide", "calcium_fluoride", "calcium_oxide", "calcium_hydroxide", "carnallite", "calcium_silicate", "dewalquite", "fluorapatite", "potassium_chloride", "magnalium", "magnesium_bromide", "ammonium_nitrate", "titanium_iv_oxide", "urea", "vanadium_v_oxide"};
		for (int index = 0; index < damnitSubNames.length; index++) {
			RegHelper.registerModel(RegFrogItemsBlocks.DUST, index, "dust/" + damnitSubNames[index]);
		}
		final String[] damnitSubNames2 = {"carnallite", "dewalquite", "fluorapatite"};
		for (int index = 0; index < damnitSubNames2.length; index++) {
			RegHelper.registerModel(RegFrogItemsBlocks.CRUSHED_DUST, index, "dust/" +  damnitSubNames2[index] + "_crushed");
			RegHelper.registerModel(RegFrogItemsBlocks.PURIFIED_DUST, index, "dust/" +  damnitSubNames2[index] + "_purified");
			RegHelper.registerModel(RegFrogItemsBlocks.SMALL_PILE_DUST, index, "dust/" +  damnitSubNames2[index] + "_small");
		}
		
		RegHelper.registerModel(RegFrogItemsBlocks.FLUID_ARMOR, "fluid_armor");
	}
	
	static void initFrogBlocksTexture() {
		RegHelper.registerModel(RegFrogItemsBlocks.ORE, 0, "carnallite");
		RegHelper.registerModel(RegFrogItemsBlocks.ORE, 1, "dewalquite");
		RegHelper.registerModel(RegFrogItemsBlocks.ORE, 2, "fluorapatite");
		RegHelper.registerModel(RegFrogItemsBlocks.TIBERIUM, 0);
		RegHelper.registerModel(RegFrogItemsBlocks.TIBERIUM, 1);
		RegHelper.registerModel(RegFrogItemsBlocks.TIBERIUM, 2);
		
		ModelLoader.setCustomStateMapper(RegFrogItemsBlocks.MPS, new StateMap.Builder().ignore(BlockMPS.LEVEL).build());
		RegHelper.registerModel(RegFrogItemsBlocks.MPS, "mobile_power_station");
		
		ModelLoader.setCustomStateMapper(RegFrogItemsBlocks.CONDENSE_TOWER, new StateMap.Builder().withSuffix("_condense_tower").withName(BlockCondenseTower.TYPE).build());
		RegHelper.registerModel(RegFrogItemsBlocks.CONDENSE_TOWER, 0, "condensetower/core");
		RegHelper.registerModel(RegFrogItemsBlocks.CONDENSE_TOWER, 1, "condensetower/cylinder");
		RegHelper.registerModel(RegFrogItemsBlocks.CONDENSE_TOWER, 2, "condensetower/outlet");
		
		ModelLoader.setCustomStateMapper(RegFrogItemsBlocks.MACHINE, new StateMap.Builder().withName(BlockMachine.TYPE).build());
		RegHelper.registerModel(RegFrogItemsBlocks.MACHINE, 0, "machine/advanced_chemical_reactor");
		RegHelper.registerModel(RegFrogItemsBlocks.MACHINE, 1, "machine/air_pump");
		RegHelper.registerModel(RegFrogItemsBlocks.MACHINE, 2, "machine/pyrolyzer");
		RegHelper.registerModel(RegFrogItemsBlocks.MACHINE, 3, "machine/liquefier");
		
		ModelLoader.setCustomStateMapper(RegFrogItemsBlocks.GENERATOR, new StateMap.Builder().build());
		RegHelper.registerModel(RegFrogItemsBlocks.GENERATOR, "combustion_furnace");
		
		RegHelper.registerModel(RegFrogItemsBlocks.HSU, 0, "hybrid_storage_unit");
		RegHelper.registerModel(RegFrogItemsBlocks.HSU, 1, "ultra_hybrid_storage_unit");

		RegHelper.regFluidBlockTexture(FrogFluids.ammonia);
		RegHelper.regFluidBlockTexture(FrogFluids.argon);
		RegHelper.regFluidBlockTexture(FrogFluids.benzene);
		RegHelper.regFluidBlockTexture(FrogFluids.bromine);
		RegHelper.regFluidBlockTexture(FrogFluids.carbonDioxide);
		RegHelper.regFluidBlockTexture(FrogFluids.carbonOxide);
		RegHelper.regFluidBlockTexture(FrogFluids.coalTar);
		RegHelper.regFluidBlockTexture(FrogFluids.fluorine);
		RegHelper.regFluidBlockTexture(FrogFluids.nitricAcid);
		RegHelper.regFluidBlockTexture(FrogFluids.nitrogenOxide);
		RegHelper.regFluidBlockTexture(FrogFluids.oxygen);
		RegHelper.regFluidBlockTexture(FrogFluids.sulfurDioxide);
		RegHelper.regFluidBlockTexture(FrogFluids.sulfurTrioxide);
	}

}
