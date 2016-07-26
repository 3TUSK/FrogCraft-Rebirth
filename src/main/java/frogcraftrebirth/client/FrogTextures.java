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
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FrogTextures {
	
	public static void initFrogItemsTexture() {
		RegHelper.registerModel(FrogItems.coolantAmmonia60K, "AmmoniaCoolant60K");
		RegHelper.registerModel(FrogItems.coolantAmmonia180K, "AmmoniaCoolant180K");
		RegHelper.registerModel(FrogItems.coolantAmmonia360K, "AmmoniaCoolant360K");
		RegHelper.registerModel(FrogItems.decayBatteryPlutoium, "DecayBatteryPlutoium");
		RegHelper.registerModel(FrogItems.decayBatteryThorium, "DecayBatteryThorium");
		RegHelper.registerModel(FrogItems.decayBatteryUranium, "DecayBatteryUranium");
		RegHelper.registerModel(FrogItems.ionCannon);
		RegHelper.registerModel(FrogItems.ionCannonFrame);
		RegHelper.registerModel(FrogItems.jinkela, "GoldClod");
		
		RegHelper.registerModel(FrogItems.tiberium, 0, "TiberiumRed");
		RegHelper.registerModel(FrogItems.tiberium, 1, "TiberiumBlue");
		RegHelper.registerModel(FrogItems.tiberium, 2, "TiberiumGreen");
		
		RegHelper.registerModel(FrogItems.itemReactionModule, 0, "ModuleHeating");
		RegHelper.registerModel(FrogItems.itemReactionModule, 1, "ModuleElectrolyze");
		RegHelper.registerModel(FrogItems.itemReactionModule, 2, "ModuleAmmonia");
		RegHelper.registerModel(FrogItems.itemReactionModule, 3, "ModuleSulfuricAcid");
		
		RegHelper.registerModel(FrogItems.itemIngot, 0, "Potassium");
		RegHelper.registerModel(FrogItems.itemIngot, 1, "Phosphorus");
		RegHelper.registerModel(FrogItems.itemIngot, 2, "NaturalGasHydrate");
		RegHelper.registerModel(FrogItems.itemIngot, 3, "Briquette");
		RegHelper.registerModel(FrogItems.itemIngot, 4, "ShatteredCoalCoke");
		
		final String[] damnitSubNames = {"Al2O3", "CaF2", "CaO", "CaOH2", "Carnallite", "CaSiO3", "Dewalquite", "Fluorapatite", "KCl", "Magnalium", "MgBr2", "NH4NO3", "TiO2", "Urea", "V2O5"};
		for (int index = 0; index < damnitSubNames.length; index++) {
			RegHelper.registerModel(FrogItems.itemDust, index, "dust/" + damnitSubNames[index]);
		}
	}
	
	public static void initFrogBlocksTexture() {
		RegHelper.registerModel(FrogBlocks.frogOres, 0, "Carnallite");
		RegHelper.registerModel(FrogBlocks.frogOres, 1, "Dewalquite");
		RegHelper.registerModel(FrogBlocks.frogOres, 2, "Fluorapatite");
		RegHelper.registerModel(FrogBlocks.tiberium, 0);
		RegHelper.registerModel(FrogBlocks.tiberium, 1);
		RegHelper.registerModel(FrogBlocks.tiberium, 2);
		
		RegHelper.registerModel(FrogBlocks.mobilePowerStation, "MPS");
		
		RegHelper.registerModel(FrogBlocks.condenseTowerPart, 0, "condensetower/Core");
		RegHelper.registerModel(FrogBlocks.condenseTowerPart, 1, "condensetower/Cylinder");
		RegHelper.registerModel(FrogBlocks.condenseTowerPart, 2, "condensetower/Output");
		
		RegHelper.registerModel(FrogBlocks.machines, 0, "AdvancedChemicalReactor");
		RegHelper.registerModel(FrogBlocks.machines, 1, "AirPump");
		RegHelper.registerModel(FrogBlocks.machines, 2, "Liquefier");
		RegHelper.registerModel(FrogBlocks.machines, 3, "Pyrolyzer");
		
		RegHelper.registerModel(FrogBlocks.generators, "CombustionGenerator");
		
		RegHelper.registerModel(FrogBlocks.hybridStorageUnit, 0, "HSU");
		RegHelper.registerModel(FrogBlocks.hybridStorageUnit, 1, "UHSU");
		
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
