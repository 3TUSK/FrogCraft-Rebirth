/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 5:24:23 PM, Jul 27, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common;

import frogcraftrebirth.client.gui.GuiAdvChemReactor;
import frogcraftrebirth.client.gui.GuiAirPump;
import frogcraftrebirth.client.gui.GuiCombustionFurnace;
import frogcraftrebirth.client.gui.GuiCondenseTower;
import frogcraftrebirth.client.gui.GuiFluidOutputHatch;
import frogcraftrebirth.client.gui.GuiHybridEStorage;
import frogcraftrebirth.client.gui.GuiLiquefier;
import frogcraftrebirth.client.gui.GuiMPS;
import frogcraftrebirth.client.gui.GuiPyrolyzer;
import frogcraftrebirth.common.gui.ContainerAdvChemReactor;
import frogcraftrebirth.common.gui.ContainerAirPump;
import frogcraftrebirth.common.gui.ContainerCombustionFurnace;
import frogcraftrebirth.common.gui.ContainerCondenseTower;
import frogcraftrebirth.common.gui.ContainerFluidOutputHatch;
import frogcraftrebirth.common.gui.ContainerHybridEStorage;
import frogcraftrebirth.common.gui.ContainerLiquefier;
import frogcraftrebirth.common.gui.ContainerMPS;
import frogcraftrebirth.common.gui.ContainerPyrolyzer;
import frogcraftrebirth.common.tile.TileAdvChemReactor;
import frogcraftrebirth.common.tile.TileAirPump;
import frogcraftrebirth.common.tile.TileCombustionFurnace;
import frogcraftrebirth.common.tile.TileCondenseTower;
import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import frogcraftrebirth.common.tile.TileHSU;
import frogcraftrebirth.common.tile.TileHSUUltra;
import frogcraftrebirth.common.tile.TileLiquefier;
import frogcraftrebirth.common.tile.TileMobilePowerStation;
import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class FrogGuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity aTile = world.getTileEntity(new BlockPos(x, y, z));
		switch(id) {
			case 1: {
				if (aTile instanceof TileHSUUltra)
					return new ContainerHybridEStorage(player.inventory, (TileHSUUltra)aTile);
				if (aTile instanceof TileHSU)
					return new ContainerHybridEStorage(player.inventory, (TileHSU)aTile);
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
				if (aTile instanceof TileMobilePowerStation)
					return new ContainerMPS(player.inventory, (TileMobilePowerStation)aTile);
			}
			case 5: {
				if (aTile instanceof TileAdvChemReactor)
					return new ContainerAdvChemReactor(player.inventory, (TileAdvChemReactor)aTile);
				if (aTile instanceof TileAirPump)
					return new ContainerAirPump(player.inventory, (TileAirPump)aTile);
				if (aTile instanceof TilePyrolyzer)
					return new ContainerPyrolyzer(player.inventory, (TilePyrolyzer)aTile);
				if (aTile instanceof TileLiquefier)
					return new ContainerLiquefier(player.inventory, (TileLiquefier)aTile);
			}
			default : {
				return null;
			}
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity aTile = world.getTileEntity(new BlockPos(x, y, z));
		switch (ID) {
			case 1: {
				if (aTile instanceof TileHSUUltra)
					return new GuiHybridEStorage(player.inventory, (TileHSUUltra)aTile, true);
				if (aTile instanceof TileHSU)
					return new GuiHybridEStorage(player.inventory, (TileHSU)aTile, false);
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
				if (aTile instanceof TileMobilePowerStation)
					return new GuiMPS(player.inventory, (TileMobilePowerStation)aTile);
			}
			case 5: {
				if (aTile instanceof TileAdvChemReactor)
					return new GuiAdvChemReactor(player.inventory, (TileAdvChemReactor)aTile);
				if (aTile instanceof TileAirPump)
					return new GuiAirPump(player.inventory, (TileAirPump)aTile);
				if (aTile instanceof TilePyrolyzer)
					return new GuiPyrolyzer(player.inventory, (TilePyrolyzer)aTile);
				if (aTile instanceof TileLiquefier)
					return new GuiLiquefier(player.inventory, (TileLiquefier)aTile);
			}
			default : {
				return null;
			}
		}
	}

}
