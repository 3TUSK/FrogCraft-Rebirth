package frogcraftrewrite.common.lib.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class ItemUtil {
	
	public static Object[] asFrogInputsArray(ItemStack[] rawInputs) {
		Object[] finalInputs = new Object[rawInputs.length];
		for (int i=0;i<rawInputs.length;i++) {
			String anEntry = OreDictionary.getOreName(OreDictionary.getOreIDs(rawInputs[i])[0]);
			finalInputs[i] = anEntry == null || anEntry == "" ? rawInputs[i] : anEntry; 
		}
		return finalInputs;
	}
	
	public static ItemStack[] asItemStackArray(String[] oreArray) {
		ItemStack[] array = new ItemStack[oreArray.length];
		for (int i=0;i<oreArray.length;i++) {
			array[i] = OreDictionary.getOres(oreArray[i]).get(0);
		}
		return array;
	}

}
