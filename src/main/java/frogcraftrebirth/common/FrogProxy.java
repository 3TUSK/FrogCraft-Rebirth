package frogcraftrebirth.common;

import java.util.Map.Entry;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.ICompatModuleFrog;
import frogcraftrebirth.client.gui.GuiAdvChemReactor;
import frogcraftrebirth.client.gui.GuiAirPump;
import frogcraftrebirth.client.gui.GuiCombustionFurnace;
import frogcraftrebirth.client.gui.GuiCondenseTower;
import frogcraftrebirth.client.gui.GuiFluidOutputHatch;
import frogcraftrebirth.client.gui.GuiHybridEStorage;
import frogcraftrebirth.client.gui.GuiInductionalDevice;
import frogcraftrebirth.client.gui.GuiPyrolyzer;
import frogcraftrebirth.common.compat.gregtech.CompatGregTech;
import frogcraftrebirth.common.entity.EntityRailgunCoin;
import frogcraftrebirth.common.gui.ContainerAdvChemReactor;
import frogcraftrebirth.common.gui.ContainerAirPump;
import frogcraftrebirth.common.gui.ContainerCombustionFurnace;
import frogcraftrebirth.common.gui.ContainerCondenseTower;
import frogcraftrebirth.common.gui.ContainerFluidOutputHatch;
import frogcraftrebirth.common.gui.ContainerHybridEStorage;
//import frogcraftrebirth.common.gui.ContainerInductionalDevice;
import frogcraftrebirth.common.gui.ContainerPyrolyzer;
import frogcraftrebirth.common.lib.AdvChemRecRecipeManager;
import frogcraftrebirth.common.lib.CondenseTowerRecipeManager;
import frogcraftrebirth.common.lib.PyrolyzerRecipeManger;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class FrogProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity aTile = world.getTileEntity(x, y, z);
		switch(id) {
			/*case 0: {
				if (aTile instanceof TileFrogInductionalDevice)
					return new ContainerIndustrialDevice(player.inventory, (TileFrogInductionalDevice)aTile);
			}*/
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
			default : {
				return null;
			}
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity aTile = world.getTileEntity(x, y, z);
		switch (ID) {
			case 0: {
				if (aTile instanceof TileFrogInductionalDevice)
					return new GuiInductionalDevice(player.inventory, (TileFrogInductionalDevice)aTile);
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
			default : {
				return null;
			}
		}
	}
	
	public void preInit(FMLPreInitializationEvent event) {
		GameRegistry.registerFuelHandler(FrogAPI.FUEL_REG);
		RegFrogItemsBlocks.preInit();
		RegFluid.init();
		EntityRegistry.registerModEntity(EntityRailgunCoin.class, "EntityRailgunCoin", 0, frogcraftrebirth.FrogCraftRebirth.instance, 160, 5, true);
		GameRegistry.registerWorldGenerator(new FrogWorldGenerator(), 1);
		RegFrogAchievements.init();
		FrogAPI.registerFrogCompatModule("gregtech", new CompatGregTech());
		FrogAPI.registerFrogCompatModule("academy-craft", () -> {
			FrogAPI.FROG_LOG.warn("AcademyCraft has been detected, Frog's railgun will be disabled.");
		});
	}

	public void init(FMLInitializationEvent event) {
		RegFrogItemsBlocks.init();
		FrogAPI.managerACR = new AdvChemRecRecipeManager();
		FrogAPI.managerCT = new CondenseTowerRecipeManager();
		FrogAPI.managerPyrolyzer = new PyrolyzerRecipeManger();
		RegFrogRecipes.init();
		for (Entry<String, ICompatModuleFrog> module : FrogAPI.COMPATS.entrySet()) {
			if (Loader.isModLoaded(module.getKey()))
				module.getValue().init();
			else
				FrogAPI.FROG_LOG.info("The compat module '" + module.getKey() + "' is not loaded because the mod is not present. It may be a typo, but who knows?");
		}
	}

	public void postInit(FMLPostInitializationEvent event) {
		RegFrogItemsBlocks.postInit();
		RegFrogRecipes.postInit();
		MinecraftForge.EVENT_BUS.register(new FrogEventListener());
	}

}
