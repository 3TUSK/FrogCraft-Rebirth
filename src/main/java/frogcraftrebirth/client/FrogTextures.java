/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TSUK at 2:37:47 PM, Jul 21, 2016, CST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.client;

import frogcraftrebirth.api.FrogBlocks;
import frogcraftrebirth.api.FrogItems;
import frogcraftrebirth.common.block.BlockCondenseTower;
import frogcraftrebirth.common.block.BlockMPS;
import frogcraftrebirth.common.block.BlockMachine;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FrogTextures {
	
	public static void initFrogItemsTexture() {
		RegHelper.registerModel(FrogItems.AMMONIA_COOLANT_60K);
		RegHelper.registerModel(FrogItems.AMMONIA_COOLANT_180K);
		RegHelper.registerModel(FrogItems.AMMONIA_COOLANT_360K);
		RegHelper.registerModel(FrogItems.DECAY_BATTERY_URANIUM);
		RegHelper.registerModel(FrogItems.DECAY_BATTERY_THORIUM);
		RegHelper.registerModel(FrogItems.DECAY_BATTERY_PLOTRIUM);
		RegHelper.registerModel(FrogItems.ION_CANNON);
		RegHelper.registerModel(FrogItems.ION_CANNON_FRAME);
		RegHelper.registerModel(FrogItems.JINKELA);
		
		RegHelper.registerModel(FrogItems.TIBERIUM, 0, "TIBERIUM_red");
		RegHelper.registerModel(FrogItems.TIBERIUM, 1, "TIBERIUM_blue");
		RegHelper.registerModel(FrogItems.TIBERIUM, 2, "TIBERIUM_green");
		
		RegHelper.registerModel(FrogItems.REACTION_MODULE, 0, "module_heating");
		RegHelper.registerModel(FrogItems.REACTION_MODULE, 1, "module_electrolysis");
		RegHelper.registerModel(FrogItems.REACTION_MODULE, 2, "module_ammonia");
		RegHelper.registerModel(FrogItems.REACTION_MODULE, 3, "module_sulfuric_acid");
		
		RegHelper.registerModel(FrogItems.INGOT, 0, "potassium");
		RegHelper.registerModel(FrogItems.INGOT, 1, "phosphorus");
		RegHelper.registerModel(FrogItems.INGOT, 2, "natural_gas_hydrate");
		RegHelper.registerModel(FrogItems.INGOT, 3, "briquette");
		RegHelper.registerModel(FrogItems.INGOT, 4, "shattered_coal_coke");
		final String[] damnitSubNames = {"aluminium_oxide", "calcium_fluoride", "calcium_oxide", "calcium_hydroxide", "carnallite", "calcium_silicate", "dewalquite", "fluorapatite", "potassium_chloride", "magnalium", "magnesium_bromide", "ammonium_nitrate", "titanium_iv_oxide", "urea", "vanadium_v_oxide"};
		//{"Al2O3", "CaF2", "CaO", "CaOH2", "carnallite", "CaSiO3", "dewalquite", "fluorapatite", "KCl", "magnalium", "MgBr2", "NH4NO3", "TiO2", "urea", "V2O5"};
		for (int index = 0; index < damnitSubNames.length; index++) {
			RegHelper.registerModel(FrogItems.DUST, index, "dust/" + damnitSubNames[index]);
		}
		final String[] damnitSubNames2 = {"carnallite", "dewalquite", "fluorapatite"};
		for (int index = 0; index < damnitSubNames2.length; index++) {
			RegHelper.registerModel(FrogItems.CRUSHED_DUST, index, "dust/" +  damnitSubNames2[index] + "_crushed");
			RegHelper.registerModel(FrogItems.PURIFIED_DUST, index, "dust/" +  damnitSubNames2[index] + "_purified");
			RegHelper.registerModel(FrogItems.SMALL_PILE_DUST, index, "dust/" +  damnitSubNames2[index] + "_small");
		}
		
		RegHelper.registerModel(FrogItems.FLUID_ARMOR, "fluid_armor");
	}
	
	public static void initFrogBlocksTexture() {
		RegHelper.registerModel(FrogBlocks.ORE, 0, "carnallite");
		RegHelper.registerModel(FrogBlocks.ORE, 1, "dewalquite");
		RegHelper.registerModel(FrogBlocks.ORE, 2, "fluorapatite");
		RegHelper.registerModel(FrogBlocks.TIBERIUM, 0);
		RegHelper.registerModel(FrogBlocks.TIBERIUM, 1);
		RegHelper.registerModel(FrogBlocks.TIBERIUM, 2);
		
		ModelLoader.setCustomStateMapper(FrogBlocks.MPS, new StateMap.Builder().ignore(BlockMPS.LEVEL).build());
		RegHelper.registerModel(FrogBlocks.MPS, "mobile_power_station");
		
		ModelLoader.setCustomStateMapper(FrogBlocks.CONDENSE_TOWER, new StateMap.Builder().withSuffix("_condense_tower").withName(BlockCondenseTower.TYPE).build());
		RegHelper.registerModel(FrogBlocks.CONDENSE_TOWER, 0, "condensetower/core");
		RegHelper.registerModel(FrogBlocks.CONDENSE_TOWER, 1, "condensetower/cylinder");
		RegHelper.registerModel(FrogBlocks.CONDENSE_TOWER, 2, "condensetower/outlet");
		
		ModelLoader.setCustomStateMapper(FrogBlocks.MACHINE, new StateMap.Builder().withName(BlockMachine.TYPE).build());
		RegHelper.registerModel(FrogBlocks.MACHINE, 0, "machine/advanced_chemical_reactor");
		RegHelper.registerModel(FrogBlocks.MACHINE, 1, "machine/air_pump");
		RegHelper.registerModel(FrogBlocks.MACHINE, 2, "machine/pyrolyzer");
		RegHelper.registerModel(FrogBlocks.MACHINE, 3, "machine/liquefier");
		
		ModelLoader.setCustomStateMapper(FrogBlocks.GENERATOR, new StateMap.Builder().build());
		RegHelper.registerModel(FrogBlocks.GENERATOR, "combustion_furnace");
		
		RegHelper.registerModel(FrogBlocks.HSU, 0, "hybrid_storage_unit");
		RegHelper.registerModel(FrogBlocks.HSU, 1, "ultra_hybrid_storage_unit");
		
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_AMMONIA, "ammonia");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_ARGON, "argon");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_BENZENE, "benzene");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_BROMINE, "bromine");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_CARBON_DIOXIDE, "carbon_dioxide");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_CARBON_OXIDE, "carbon_oxide");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_COAL_TAR, "coal_tar");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_FLUORINE, "fluorine");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_NITRIC_ACID, "nitric_acid");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_NITROGEN_OXIDE, "nitrogen_oxide");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_OXYGEN, "oxygen");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_SULFUR_DIOXIDE, "sulfur_dioxide");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.FLUID_SULFUR_TRIOXIDE, "sulfur_trioxide");
	}

}
