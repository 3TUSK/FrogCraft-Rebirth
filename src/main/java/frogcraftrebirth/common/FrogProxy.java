package frogcraftrebirth.common;

import java.util.Map.Entry;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.ICompatModuleFrog;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.common.compat.techreborn.CompatTechReborn;
import frogcraftrebirth.common.entity.EntityIonCannonBeam;
import frogcraftrebirth.common.lib.AdvChemRecRecipeManager;
import frogcraftrebirth.common.lib.CondenseTowerRecipeManager;
import frogcraftrebirth.common.lib.PyrolyzerRecipeManger;
import frogcraftrebirth.common.lib.config.ConfigMain;
import frogcraftrebirth.common.network.NetworkHandler;
import frogcraftrebirth.common.potion.PotionTiberium;
import frogcraftrebirth.common.registry.RegFluid;
import frogcraftrebirth.common.registry.RegFrogAchievements;
import frogcraftrebirth.common.registry.RegFrogItemsBlocks;
import frogcraftrebirth.common.registry.RegFrogRecipes;
import frogcraftrebirth.common.world.FrogWorldGenerator;
import ic2.api.item.IC2Items;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FrogProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ConfigMain.init(event);
		GameRegistry.registerFuelHandler(FrogAPI.FUEL_REG);
		RegFrogItemsBlocks.init();
		RegFluid.init(); 
		FrogAPI.potionTiberium = new PotionTiberium(0x66CCFF);
		GameRegistry.<Potion>register(FrogAPI.potionTiberium);
		EntityRegistry.registerModEntity(EntityIonCannonBeam.class, "EntityRailgunCoin", 0, frogcraftrebirth.FrogCraftRebirth.instance, 160, 5, true);
		GameRegistry.registerWorldGenerator(new FrogWorldGenerator(), 3);
		RegFrogAchievements.init();
		if (Loader.isModLoaded("techreborn")) {
			try {
				Class.forName("frogcraftrebirth.common.compat.techreborn.CompatTechReborn").getDeclaredMethod("preInit").invoke(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			FrogAPI.registerFrogCompatModule("techreborn", new CompatTechReborn());
		}
	}

	public void init(FMLInitializationEvent event) {
		NetworkHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(FrogCraftRebirth.instance, new FrogGuiHandler());
		FrogAPI.managerACR = new AdvChemRecRecipeManager();
		FrogAPI.managerCT = new CondenseTowerRecipeManager();
		FrogAPI.managerPyrolyzer = new PyrolyzerRecipeManger();
		RegFrogRecipes.init();
	}

	public void postInit(FMLPostInitializationEvent event) {
		RegFrogRecipes.postInit();
		MPSUpgradeManager.INSTANCE.registerSolarUpgrade(IC2Items.getItem("te", "solar_generator"));
		MPSUpgradeManager.INSTANCE.registerStorageUpgrade(IC2Items.getItem("upgrade", "energy_storage"), 10000);
		MPSUpgradeManager.INSTANCE.registerVoltageUpgrades(IC2Items.getItem("upgrade", "transformer"), 1);
		MinecraftForge.EVENT_BUS.register(new FrogEventListener());
		for (Entry<String, ICompatModuleFrog> module : FrogAPI.COMPATS.entrySet()) {
			if (Loader.isModLoaded(module.getKey()))
				module.getValue().init();
			else
				FrogAPI.FROG_LOG.info("The compat module '%s' is not loaded because the mod is not present. It may be a typo, but who knows?", module.getKey());
		}
	}

}
