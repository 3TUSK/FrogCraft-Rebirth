package frogcraftrebirth.api.tile;

import net.minecraftforge.common.util.ForgeDirection;

public interface IAirPump {
	
	int airAmount();
	
	void extractAir(ForgeDirection from, int amount, boolean simluated);

}
