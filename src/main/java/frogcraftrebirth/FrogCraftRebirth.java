package frogcraftrebirth;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogConfig;
import frogcraftrebirth.common.FrogEventListener;
import frogcraftrebirth.common.FrogIMCHanlder;
import frogcraftrebirth.common.FrogProxy;
import frogcraftrebirth.common.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = FrogAPI.MODID, name = FrogAPI.NAME, version = "@VERSION@", dependencies = FrogAPI.DEPENDING, useMetadata = true)
public class FrogCraftRebirth {

	@Mod.Instance(FrogAPI.MODID)
	public static FrogCraftRebirth instance;

	@SidedProxy(serverSide = "frogcraftrebirth.common.FrogProxy", clientSide = "frogcraftrebirth.client.FrogProxyClient")
	public static FrogProxy proxy;
	
	public FrogCraftRebirth() {
		instance = this;
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		FrogConfig.initConfig(event);
		NetworkHandler.init();
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new FrogEventListener());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		proxy.init(event);
	}

	@Mod.EventHandler
	public void imcInit(FMLInterModComms.IMCEvent event) {
		FrogIMCHanlder.resolveIMCMessage(event.getMessages());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		FrogAPI.FROG_LOG.info("FrogCraft has finished loading. The era of chemsitry will begin!");
	}

}
