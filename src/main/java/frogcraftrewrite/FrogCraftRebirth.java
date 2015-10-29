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
import frogcraftrewrite.common.lib.config.ConfigMain;
import frogcraftrewrite.common.lib.FrogReference;
import frogcraftrewrite.common.event.subscribe.ExplosionEventListener;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = FrogReference.MODID, name = FrogReference.NAME, dependencies = FrogReference.DEPENDING, useMetadata = true)
public class FrogCraftRebirth {
	
	@Instance("FrogCraftRebirth")
	public static FrogCraftRebirth instance;
	
	@SidedProxy(serverSide = "frogcraftrewrite.common.CommonProxy", clientSide = "frogcraftrewrite.client.ClientProxy")
	public static CommonProxy proxy;
	
	public static Logger frogLogger;
	
	public static final CreativeTabs TAB_FC = new CreativeTabs("FrogCraft") {
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
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		frogLogger = LogManager.getLogger("FrogCraft-Rebirth");
		//instance = this;
		
		File configDir = new File(event.getModConfigurationDirectory(), FrogAPI.MODID);
		if (!configDir.exists()) configDir.mkdirs();
		ConfigMain.initMainConfig(new File(configDir, "FrogMain.cfg"));
		File reactionDir = new File(configDir, "reactions");
		if (!reactionDir.exists()) reactionDir.mkdirs(); //Prepare for extract xml configuration from jar
		
		GameRegistry.registerFuelHandler(FUEL_REG);
		
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		frogLogger.debug("F*** loading");
		MinecraftForge.EVENT_BUS.register(new ExplosionEventListener());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		FUEL_REG.reg(Items.nether_star, 2000);
		
		proxy.postInit(event);
	}
	
}
