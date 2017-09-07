package frogcraftrebirth.common.tile;

import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.tile.TileFrog;
import ic2.api.energy.tile.IHeatSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TileAdvBlastFurnace extends TileFrog implements IHasGui, IHasWork, ITickable {

	private int heat;
	private EnumFacing heatInput;

	@Override
	public boolean isWorking() {
		return false;
	}

	@Override
	public void update() {
		if (getWorld().isRemote) {
			getWorld().markBlockRangeForRenderUpdate(getPos(), getPos());
		} else {
			TileEntity tile = getWorld().getTileEntity(getPos().offset(heatInput));
			if (tile instanceof IHeatSource) {
				this.heat += ((IHeatSource)tile).requestHeat(heatInput.getOpposite(), 20);
			}
		}
	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {

	}

	@Override
	public void readPacketData(DataInputStream input) throws IOException {

	}

	@Override
	public ContainerTileFrog<? extends TileFrog> getGuiContainer(World world, EntityPlayer player) {
		return null;
	}

	@Override
	public GuiTileFrog<? extends TileFrog, ? extends ContainerTileFrog<? extends TileFrog>> getGui(World world, EntityPlayer player) {
		return null;
	}




}
