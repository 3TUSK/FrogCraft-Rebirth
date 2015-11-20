package frogcraftrewrite;

import static frogcraftrewrite.api.FrogFuelHandler.FUEL_REG;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import frogcraftrewrite.api.FrogAPI;
import frogcraftrewrite.common.CommonProxy;
import frogcraftrewrite.common.event.FrogEventListener;
import frogcraftrewrite.common.lib.FrogRef;
import frogcraftrewrite.common.lib.config.ConfigMain;
import frogcraftrewrite.common.network.NetworkHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = FrogRef.MODID, name = FrogRef.NAME, version = FrogRef.VERSION, dependencies = FrogRef.DEPENDING, useMetadata = true)
public class FrogCraftRebirth {
	
	@Instance(FrogRef.MODID)
	public static FrogCraftRebirth instance;
	
	@SidedProxy(serverSide = "frogcraftrewrite.common.CommonProxy", clientSide = "frogcraftrewrite.client.ClientProxy")
	public static CommonProxy proxy;
	
	public static Logger frogLogger = LogManager.getLogger("FrogCraft-Rebirth");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {	
		File configDir = new File(event.getModConfigurationDirectory(), FrogAPI.MODID);
		if (!configDir.exists()) configDir.mkdirs();
		ConfigMain.initMainConfig(new File(configDir, "FrogMain.cfg"));
		File reactionDir = new File(configDir, "reactions");
		if (!reactionDir.exists()) reactionDir.mkdirs(); //Prepare for extract xml configuration from jar
		
		FrogAPI.frogTab = new CreativeTabs("FrogCraft") {
			@SuppressWarnings("unused") private static final String IDENTITY = "0x0000002A"; //42.
			Item frogLogo;	
			{
				frogLogo = new Item().setTextureName("frogcraftrewrite:coin").setUnlocalizedName("frogLogo");
				cpw.mods.fml.common.registry.GameRegistry.registerItem(frogLogo, "frogLogo");
				codechicken.nei.api.API.hideItem(new net.minecraft.item.ItemStack(frogLogo, 1));
			}
			@Override
			public Item getTabIconItem() {
				return frogLogo;
			}
		};
		
		GameRegistry.registerFuelHandler(FUEL_REG);
		
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
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		try {
			java.lang.reflect.Field identityCheck = FrogAPI.frogTab.getClass().getField("IDENTITY");
			identityCheck.setAccessible(true);
			String value = (String)identityCheck.get(null);
			if ("0x0000002A".equals(value) && Integer.parseInt(value, 16) == 42)
				frogLogger.debug("Identity check finished.");
			else
				throw new RuntimeException("FrogCraft's creative tab has been overrided! THIS IS ILLEGAL AND FROGCRAFT HAS NO RESPONSIBILITY ON THIS EXCEPTION!");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		frogLogger.info("FrogCraft has finished loading. The era of chemsitry will begin!");
	}
	
}
