package frogcraftrewrite.common.compat.minetweaker;

import minetweaker.MineTweakerAPI;

public class CompatMinetweaker {
	
	public void init() {
		MineTweakerAPI.registerClass(AdvChemReactor.class);
		MineTweakerAPI.registerClass(CondenseTower.class);
	}

}
