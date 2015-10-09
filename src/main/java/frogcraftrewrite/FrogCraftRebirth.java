package frogcraftrewrite;

import static frogcraftrewrite.api.FrogFuelHandler.FUEL_REG;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import frogcraftrewrite.api.Constants;
import frogcraftrewrite.common.CommonProxy;
import frogcraftrewrite.common.Config;
import frogcraftrewrite.common.event.subscribe.ExplosionEventListener;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Constants.MODID, name = Constants.NAME, dependencies = Constants.DEPENDING, useMetadata = true)
public class FrogCraftRebirth {
	
	@Instance("FrogCraftRebirth")
	public static FrogCraftRebirth instance;
	
	@SidedProxy(serverSide = "frogcraftrewrite.common.CommonProxy", clientSide = "frogcraftrewrite.client.ClientProxy")
	public static CommonProxy proxy;
	
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
		instance = this;
		proxy = new CommonProxy();
		GameRegistry.registerFuelHandler(FUEL_REG);
		Config.initConfig(event.getSuggestedConfigurationFile());
		
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ExplosionEventListener());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		FUEL_REG.reg(Items.nether_star, 2000);
	}
	
	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		//TODO i don't know!
	}
}
