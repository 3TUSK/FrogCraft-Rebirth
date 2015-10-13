package frogcraftrewrite.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import frogcraftrewrite.client.gui.GuiHybridEStorage;
import frogcraftrewrite.client.gui.GuiIndustrialDevice;
import frogcraftrewrite.common.entity.EntityRailgunCoin;
import frogcraftrewrite.common.event.subscribe.ExplosionEventListener;
import frogcraftrewrite.common.gui.ContainerHybridEStorage;
import frogcraftrewrite.common.gui.ContainerIndustrialDevice;
import frogcraftrewrite.common.registry.RegFluid;
import frogcraftrewrite.common.registry.RegFrogItemsBlocks;
import frogcraftrewrite.common.tile.TileFrogInductionalDevice;
import frogcraftrewrite.common.tile.TileHSU;

public class CommonProxy implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		// TODO WTF!
		TileEntity aTile = world.getTileEntity(x, y, z);
		switch(id) {
			case 0:{
				if (aTile instanceof TileFrogInductionalDevice)
					return new ContainerIndustrialDevice(player.inventory, (TileFrogInductionalDevice)aTile);
			}
			case 1:{
				if (aTile instanceof TileHSU)
					System.out.println("The Final Dawn Has Come, LUX ET FUTURA!");
					return new ContainerHybridEStorage(player.inventory, (TileHSU)aTile);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO WTF!
		TileEntity aTile = world.getTileEntity(x, y, z);
		switch (ID) {
			case 0:{
				if (aTile instanceof TileFrogInductionalDevice)
					return new GuiIndustrialDevice(player.inventory, (TileFrogInductionalDevice)aTile);
			}
			case 1:{
				if (aTile instanceof TileHSU)
					return new GuiHybridEStorage(player.inventory, (TileHSU)aTile);
			}
		}
		return null;
	}
	
	public void preInit(FMLPreInitializationEvent event) {
		RegFrogItemsBlocks.preInit();
		
		RegFluid.init();
		
		EntityRegistry.registerModEntity(EntityRailgunCoin.class, "EntityRailgunCoin", 0, frogcraftrewrite.FrogCraftRebirth.instance, 160, 5, true);
	}

	public void init(FMLInitializationEvent event) {
		RegFrogItemsBlocks.init();
	}

	public void postInit(FMLPostInitializationEvent event) {
		RegFrogItemsBlocks.postInit();
		
		MinecraftForge.EVENT_BUS.register(new ExplosionEventListener());
	}

}
