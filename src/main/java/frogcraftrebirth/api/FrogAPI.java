package frogcraftrebirth.api;

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

	/**
	 * Magic number that serves as an identifier, used by data fixer to identify whether the given
	 * save contains data from older version of FrogCraft: Rebirth.
	 */
	public static final int DATA_FIXER_REMARK = 20160730;

	public static final Logger FROG_LOG = LogManager.getLogger("FrogCraft-Rebirth");

	public static final CreativeTabs TAB = new CreativeTabs("FrogCraft") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FrogRegistees.HSU);
		}
	};

	@Deprecated
	public static final DamageSource TIBERIUM = new DamageSource("tiberium").setDamageBypassesArmor().setDamageIsAbsolute().setDifficultyScaled();
	@Deprecated
	public static final DamageSource ION_CANNON = new DamageSource("IonCannon").setDamageBypassesArmor().setFireDamage().setExplosion().setProjectile();
	
	public static final FrogFuelHandler FUEL_REG = new FrogFuelHandler();
	
	@Nonnull
	public static IRecipeManager<IAdvChemRecRecipe> managerACR;
	@Nonnull
	public static IRecipeManager<ICondenseTowerRecipe> managerCT;
	@Nonnull
	public static IRecipeManager<IPyrolyzerRecipe> managerPyrolyzer;

}
