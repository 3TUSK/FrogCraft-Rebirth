package frogcraftrewrite.api;

import frogcraftrewrite.api.recipes.IRecipeManager;
import net.minecraft.creativetab.CreativeTabs;

public class Constants {
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
	 * effective chemical plant inside of my limitation. GUI will be 
	 * applied on several special-designed block, e.g. mobile power station, 
	 * ic2-ish industrial device, etc.
	 * */
	public static final int MEANING_OF_LIFE_UNIVERSE_EVERYTHING = 42;
	
	//General stuff.
	public static final String MODID = "FrogCraftRebirth";
	public static final String NAME = "FrogCraft: Rebirth";
	public static final String DEPENDING = 
			"required-after:IC2;"
			+ "after:gregtech_api;"
			+ "after:thaumcraft;"
			+ "after:minetweaker3;"
			+ "after:techreborn;";
	
	/** Creative Page for all FrogCraft stuff. DO NOT OVERRIDE IT.*/
	public static CreativeTabs tabFrogCraft;
	
	//Recipe manager, and I can't stand the loooooooong name!
	@SuppressWarnings("rawtypes")
	public static IRecipeManager managerACR, managerCFG, managerCT, managerTC;
	
	/**
	 * @return THE FINAL ANSWER: 42
	 */
	public static String getFinalAnswer() {
		return Integer.toString(MEANING_OF_LIFE_UNIVERSE_EVERYTHING);
	}
}
