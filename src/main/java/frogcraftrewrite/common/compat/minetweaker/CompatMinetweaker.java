package frogcraftrewrite.common.compat.minetweaker;

import frogcraftrewrite.common.compat.ICompatModuleFrog;
import minetweaker.MineTweakerAPI;

public class CompatMinetweaker implements ICompatModuleFrog {
	
	public void init() {
		MineTweakerAPI.registerClass(AdvChemReactor.class);
		MineTweakerAPI.registerClass(CondenseTower.class);
	}

	@Override
	public void postInit() {
		
	}

}
