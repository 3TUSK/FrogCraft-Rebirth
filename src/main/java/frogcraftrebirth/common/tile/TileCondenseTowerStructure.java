/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 3:25:33 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.api.tile.ICondenseTowerPart;
import frogcraftrebirth.common.lib.tile.TileFrog;
import ic2.core.energy.grid.Tile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileCondenseTowerStructure extends TileFrog implements ICondenseTowerPart {

	private ICondenseTowerCore mainBlock;
	
	@Override
	public void behave() {
		
	}
	
	@Override
	public ICondenseTowerCore getMainBlock() {
		return mainBlock;
	}
	
	@Override
	public void setMainBlock(@Nullable ICondenseTowerCore core) {
		this.mainBlock = core;
	}
	
	@Override
	public boolean isFunctional() {
		return false;
	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		
	}

	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		
	}

}
