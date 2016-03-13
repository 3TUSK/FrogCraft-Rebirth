package frogcraftrebirth.common.compat;

import static frogcraftrebirth.common.lib.config.ConfigMain.*;

import frogcraftrebirth.api.ICompatModuleFrog;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

public class CompatThaumcraft implements ICompatModuleFrog {
	
	public static Aspect fertilizer;

	@Override
	public void init() {
		fertilizer = new Aspect("fertilitati", 0xFFFF00, new Aspect[] {Aspect.GREED, Aspect.CROP}, new ResourceLocation(""/*todo*/), 771);
		if (enableTCAspect) {
			
		}
	}

}
