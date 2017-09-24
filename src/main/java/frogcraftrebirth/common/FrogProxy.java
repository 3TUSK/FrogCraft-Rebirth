package frogcraftrebirth.common;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.entity.EntityIonCannonBeam;
import frogcraftrebirth.common.lib.AdvChemRecRecipeManager;
import frogcraftrebirth.common.lib.CondenseTowerRecipeManager;
import frogcraftrebirth.common.lib.PyrolyzerRecipeManger;
import frogcraftrebirth.common.network.NetworkHandler;
import frogcraftrebirth.common.registry.RegFrogRecipes;
import frogcraftrebirth.common.world.FrogWorldGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class FrogProxy {

	public void preInit(FMLPreInitializationEvent event) {
		NetworkHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(FrogCraftRebirth.getInstance(), new FrogGuiHandler());
		EntityRegistry.registerModEntity(new ResourceLocation(FrogAPI.MODID, "ion_cannon_beam"), EntityIonCannonBeam.class, "EntityRailgunCoin", 0, FrogCraftRebirth.getInstance(), 160, 5, true);
	}

	public void init(FMLInitializationEvent event) {
		FrogAPI.managerACR = new AdvChemRecRecipeManager();
		FrogAPI.managerCT = new CondenseTowerRecipeManager();
		FrogAPI.managerPyrolyzer = new PyrolyzerRecipeManger();
		RegFrogRecipes.init();
		if (FrogConfig.enableWorldGen) {
			MinecraftForge.ORE_GEN_BUS.register(new FrogWorldGenerator());
		}
	}
	
	public void imcInit(FMLInterModComms.IMCEvent event) {
		FrogIMCHandler.resolveIMCMessage(event.getMessages());
	}

	public void postInit(FMLPostInitializationEvent event) {
		RegFrogRecipes.postInit();
		MinecraftForge.EVENT_BUS.register(new FrogEventListener());
	}

}
