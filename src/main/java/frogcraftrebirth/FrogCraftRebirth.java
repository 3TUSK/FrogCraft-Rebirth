package frogcraftrebirth;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogConfig;
import frogcraftrebirth.common.FrogEventListener;
import frogcraftrebirth.common.FrogIMCHanlder;
import frogcraftrebirth.common.FrogItems;
import frogcraftrebirth.common.FrogProxy;
import frogcraftrebirth.common.network.NetworkHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = FrogAPI.MODID, name = FrogAPI.NAME, version = "@VERSION@", dependencies = FrogAPI.DEPENDING, useMetadata = true)
public class FrogCraftRebirth {

	@Instance(FrogAPI.MODID)
	public static FrogCraftRebirth instance;

	@SidedProxy(serverSide = "frogcraftrebirth.common.FrogProxy", clientSide = "frogcraftrebirth.client.FrogProxyClient")
	public static FrogProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		FrogConfig.initConfig(event);

		FrogAPI.frogTab = new CreativeTabs("FrogCraft") {
			@Override
			public Item getTabIconItem() {
				return FrogItems.acwinmillFan;
			}
		};
		NetworkHandler.init();
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new FrogEventListener());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		proxy.init(event);
	}

	@EventHandler
	public void imcInit(FMLInterModComms.IMCEvent event) {
		FrogIMCHanlder.resolveIMCMessage(event.getMessages());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		FrogAPI.FROG_LOG.info("FrogCraft has finished loading. The era of chemsitry will begin!");
	}

}
