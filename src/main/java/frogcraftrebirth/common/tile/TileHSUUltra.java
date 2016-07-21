package frogcraftrebirth.common.tile;

import net.minecraft.util.EnumFacing;

public class TileHSUUltra extends TileHSU {
	
	public TileHSUUltra() {
		super(1000000000, 8192, EnumFacing.DOWN, false);
	}

	@Override
	public int getTier() {
		return 5;
	}

}
