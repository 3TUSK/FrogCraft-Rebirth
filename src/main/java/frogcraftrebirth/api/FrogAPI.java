package frogcraftrebirth.api;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import frogcraftrebirth.api.recipes.IRecipeManager;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
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
		API_VER = "0.2", 
		DEPENDING = "required-after:Forge@[12.18.1.2063,);required-after:IC2@[2.6.40,);after:JEI@[3.11.0,)";

	public static final Logger FROG_LOG = LogManager.getLogger("FrogCraft-Rebirth");

	public static final CreativeTabs TAB = new CreativeTabs("FrogCraft") {
		@Override
		public Item getTabIconItem() {
			return findFrogItem("hybridStorageUnit", 1, 0).getItem();
		}
	};
	
	public static final DamageSource TIBERIUM = new DamageSource("tiberium").setDamageBypassesArmor().setDamageIsAbsolute().setDifficultyScaled();
	public static final DamageSource ION_CANNON = new DamageSource("IonCannon").setDamageBypassesArmor().setFireDamage().setExplosion().setProjectile();
	
	@Nonnull
	public static Potion potionTiberium;
	
	public static final FrogFuelHandler FUEL_REG = new FrogFuelHandler();
	
	public static final Map<String, ICompatModuleFrog> COMPATS = new HashMap<String, ICompatModuleFrog>();
	
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

	/**
	 * @param name
	 *            internal name.
	 * @param amount
	 *            quantity of stack
	 * @param damage
	 * @return Your item stack, maybe null.
	 */
	@Nullable
	public static ItemStack findFrogItem(final String name, final int amount, final int meta) {
		Field stuff;

		try {
			stuff = Class.forName("frogcraftrebirth.common.FrogItems").getField(name);
			return new ItemStack((Item) stuff.get(null), amount, meta);
		} catch (Exception e) {
		}

		try {
			stuff = Class.forName("frogcraftrebirth.common.FrogBlocks").getField(name);
			return new ItemStack((Block) stuff.get(null), amount, meta);
		} catch (Exception e) {
		}

		FROG_LOG.error("Failed to find FrogCraft: Rebirth item: " + name + "@" + meta);
		return null;
	}

}
