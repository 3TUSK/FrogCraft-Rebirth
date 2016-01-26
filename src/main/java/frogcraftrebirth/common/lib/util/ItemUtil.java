package frogcraftrebirth.common.lib.util;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class ItemUtil {
	
	/**
	 * Convert itemstack array to itemstack + String (ore dictionary) hybrid array
	 * @param rawInputs An ItemStack array
	 * @return An hybrid array containing itemstack and String (in term of ore dictionary)
	 */
	public static Object[] asFrogInputsArray(ItemStack[] rawInputs) {
		Object[] finalInputs = new Object[rawInputs.length];
		for (int i=0;i<rawInputs.length;i++) {
			String anEntry = OreDictionary.getOreName(OreDictionary.getOreIDs(rawInputs[i])[0]);
			finalInputs[i] = anEntry == null || anEntry == "" ? rawInputs[i] : anEntry; 
		}
		return finalInputs;
	}
	
	/**
	 * 
	 * @param oreArray
	 * @return
	 */
	public static ItemStack[] asItemStackArray(String[] oreArray) {
		ItemStack[] array = new ItemStack[oreArray.length];
		for (int i=0;i<oreArray.length;i++) {
			array[i] = OreDictionary.getOres(oreArray[i]).get(0);
		}
		return array;
	}
	
	public static boolean stackContains(ItemStack[] targetArray, ItemStack stack, final boolean oreDict, final boolean strictNBT, final boolean strictSize) {
		for (ItemStack aStack : targetArray) {
			if (oreDict) {
				int[] idArray = OreDictionary.getOreIDs(stack);
				ArrayList<String> entryArray = new ArrayList<String>();
				for (int id : idArray) {
					entryArray.addAll(Arrays.asList(OreDictionary.getOreName(id)));
				}
				ArrayList<ItemStack> stackArray = new ArrayList<ItemStack>();
				for (String entry : entryArray) {
					stackArray.addAll(OreDictionary.getOres(entry));
				}
				for (ItemStack examining : stackArray) {
					if (OreDictionary.itemMatches(examining, stack, true)) {
						return true;
					}
				}
			}
			
			boolean hasStack = OreDictionary.itemMatches(aStack, stack, true);
			if (strictSize)
				hasStack = aStack.stackSize == stack.stackSize;
			if (strictNBT)
				hasStack = aStack.getTagCompound().equals(stack.stackTagCompound);
			
			if (hasStack)
				return true;
			else
				continue;
		}
		return false;
	}
	
	/**
	 * Return whether stack1 has all stuff that stack2 has.
	 * @param oreDict Give it true if two stack can be equivalent in term of ore dictionary
	 * @param strictNBT Give it true if NBT tag is required in term of "equivalent"
	 * @param strictSize Give it false if size doesn't matter
	 * @return True if stack1 contains all stack2 contents, based on three extra parameters
	 */
	public static boolean deepStackContainsAll(ItemStack[] stack1, ItemStack[] stack2, final boolean oreDict, final boolean strictNBT, final boolean strictSize) {
		for (ItemStack s : stack2) {
			if (stackContains(stack1, s, oreDict, strictNBT, strictSize))
				return true;
		}
		return false;
	}
	
	public static boolean deepStackEquals(ItemStack[] stack1, ItemStack[] stack2, final boolean oreDict, final boolean strictNBT) {
		for (ItemStack s : stack2) {
			if (stackContains(stack1, s, oreDict, strictNBT, true))
				return true;
		}
		return false;
	}

}