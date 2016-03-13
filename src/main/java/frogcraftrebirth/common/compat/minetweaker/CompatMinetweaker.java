package frogcraftrebirth.common.compat.minetweaker;

import frogcraftrebirth.api.ICompatModuleFrog;
import minetweaker.MineTweakerAPI;

public class CompatMinetweaker implements ICompatModuleFrog {
	
	@Override
	public void init() {
		MineTweakerAPI.registerClass(AdvChemReactor.class);
		MineTweakerAPI.registerClass(CondenseTower.class);
		MineTweakerAPI.registerClass(ThermalCracker.class);
	}

}
