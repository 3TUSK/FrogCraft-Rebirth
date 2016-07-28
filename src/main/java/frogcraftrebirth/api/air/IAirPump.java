package frogcraftrebirth.api.air;

import net.minecraft.util.EnumFacing;

public interface IAirPump {

	int airAmount();

	int extractAir(EnumFacing from, int amount, boolean simluated);

}
