package frogcraftrebirth;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
		modid = FrogAPI.MODID, 
		name = FrogAPI.NAME, 
		version = "@VERSION@", 
		dependencies = FrogAPI.DEPENDING, 
		guiFactory = "frogcraftrebirth.common.lib.config.ConfigGuiFactory", 
		useMetadata = true)
public class FrogCraftRebirth {
	
	@Mod.Instance(FrogAPI.MODID)
	public static FrogCraftRebirth instance;

	@SidedProxy(serverSide = "frogcraftrebirth.common.FrogProxy", clientSide = "frogcraftrebirth.client.FrogProxyClient")
	public static FrogProxy proxy;
	
	public FrogCraftRebirth() {
		net.minecraftforge.fluids.FluidRegistry.enableUniversalBucket();
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void imcInit(FMLInterModComms.IMCEvent event) {
		proxy.imcInit(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		FrogAPI.FROG_LOG.info("FrogCraft has finished loading. The era of chemsitry will begin!");
	}

}
