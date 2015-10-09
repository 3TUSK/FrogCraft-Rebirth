package frogcraftrewrite.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import frogcraftrewrite.client.render.RenderEntityRailgunCoin;
import frogcraftrewrite.common.CommonProxy;
import frogcraftrewrite.common.entity.EntityRailgunCoin;

public class ClientProxy extends CommonProxy{
	
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}
	
	public void init(FMLInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityRailgunCoin.class, new RenderEntityRailgunCoin());
		//super.init(event);	
	}

}
