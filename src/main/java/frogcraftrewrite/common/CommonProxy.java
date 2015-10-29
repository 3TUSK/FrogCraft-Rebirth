package frogcraftrewrite.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
//import tritusk.trichemistry.matter.Element;

//import java.util.Arrays;
//import java.util.LinkedList;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import frogcraftrewrite.api.FrogAPI;
import frogcraftrewrite.api.recipes.AdvChemReactorRecipeManager;
import frogcraftrewrite.api.recipes.CombustionFurnaceRecipeManager;
import frogcraftrewrite.api.recipes.CondenseTowerRecipeManager;
import frogcraftrewrite.api.recipes.ThermalCrackerRecipeManger;
//import frogcraftrewrite.api.FrogAPI;
//import frogcraftrewrite.api.trichemcompat.ElementLoader;
import frogcraftrewrite.client.gui.GuiAdvChemReactor;
import frogcraftrewrite.client.gui.GuiAirPump;
import frogcraftrewrite.client.gui.GuiCondenseTower;
import frogcraftrewrite.client.gui.GuiFluidOutputHatch;
import frogcraftrewrite.client.gui.GuiHybridEStorage;
import frogcraftrewrite.client.gui.GuiIndustrialDevice;
import frogcraftrewrite.common.entity.EntityRailgunCoin;
import frogcraftrewrite.common.event.subscribe.ExplosionEventListener;
import frogcraftrewrite.common.gui.ContainerAdvChemReactor;
import frogcraftrewrite.common.gui.ContainerAirPump;
import frogcraftrewrite.common.gui.ContainerCondenseTower;
import frogcraftrewrite.common.gui.ContainerFluidOutputHatch;
import frogcraftrewrite.common.gui.ContainerHybridEStorage;
import frogcraftrewrite.common.gui.ContainerIndustrialDevice;
import frogcraftrewrite.common.registry.RegFluid;
import frogcraftrewrite.common.registry.RegFrogItemsBlocks;
import frogcraftrewrite.common.tile.TileAdvChemReactor;
import frogcraftrewrite.common.tile.TileAirPump;
import frogcraftrewrite.common.tile.TileCondenseTower;
import frogcraftrewrite.common.tile.TileFluidOutputHatch;
import frogcraftrewrite.common.tile.TileFrogInductionalDevice;
import frogcraftrewrite.common.tile.TileHSU;
import frogcraftrewrite.common.tile.TileUHSU;

public class CommonProxy implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity aTile = world.getTileEntity(x, y, z);
		switch(id) {
			case 0: {
				if (aTile instanceof TileFrogInductionalDevice)
					return new ContainerIndustrialDevice(player.inventory, (TileFrogInductionalDevice)aTile);
			}
			case 1: {
				if (aTile instanceof TileHSU)
					return new ContainerHybridEStorage(player.inventory, (TileHSU)aTile);
				if (aTile instanceof TileUHSU)
					return new ContainerHybridEStorage(player.inventory, (TileUHSU)aTile);
			}
			case 2: {
				if (aTile instanceof TileAirPump)
					return new ContainerAirPump(player.inventory, (TileAirPump)aTile);
				if (aTile instanceof TileCondenseTower)
					return new ContainerCondenseTower(player.inventory, (TileCondenseTower)aTile);
				if (aTile instanceof TileFluidOutputHatch)
					return new ContainerFluidOutputHatch(player.inventory, (TileFluidOutputHatch)aTile);
			}
			case 3: {
				
			}
			case 4: {
				
			}
			case 5: {
				if (aTile instanceof TileAdvChemReactor)
					return new ContainerAdvChemReactor(player.inventory, (TileAdvChemReactor)aTile);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity aTile = world.getTileEntity(x, y, z);
		switch (ID) {
			case 0: {
				if (aTile instanceof TileFrogInductionalDevice)
					return new GuiIndustrialDevice(player.inventory, (TileFrogInductionalDevice)aTile);
			}
			case 1: {
				if (aTile instanceof TileHSU)
					return new GuiHybridEStorage(player.inventory, (TileHSU)aTile);
				if (aTile instanceof TileUHSU)
					return new GuiHybridEStorage(player.inventory, (TileUHSU)aTile);
			}
			case 2: {
				if (aTile instanceof TileAirPump)
					return new GuiAirPump(player.inventory, (TileAirPump)aTile);
				if (aTile instanceof TileCondenseTower)
					return new GuiCondenseTower(player.inventory, (TileCondenseTower)aTile);
				if (aTile instanceof TileFluidOutputHatch)
					return new GuiFluidOutputHatch(player.inventory, (TileFluidOutputHatch)aTile);
			}
			case 3: {
				
			}
			case 4: {
				
			}
			case 5: {
				if (aTile instanceof TileAdvChemReactor)
					return new GuiAdvChemReactor(player.inventory, (TileAdvChemReactor)aTile);
			}
		}
		return null;
	}
	
	public void preInit(FMLPreInitializationEvent event) {
		//FrogAPI.elementsList = new LinkedList<Element>(Arrays.asList(ElementLoader.FROG_PARSER.parseElements(this.getClass().getResourceAsStream("assets/frogcraftrewrite/chemistry/PeriodicTable.xml"), false)));
		
		RegFrogItemsBlocks.preInit();
		
		RegFluid.init();
		
		EntityRegistry.registerModEntity(EntityRailgunCoin.class, "EntityRailgunCoin", 0, frogcraftrewrite.FrogCraftRebirth.instance, 160, 5, true);
	}

	public void init(FMLInitializationEvent event) {
		RegFrogItemsBlocks.init();
		
		FrogAPI.managerACR = new AdvChemReactorRecipeManager();
		FrogAPI.managerCFG = new CombustionFurnaceRecipeManager();
		FrogAPI.managerCT = new CondenseTowerRecipeManager();
		FrogAPI.managerTC = new ThermalCrackerRecipeManger();
	}

	public void postInit(FMLPostInitializationEvent event) {
		RegFrogItemsBlocks.postInit();
		
		MinecraftForge.EVENT_BUS.register(new ExplosionEventListener());
	}

}
