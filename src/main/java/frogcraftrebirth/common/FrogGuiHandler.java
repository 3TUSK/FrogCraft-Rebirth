/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 5:24:23 PM, Jul 27, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common;

import frogcraftrebirth.common.tile.IHasGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class FrogGuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity aTile = world.getTileEntity(new BlockPos(x, y, z));
		if (aTile instanceof IHasGui) {
			return ((IHasGui)aTile).getGuiContainer(world, player);
		} else {
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity aTile = world.getTileEntity(new BlockPos(x, y, z));
		if (aTile instanceof IHasGui) {
			return ((IHasGui)aTile).getGui(world, player);
		} else {
			return null;
		}
	}

}
