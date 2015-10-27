package frogcraftrewrite.api.tile;

import net.minecraftforge.common.util.ForgeDirection;

public interface IAirPump {
	
	int availableAmount();
	
	void extractAir(ForgeDirection from, int amount, boolean simluated);

}
