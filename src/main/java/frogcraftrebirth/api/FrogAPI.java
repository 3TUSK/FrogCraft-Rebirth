package frogcraftrebirth.api;

import static gregtech.api.enums.GT_Values.RES_PATH_GUI;

import java.lang.reflect.Field;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.IRecipeManager;
import frogcraftrebirth.common.lib.CondenseTowerRecipe;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import gregtech.api.util.GT_Recipe;
//import info.tritusk.tritchemlab.matter.Element;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class FrogAPI {

	//Why you want an instance of this?
	private FrogAPI() {}
	
	public static final String 
		MODID = "FrogCraftRebirth",
		NAME = "FrogCraft: Rebirth",
		API = "FrogAPI", 
		API_VER = "0.2",
		DEPENDING = "required-after:IC2;after:gregtech;after:thaumcraft;after:minetweaker3;";
	
	public static final Logger FROG_LOG = LogManager.getLogger("FrogCraft-Rebirth");
	
	/**
	 * Use for your own risk
	 */
	public static final int[][] 
		ROT_OFFSET_N_4 = 
		{
				{0, 1, 2, 3, 4, 5},
				{0, 1, 2, 3, 4, 5},
				{0, 1, 2, 3, 4, 5},
				{0, 1, 3, 2, 5, 4},
				{0, 1, 4, 5, 3, 2},
				{0, 1, 5, 4, 2, 3}
		},
		ROT_OFFSET_S_6 = 
		{
				{3, 2, 1, 0, 4, 5}, 
				{2, 3, 0, 1, 4, 5},
				{0, 1, 2, 3, 4, 5},
				{0, 1, 3, 2, 5, 4},
				{0, 1, 4, 5, 3, 2},
				{0, 1, 5, 4, 2, 3}
		};
	
	@Nonnull
	public static CreativeTabs frogTab;
	
	public static IRecipeManager<IAdvChemRecRecipe> managerACR;
	public static IRecipeManager<CondenseTowerRecipe> managerCT;
	public static IRecipeManager<PyrolyzerRecipe> managerPyrolyzer;
	
	//Disable ChemLab thing for a while
	//public static final List<Element> elementsList = new LinkedList<Element>(Arrays.asList(ElementLoader.FROG_PARSER.parseElements(FrogAPI.class.getResourceAsStream("assets/frogcraftrebirth/tritchemlab/PeriodicTable.xml"), false)));
	
	public static final FrogFuelHandler FUEL_REG = new FrogFuelHandler();
	
	public static final Map<String, ICompatModuleFrog> compats = new HashMap<String, ICompatModuleFrog>();
	
	public static final GT_Recipe.GT_Recipe_Map sPneumaticImplosionRecipes = new GT_Recipe.GT_Recipe_Map(new HashSet<GT_Recipe>(50), "fcr.recipe.pneumaticcompressor", "Pneumatic Compressor", null, RES_PATH_GUI + "basicmachines/Default", 1, 1, 1, 0, 1, "", 1, "", true, true);
	
	/**
	 * @param modid
	 * @param module instance of compat module
	 * @return true if successfully added
	 */
	public static boolean registerFrogCompatModule(String modid, ICompatModuleFrog module) {
		if (compats.containsKey(modid)) {
			/*FrogCraftRebirth.FROG_LOG.error("The following compat module id has been occupied: " + modid);*/
			return false;
		}
		
		compats.put(modid, module);
		return true;
	}
	
	/**
	 * @param name internal name. 
	 * @param damage
	 * @return Your itemstack with amount of 1
	 */
	public static ItemStack findFrogItem(String name, int damage) {
		Field stuff;
		
		try {
			stuff = Class.forName("frogcraftrebirth.common.FrogItems").getField(name);
			return new ItemStack((Item)stuff.get(null), 1, damage);
		} catch (Exception e) {}
		
		try {
			stuff = Class.forName("frogcraftrebirth.common.FrogBlocks").getField(name);
			return new ItemStack((Block)stuff.get(null), 1, damage);
		} catch (Exception e) {}
		
		return null;
	}
	
}
