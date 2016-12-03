/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 11:49:17 PM, Jul 25, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.lib;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FrogFluid extends Fluid {
	
	public FrogFluid(String name, int density, int temperature, boolean gaseous, EnumRarity rarity) {
		this(name, name, name, density, temperature, gaseous, rarity);
	}

	public FrogFluid(String name, String resLoc, int density, int temperature, boolean gaseous, EnumRarity rarity) {
		this(name, resLoc, resLoc, density, temperature, gaseous, rarity);
	}
	
	public FrogFluid(String name, String flow, String still, int density, int temperature, boolean gaseous, EnumRarity rarity) {
		super(name, new ResourceLocation("frogcraftrebirth", "fluids/" + flow), new ResourceLocation("frogcraftrebirth", "fluids/" + still));
		this.setDensity(density);
		this.setTemperature(temperature);
		this.setGaseous(gaseous);
	}

}
