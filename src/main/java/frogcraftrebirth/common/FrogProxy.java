package frogcraftrebirth.common;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.entity.EntityIonCannonBeam;
import frogcraftrebirth.common.lib.AdvChemRecRecipeManager;
import frogcraftrebirth.common.lib.CondenseTowerRecipeManager;
import frogcraftrebirth.common.lib.PyrolyzerRecipeManger;
import frogcraftrebirth.common.lib.config.ConfigMain;
import frogcraftrebirth.common.network.NetworkHandler;
import frogcraftrebirth.common.registry.RegFluid;
import frogcraftrebirth.common.registry.RegFrogAchievements;
import frogcraftrebirth.common.registry.RegFrogRecipes;
import frogcraftrebirth.common.world.FrogWorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FrogProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ConfigMain.init(event);
		GameRegistry.registerFuelHandler(FrogAPI.FUEL_REG);
		EntityRegistry.registerModEntity(EntityIonCannonBeam.class, "EntityRailgunCoin", 0, FrogCraftRebirth.getInstance(), 160, 5, true);
		RegFrogAchievements.init();
	}

	public void init(FMLInitializationEvent event) {
		NetworkHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(FrogCraftRebirth.getInstance(), new FrogGuiHandler());
		FrogAPI.managerACR = new AdvChemRecRecipeManager();
		FrogAPI.managerCT = new CondenseTowerRecipeManager();
		FrogAPI.managerPyrolyzer = new PyrolyzerRecipeManger();
		RegFrogRecipes.init();
		FrogWorldGenerator generator = new FrogWorldGenerator();
		MinecraftForge.TERRAIN_GEN_BUS.register(generator);
		MinecraftForge.ORE_GEN_BUS.register(generator);
	}
	
	public void imcInit(FMLInterModComms.IMCEvent event) {
		FrogIMCHandler.resolveIMCMessage(event.getMessages());
	}

	public void postInit(FMLPostInitializationEvent event) {
		RegFrogRecipes.postInit();
		MinecraftForge.EVENT_BUS.register(new FrogEventListener());
		FrogAPI.COMPATS.forEach((modid, module) -> {
			if (Loader.isModLoaded(modid))
				module.init();
			else
				FrogAPI.FROG_LOG.info("The compat module '%s' is not loaded because the mod is not present. It may be a typo, but who knows?", modid);
		});
	}

}
