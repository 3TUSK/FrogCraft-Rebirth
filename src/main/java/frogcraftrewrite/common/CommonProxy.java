package frogcraftrewrite.common;

import static frogcraftrewrite.api.FrogFuelHandler.FUEL_REG;

import java.util.Arrays;
import java.util.LinkedList;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import frogcraftrewrite.api.FrogAPI;
import frogcraftrewrite.api.chemlabimpl.ElementLoader;
import frogcraftrewrite.api.recipes.AdvChemReactorRecipeManager;
import frogcraftrewrite.api.recipes.CondenseTowerRecipeManager;
import frogcraftrewrite.api.recipes.ThermalCrackerRecipeManger;
import frogcraftrewrite.client.gui.GuiAdvChemReactor;
import frogcraftrewrite.client.gui.GuiAirPump;
import frogcraftrewrite.client.gui.GuiCondenseTower;
import frogcraftrewrite.client.gui.GuiFluidOutputHatch;
import frogcraftrewrite.client.gui.GuiHybridEStorage;
import frogcraftrewrite.client.gui.GuiIndustrialDevice;
import frogcraftrewrite.client.gui.GuiThermalCracker;
import frogcraftrewrite.common.entity.EntityRailgunCoin;
import frogcraftrewrite.common.event.FrogEventListener;
import frogcraftrewrite.common.gui.ContainerAdvChemReactor;
import frogcraftrewrite.common.gui.ContainerAirPump;
import frogcraftrewrite.common.gui.ContainerCondenseTower;
import frogcraftrewrite.common.gui.ContainerFluidOutputHatch;
import frogcraftrewrite.common.gui.ContainerHybridEStorage;
import frogcraftrewrite.common.gui.ContainerIndustrialDevice;
import frogcraftrewrite.common.gui.ContainerThermalCracker;
import frogcraftrewrite.common.lib.tile.TileFrogInductionalDevice;
import frogcraftrewrite.common.registry.RegFluid;
//import frogcraftrewrite.common.registry.RegFrogAchievements;
import frogcraftrewrite.common.registry.RegFrogItemsBlocks;
import frogcraftrewrite.common.registry.RegFrogRecipes;
import frogcraftrewrite.common.tile.TileAdvChemReactor;
import frogcraftrewrite.common.tile.TileAirPump;
import frogcraftrewrite.common.tile.TileCondenseTower;
import frogcraftrewrite.common.tile.TileFluidOutputHatch;
import frogcraftrewrite.common.tile.TileHSU;
import frogcraftrewrite.common.tile.TileHSUUltra;
import frogcraftrewrite.common.tile.TileThermalCracker;
import frogcraftrewrite.common.world.FrogWorldGenerator;
import info.tritusk.tritchemlab.matter.Element;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

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
				if (aTile instanceof TileHSUUltra)
					return new ContainerHybridEStorage(player.inventory, (TileHSUUltra)aTile);
			}
			case 2: {
				
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
				if (aTile instanceof TileAirPump)
					return new ContainerAirPump(player.inventory, (TileAirPump)aTile);
				if (aTile instanceof TileThermalCracker)
					return new ContainerThermalCracker(player.inventory, (TileThermalCracker)aTile);
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
				if (aTile instanceof TileHSUUltra)
					return new GuiHybridEStorage(player.inventory, (TileHSUUltra)aTile);
			}
			case 2: {
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
				if (aTile instanceof TileAirPump)
					return new GuiAirPump(player.inventory, (TileAirPump)aTile);
				if (aTile instanceof TileThermalCracker)
					return new GuiThermalCracker(player.inventory, (TileThermalCracker)aTile);
			}
		}
		return null;
	}
	
	public void preInit(FMLPreInitializationEvent event) {
		try {
			FrogAPI.elementsList = new LinkedList<Element>(Arrays.asList(ElementLoader.FROG_PARSER.parseElements(this.getClass().getResourceAsStream("assets/frogcraftrewrite/chemistry/PeriodicTable.xml"), false)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		GameRegistry.registerFuelHandler(FUEL_REG);
		RegFrogItemsBlocks.preInit();
		RegFluid.init();
		EntityRegistry.registerModEntity(EntityRailgunCoin.class, "EntityRailgunCoin", 0, frogcraftrewrite.FrogCraftRebirth.instance, 160, 5, true);
		GameRegistry.registerWorldGenerator(new FrogWorldGenerator(), 1);
		//RegFrogAchievements.init();
	}

	public void init(FMLInitializationEvent event) {
		RegFrogItemsBlocks.init();
		
		FrogAPI.managerACR = new AdvChemReactorRecipeManager();
		FrogAPI.managerCT = new CondenseTowerRecipeManager();
		FrogAPI.managerTC = new ThermalCrackerRecipeManger();
	}

	public void postInit(FMLPostInitializationEvent event) {
		RegFrogItemsBlocks.postInit();
		RegFrogRecipes.init();
		MinecraftForge.EVENT_BUS.register(new FrogEventListener());
	}

}
