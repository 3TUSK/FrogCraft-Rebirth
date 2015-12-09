package frogcraftrewrite.api;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Nonnull;

import frogcraftrewrite.api.recipes.AdvChemReactorRecipe;
import frogcraftrewrite.api.recipes.CondenseTowerRecipe;
import frogcraftrewrite.api.recipes.IRecipeManager;
import frogcraftrewrite.api.recipes.ThermalCrackerRecipe;
import frogcraftrewrite.common.lib.FrogBlocks;
import frogcraftrewrite.common.lib.FrogItems;
import frogcraftrewrite.common.lib.FrogRef;
import info.tritusk.tritchemlab.matter.Element;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FrogAPI {
	/**
	 * FrogCraft (2013-) is a Minecraft Mod authored by Rikka.<br>
	 * Credits to Rikka for original code and M3gaFrank (original
	 * author of ExtraCell) for porting to 1.6.4.<br> FrogCraftRebirth 
	 * is a Minecraft Mod, porting 1.6.2 FrogCraft to 1.7.10, authored 
	 * by 3TUSK.<br> Code are based on original code, may adjusted in 
	 * order to improve player performance.
	 * <p>
	 * This mod is my response to asie's Cult of Kitteh since 11:15 PM,
	 * Aug 31st, EST, 2015. As response, the resurrection of FrogCraft is 
	 * aiming to reduce the implements of graphic user interface (known 
	 * for GUI) in order to let players focus on designing a real, useful, 
	 * effective chemical plant inside of my limitation.
	 * */
	static final int MEANING_OF_LIFE_UNIVERSE_EVERYTHING = 42;
	
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
	public static final int[][] ROTATION_OFFSET_NORTH_6 = 
		{
				{}
		};
	
	public static final String MODID = FrogRef.MODID, NAME = FrogRef.NAME;
	
	public static final String API = "FrogAPI", API_VER = "0.2";
	
	@Nonnull
	public static CreativeTabs frogTab;
	
	public static IRecipeManager<AdvChemReactorRecipe> managerACR;
	public static IRecipeManager<CondenseTowerRecipe> managerCT;
	public static IRecipeManager<ThermalCrackerRecipe> managerTC;
	
	public static List<Element> elementsList;
	
	//Plan: This method need document
	public static ItemStack findFrogStuff(String name, int damage) {
		Field stuff;
		
		try {
			stuff = FrogItems.class.getField(name);
			return new ItemStack((Item)stuff.get(Item.class), 1, damage);
		} catch (Exception e) {}
		
		try {
			stuff = FrogBlocks.class.getField(name);
			return new ItemStack((Block)stuff.get(Block.class), 1, damage);
		} catch (Exception e) {}
		
		return null;
	}
}
