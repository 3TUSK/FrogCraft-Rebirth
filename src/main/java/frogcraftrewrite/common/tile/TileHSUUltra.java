package frogcraftrewrite.common.tile;

import net.minecraftforge.common.util.ForgeDirection;

public class TileHSUUltra extends TileHSU {
	
	public TileHSUUltra() {
		super(1000000000, 8192, ForgeDirection.DOWN, false);
	}

	@Override
	public int getTier() {
		return 5;
	}

}
