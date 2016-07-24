package frogcraftrebirth.api;

//import static gregtech.api.enums.GT_Values.RES_PATH_GUI;
//import gregtech.api.util.GT_Recipe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.IRecipeManager;
import frogcraftrebirth.common.FrogItems;
import frogcraftrebirth.common.lib.CondenseTowerRecipe;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
		DEPENDING = "required-after:Forge@[12.18.1.2014,);required-after:IC2;";

	public static final Logger FROG_LOG = LogManager.getLogger("FrogCraft-Rebirth");

	public static final CreativeTabs frogTab = new CreativeTabs("FrogCraft") {
		@Override
		public Item getTabIconItem() {
			return FrogItems.jinkela;
		}
	};
	
	public static final FrogFuelHandler FUEL_REG = new FrogFuelHandler();

	@Nonnull
	public static IRecipeManager<IAdvChemRecRecipe> managerACR;
	@Nonnull
	public static IRecipeManager<CondenseTowerRecipe> managerCT;
	@Nonnull
	public static IRecipeManager<PyrolyzerRecipe> managerPyrolyzer;

	public static final Map<String, ICompatModuleFrog> COMPATS = new HashMap<String, ICompatModuleFrog>();

	// Awaitng GregTech
	// public static final GT_Recipe.GT_Recipe_Map sPneumaticImplosionRecipes =
	// new GT_Recipe.GT_Recipe_Map(new HashSet<GT_Recipe>(50),
	// "fcr.recipe.pneumaticcompressor", "Pneumatic Compressor", null,
	// RES_PATH_GUI + "basicmachines/Default", 1, 1, 1, 0, 1, "", 1, "", true,
	// true);

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
	 * @return Your itemstack
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
