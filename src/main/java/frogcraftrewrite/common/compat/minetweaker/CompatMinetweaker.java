package frogcraftrewrite.common.compat.minetweaker;

import frogcraftrewrite.common.compat.ICompatModuleFrog;
import minetweaker.MineTweakerAPI;

public class CompatMinetweaker implements ICompatModuleFrog {
	
	@Override
	public void preInit() {
		
	}
	
	@Override
	public void init() {
		MineTweakerAPI.registerClass(AdvChemReactor.class);
		MineTweakerAPI.registerClass(CondenseTower.class);
	}

	@Override
	public void postInit() {
		
	}

}
