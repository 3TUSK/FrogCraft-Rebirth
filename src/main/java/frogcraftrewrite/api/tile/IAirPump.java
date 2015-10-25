package frogcraftrewrite.api.tile;

import net.minecraftforge.common.util.ForgeDirection;

public interface IAirPump {
	
	int availableAmount();
	
	void extract(ForgeDirection from, int amount, boolean simluated);

}
