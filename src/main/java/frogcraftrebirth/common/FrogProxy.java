package frogcraftrebirth.common;

import static frogcraftrebirth.api.FrogFuelHandler.FUEL_REG;

import java.util.Arrays;
import java.util.LinkedList;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.ICompatModuleFrog;
import frogcraftrebirth.api.impl.chemlab.ElementLoader;
import frogcraftrebirth.api.recipes.AdvChemReactorRecipeManager;
import frogcraftrebirth.api.recipes.CondenseTowerRecipeManager;
import frogcraftrebirth.api.recipes.PyrolyzerRecipeManger;
import frogcraftrebirth.client.gui.GuiAdvChemReactor;
import frogcraftrebirth.client.gui.GuiAirPump;
import frogcraftrebirth.client.gui.GuiCombustionFurnace;
import frogcraftrebirth.client.gui.GuiCondenseTower;
import frogcraftrebirth.client.gui.GuiFluidOutputHatch;
import frogcraftrebirth.client.gui.GuiHybridEStorage;
import frogcraftrebirth.client.gui.GuiIndustrialDevice;
import frogcraftrebirth.client.gui.GuiPyrolyzer;
import frogcraftrebirth.common.entity.EntityRailgunCoin;
import frogcraftrebirth.common.gui.ContainerAdvChemReactor;
import frogcraftrebirth.common.gui.ContainerAirPump;
import frogcraftrebirth.common.gui.ContainerCombustionFurnace;
import frogcraftrebirth.common.gui.ContainerCondenseTower;
import frogcraftrebirth.common.gui.ContainerFluidOutputHatch;
import frogcraftrebirth.common.gui.ContainerHybridEStorage;
import frogcraftrebirth.common.gui.ContainerIndustrialDevice;
import frogcraftrebirth.common.gui.ContainerPyrolyzer;
import frogcraftrebirth.common.registry.RegFluid;
import frogcraftrebirth.common.registry.RegFrogAchievements;
import frogcraftrebirth.common.registry.RegFrogItemsBlocks;
import frogcraftrebirth.common.registry.RegFrogRecipes;
import frogcraftrebirth.common.tile.TileAdvChemReactor;
import frogcraftrebirth.common.tile.TileAirPump;
import frogcraftrebirth.common.tile.TileCombustionFurnace;
import frogcraftrebirth.common.tile.TileCondenseTower;
import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import frogcraftrebirth.common.tile.TileFrogInductionalDevice;
import frogcraftrebirth.common.tile.TileHSU;
import frogcraftrebirth.common.tile.TileHSUUltra;
import frogcraftrebirth.common.tile.TilePyrolyzer;
import frogcraftrebirth.common.world.FrogWorldGenerator;
import info.tritusk.tritchemlab.matter.Element;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class FrogProxy implements IGuiHandler {

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
				if (aTile instanceof TileCombustionFurnace)
					return new ContainerCombustionFurnace(player.inventory, (TileCombustionFurnace)aTile);
			}
			case 4: {
				
			}
			case 5: {
				if (aTile instanceof TileAdvChemReactor)
					return new ContainerAdvChemReactor(player.inventory, (TileAdvChemReactor)aTile);
				if (aTile instanceof TileAirPump)
					return new ContainerAirPump(player.inventory, (TileAirPump)aTile);
				if (aTile instanceof TilePyrolyzer)
					return new ContainerPyrolyzer(player.inventory, (TilePyrolyzer)aTile);
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
				if (aTile instanceof TileCombustionFurnace)
					return new GuiCombustionFurnace(player.inventory, (TileCombustionFurnace)aTile);
			}
			case 4: {
				
			}
			case 5: {
				if (aTile instanceof TileAdvChemReactor)
					return new GuiAdvChemReactor(player.inventory, (TileAdvChemReactor)aTile);
				if (aTile instanceof TileAirPump)
					return new GuiAirPump(player.inventory, (TileAirPump)aTile);
				if (aTile instanceof TilePyrolyzer)
					return new GuiPyrolyzer(player.inventory, (TilePyrolyzer)aTile);
			}
		}
		return null;
	}
	
	public void preInit(FMLPreInitializationEvent event) {
		try {
			FrogAPI.elementsList = new LinkedList<Element>(Arrays.asList(ElementLoader.FROG_PARSER.parseElements(this.getClass().getResourceAsStream("assets/frogcraftrewrite/tritchemlab/PeriodicTable.xml"), false)));
		} catch (Exception e) {}
		GameRegistry.registerFuelHandler(FUEL_REG);
		RegFrogItemsBlocks.preInit();
		RegFluid.init();
		EntityRegistry.registerModEntity(EntityRailgunCoin.class, "EntityRailgunCoin", 0, frogcraftrebirth.FrogCraftRebirth.instance, 160, 5, true);
		GameRegistry.registerWorldGenerator(new FrogWorldGenerator(), 1);
		RegFrogAchievements.init();
		for (ICompatModuleFrog module : ICompatModuleFrog.compats.values()) {
			module.preInit();
		}
	}

	public void init(FMLInitializationEvent event) {
		RegFrogItemsBlocks.init();
		FrogAPI.managerACR = new AdvChemReactorRecipeManager();
		FrogAPI.managerCT = new CondenseTowerRecipeManager();
		FrogAPI.managerPyrolyzer = new PyrolyzerRecipeManger();
		RegFrogRecipes.init();
		for (ICompatModuleFrog module : ICompatModuleFrog.compats.values()) {
			module.init();
		}
	}

	public void postInit(FMLPostInitializationEvent event) {
		RegFrogItemsBlocks.postInit();
		RegFrogRecipes.postInit();
		MinecraftForge.EVENT_BUS.register(new FrogEventListener());
		for (ICompatModuleFrog module : ICompatModuleFrog.compats.values()) {
			module.postInit();
		}
	}

}
