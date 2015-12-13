package frogcraftrewrite.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import frogcraftrewrite.FrogCraftRebirth;
import frogcraftrewrite.client.render.RenderEntityRailgunCoin;
import frogcraftrewrite.common.CommonProxy;
import frogcraftrewrite.common.entity.EntityRailgunCoin;
import frogcraftrewrite.common.lib.FrogFluids;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		FrogCraftRebirth.FROG_LOG.info("Client side pre-initialization finished");
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		RenderingRegistry.registerEntityRenderingHandler(EntityRailgunCoin.class, new RenderEntityRailgunCoin());

		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		FrogCraftRebirth.FROG_LOG.info("Client side initialization finished");
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		FrogCraftRebirth.FROG_LOG.info("Client side post-initialization finished");
	}
 
	@SubscribeEvent
	public void onTextureStiching(TextureStitchEvent.Pre event) {
		if (event.map.getTextureType() == 0) {
			FrogFluids.ammonia.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/Ammonia"));
			FrogFluids.argon.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/Argon"));
			FrogFluids.benzene.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/Benzene"));
			FrogFluids.bromine.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/Bromine"));
			FrogFluids.carbonOxide.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/CO"));
			FrogFluids.carbonDioxide.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/CO2"));
			FrogFluids.coalTar.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/CoalTar"));
			FrogFluids.fluorine.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/Fluorine"));
			FrogFluids.nitricAcid.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/HNO3"));
			FrogFluids.liquidAir.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/LiquifiedAir"));
			FrogFluids.nitrogenOxide.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/NO"));
			FrogFluids.oxygen.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/Oxygen"));
			FrogFluids.sulfurDioxide.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/SO2"));
			FrogFluids.sulfurTrioxide.setIcons(event.map.registerIcon("frogcraftrewrite:fluids/SO3"));
		}
	}

}
