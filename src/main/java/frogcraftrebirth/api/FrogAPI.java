package frogcraftrebirth.api;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.CondenseTowerRecipe;
import frogcraftrebirth.api.recipes.IRecipeManager;
import frogcraftrebirth.api.recipes.PyrolyzerRecipe;
import gregtech.api.util.GT_Recipe;
import info.tritusk.tritchemlab.matter.Element;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class FrogAPI {

	//Why you want an instance of this?
	private FrogAPI() {}
	
	// ROT_OFFSET_N_4 stands for "Rotation offset with 4 faces and north-toward by default
	public static final int[][] ROT_OFFSET_N_4 = 
		{
				{0, 1, 2, 3, 4, 5},
				{0, 1, 2, 3, 4, 5},
				{0, 1, 2, 3, 4, 5},
				{0, 1, 3, 2, 5, 4},
				{0, 1, 4, 5, 3, 2},
				{0, 1, 5, 4, 2, 3}
		};
	
	@Deprecated //to be continue
	public static final int[][] ROTATION_OFFSET_N_6 = 
		{
				{}
		};
	
	public static final String 
		MODID = "FrogCraftRebirth",
		NAME = "FrogCraft: Rebirth",
		API = "FrogAPI", 
		API_VER = "0.2";
	
	public static List<Element> elementsList;
	
	@Nonnull
	public static CreativeTabs frogTab;
	
	public static IRecipeManager<IAdvChemRecRecipe> managerACR;
	public static IRecipeManager<CondenseTowerRecipe> managerCT;
	public static IRecipeManager<PyrolyzerRecipe> managerPyrolyzer;
	
	public static final FrogFuelHandler FUEL_REG = new FrogFuelHandler();
	
	public static final Map<String, ICompatModuleFrog> compats = new HashMap<String, ICompatModuleFrog>();
	
	//Yes this is unfinished, and will be finished soon.
	public static GT_Recipe.GT_Recipe_Map sPneumaticImplosionRecipes;
	
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
