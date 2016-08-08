/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 11:08:34 PM, Aug 8, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.item;

import java.util.Collection;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fluids.Fluid;

public enum FluidArmorPotionEffectManager {
	
	INSTANCE;
	
	private final Multimap<Fluid, PotionEffect> fluidSideEffect = LinkedListMultimap.<Fluid, PotionEffect>create();
	
	public boolean registerFluidArmorSideEffect(Fluid fluid, PotionEffect potion) {
		return fluidSideEffect.put(fluid, potion);
	}
	
	public Collection<PotionEffect> getEffect(Fluid fluid) {
		return fluidSideEffect.get(fluid);
	}
	
	public void resetRegistry() {
		fluidSideEffect.clear();
	}

}
