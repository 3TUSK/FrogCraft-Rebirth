package frogcraftrebirth.api.tile;

import net.minecraft.util.EnumFacing;

public interface IAirPump {

	int airAmount();

	void extractAir(EnumFacing from, int amount, boolean simluated);

}
