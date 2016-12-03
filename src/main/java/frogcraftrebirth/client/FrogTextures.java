/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TSUK at 2:37:47 PM, Jul 21, 2016, CST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.client;

import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogItems;
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
		RegHelper.registerModel(FrogItems.coolantAmmonia60K);
		RegHelper.registerModel(FrogItems.coolantAmmonia180K);
		RegHelper.registerModel(FrogItems.coolantAmmonia360K);
		RegHelper.registerModel(FrogItems.decayBatteryPlutoium);
		RegHelper.registerModel(FrogItems.decayBatteryThorium);
		RegHelper.registerModel(FrogItems.decayBatteryUranium);
		RegHelper.registerModel(FrogItems.ionCannon);
		RegHelper.registerModel(FrogItems.ionCannonFrame);
		RegHelper.registerModel(FrogItems.jinkela);
		
		RegHelper.registerModel(FrogItems.tiberium, 0, "tiberium_red");
		RegHelper.registerModel(FrogItems.tiberium, 1, "tiberium_blue");
		RegHelper.registerModel(FrogItems.tiberium, 2, "tiberium_green");
		
		RegHelper.registerModel(FrogItems.itemReactionModule, 0, "module_heating");
		RegHelper.registerModel(FrogItems.itemReactionModule, 1, "module_electrolysis");
		RegHelper.registerModel(FrogItems.itemReactionModule, 2, "module_ammonia");
		RegHelper.registerModel(FrogItems.itemReactionModule, 3, "module_sulfuric_acid");
		
		RegHelper.registerModel(FrogItems.itemIngot, 0, "potassium");
		RegHelper.registerModel(FrogItems.itemIngot, 1, "phosphorus");
		RegHelper.registerModel(FrogItems.itemIngot, 2, "natural_gas_hydrate");
		RegHelper.registerModel(FrogItems.itemIngot, 3, "briquette");
		RegHelper.registerModel(FrogItems.itemIngot, 4, "shattered_coal_coke");
		final String[] damnitSubNames = {"aluminium_oxide", "calcium_fluoride", "calcium_oxide", "calcium_hydroxide", "carnallite", "calcium_silicate", "dewalquite", "fluorapatite", "potassium_chloride", "magnalium", "magnesium_bromide", "ammoinum_nitrate", "titanium_iv_oxide", "urea", "vanadium_v_oxide"};
		//{"Al2O3", "CaF2", "CaO", "CaOH2", "carnallite", "CaSiO3", "dewalquite", "fluorapatite", "KCl", "magnalium", "MgBr2", "NH4NO3", "TiO2", "urea", "V2O5"};
		for (int index = 0; index < damnitSubNames.length; index++) {
			RegHelper.registerModel(FrogItems.itemDust, index, "dust/" + damnitSubNames[index]);
		}
		final String[] damnitSubNames2 = {"carnallite", "dewalquite", "fluorapatite"};
		for (int index = 0; index < damnitSubNames2.length; index++) {
			RegHelper.registerModel(FrogItems.itemCrushedDust, index, "dust/" +  damnitSubNames2[index] + "_crushed");
			RegHelper.registerModel(FrogItems.itemPurifiedDust, index, "dust/" +  damnitSubNames2[index] + "_purified");
			RegHelper.registerModel(FrogItems.itemSmallPileDust, index, "dust/" +  damnitSubNames2[index] + "_small");
		}
		
		RegHelper.registerModel(FrogItems.fluidArmor, "fluid_armor");
	}
	
	public static void initFrogBlocksTexture() {
		RegHelper.registerModel(FrogBlocks.frogOres, 0, "carnallite");
		RegHelper.registerModel(FrogBlocks.frogOres, 1, "dewalquite");
		RegHelper.registerModel(FrogBlocks.frogOres, 2, "fluorapatite");
		RegHelper.registerModel(FrogBlocks.tiberium, 0);
		RegHelper.registerModel(FrogBlocks.tiberium, 1);
		RegHelper.registerModel(FrogBlocks.tiberium, 2);
		
		ModelLoader.setCustomStateMapper(FrogBlocks.mobilePowerStation, new StateMap.Builder().ignore(BlockMPS.LEVEL).build());
		RegHelper.registerModel(FrogBlocks.mobilePowerStation, "mobile_power_station");
		
		ModelLoader.setCustomStateMapper(FrogBlocks.condenseTowerPart, new StateMap.Builder().withSuffix("_condense_tower").withName(BlockCondenseTower.TYPE).build());
		RegHelper.registerModel(FrogBlocks.condenseTowerPart, 0, "condensetower/core");
		RegHelper.registerModel(FrogBlocks.condenseTowerPart, 1, "condensetower/cylinder");
		RegHelper.registerModel(FrogBlocks.condenseTowerPart, 2, "condensetower/outlet");
		
		ModelLoader.setCustomStateMapper(FrogBlocks.machines, new StateMap.Builder().withName(BlockMachine.TYPE).build());
		RegHelper.registerModel(FrogBlocks.machines, 0, "machine/advanced_chemical_reactor");
		RegHelper.registerModel(FrogBlocks.machines, 1, "machine/air_pump");
		RegHelper.registerModel(FrogBlocks.machines, 2, "machine/pyrolyzer");
		RegHelper.registerModel(FrogBlocks.machines, 3, "machine/liquefier");
		
		ModelLoader.setCustomStateMapper(FrogBlocks.generators, new StateMap.Builder().build());
		RegHelper.registerModel(FrogBlocks.generators, "combustion_furnace");
		
		RegHelper.registerModel(FrogBlocks.hybridStorageUnit, 0, "hybrid_storage_unit");
		RegHelper.registerModel(FrogBlocks.hybridStorageUnit, 1, "ultra_hybrid_storage_unit");
		
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidAmmonia, "ammonia");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidArgon, "argon");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidBenzene, "benzene");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidBromine, "bromine");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidCarbonDioxide, "carbon_dioxide");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidCarbonOxide, "carbon_oxide");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidCoalTar, "coal_tar");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidFluorine, "fluorine");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidNitricAcid, "nitric_acid");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidNitrogenOxide, "nitrogen_oxide");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidOxygen, "oxygen");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidSulfurDioxide, "sulfur_dioxide");
		RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidSulfurTrioxide, "sulfur_trioxide");
	}

}
