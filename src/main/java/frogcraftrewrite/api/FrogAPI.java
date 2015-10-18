package frogcraftrewrite.api;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import frogcraftrewrite.api.recipes.IRecipeManager;
import frogcraftrewrite.api.trichemcompat.ElementLoader;
import frogcraftrewrite.common.lib.FrogBlocks;
import frogcraftrewrite.common.lib.FrogItems;
import frogcraftrewrite.common.lib.FrogReference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tritusk.trichemistry.matter.Element;

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
	 * effective chemical plant inside of my limitation. GUI will be 
	 * applied on several special-designed block, e.g. mobile power station, 
	 * ic2-ish industrial device, etc.
	 * */
	static final int MEANING_OF_LIFE_UNIVERSE_EVERYTHING = 42;
	
	//Public usage
	public static final String MODID = FrogReference.MODID, NAME = FrogReference.NAME;
	
	//Recipe manager, and I can't stand the loooooooong name!
	@SuppressWarnings("rawtypes")
	public static IRecipeManager managerACR, managerCFG, managerCT, managerTC;
	
	public static List<Element> elementsList = new LinkedList<Element>(Arrays.asList(ElementLoader.FROG_PARSER.parseElements(null, false)));
	
	//Plan: This method need document
	public static ItemStack findFrogStuff(String name, int damage) {
		Field stuff;
		
		try {
			stuff = FrogItems.class.getField(name);
			return new ItemStack((Item)stuff.get(Item.class), 1, damage);
		} catch (Exception e) {
			
		}
		
		try {
			stuff = FrogBlocks.class.getField(name);
			return new ItemStack((Block)stuff.get(Block.class), 1, damage);
		} catch (Exception e) {
			
		}
		
		return null;
	}
}
