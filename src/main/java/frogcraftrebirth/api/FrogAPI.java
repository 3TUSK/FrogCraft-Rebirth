package frogcraftrebirth.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import frogcraftrebirth.api.recipes.IRecipeManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.DamageSource;

public final class FrogAPI {

	// Why you want an instance of this?
	private FrogAPI() {
		throw new UnsupportedOperationException();
	}

	public static final String 
		MODID = "frogcraftrebirth", 
		NAME = "FrogCraft: Rebirth", 
		API = "FrogAPI",
		API_VER = "0.2";

	public static final Logger FROG_LOG = LogManager.getLogger("FrogCraft-Rebirth");

	public static final CreativeTabs TAB = new CreativeTabs("FrogCraft") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FrogRegistees.HSU);
		}
	};
	
	public static final DamageSource TIBERIUM = new DamageSource("tiberium").setDamageBypassesArmor().setDamageIsAbsolute().setDifficultyScaled();
	public static final DamageSource ION_CANNON = new DamageSource("IonCannon").setDamageBypassesArmor().setFireDamage().setExplosion().setProjectile();
	
	public static final FrogFuelHandler FUEL_REG = new FrogFuelHandler();
	
	public static final Map<String, ICompatModuleFrog> COMPATS = new HashMap<>();
	
	@Nonnull
	public static IRecipeManager<IAdvChemRecRecipe> managerACR;
	@Nonnull
	public static IRecipeManager<ICondenseTowerRecipe> managerCT;
	@Nonnull
	public static IRecipeManager<IPyrolyzerRecipe> managerPyrolyzer;

	/**
	 * @param modid
	 *            the mod id.
	 * @param module
	 *            instance of compat module
	 * @return true if successfully added
	 */
	public static boolean registerFrogCompatModule(final String modid, final ICompatModuleFrog module) {
		if (COMPATS.containsKey(modid)) {
			FROG_LOG.error("Failed when registering compat module: " + modid + ", because the id has been occupied");
			return false;
		}

		COMPATS.put(modid, module);
		return true;
	}

}
