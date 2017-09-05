package frogcraftrebirth.common.lib.util;

import java.util.*;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.oredict.OreDictionary;

public final class ItemUtil {
	
	private static final Random RAND = new Random();

	public static final Predicate<ItemStack> EMPTY_PREDICATE = ItemStack::isEmpty;

	public static final Predicate<ItemStack> NON_EMPTY_PREDICATE = stack -> !stack.isEmpty();

	/**
	 * Identical to {@link InventoryHelper#dropInventoryItems}, except this one is designed for {@link IItemHandler}.
	 * @param worldIn The world that block is in
	 * @param pos The position of block
	 * @param inv An array of IItemHandler implementation. Note: all IItemHandler here are assumed to start index from zero.
	 */
	public static void dropInventroyItems(World worldIn, BlockPos pos, IItemHandler... inv) {
		for (IItemHandler invSingle : inv) {
			final int slots = invSingle.getSlots();
			for (int index = 0; index < slots; index++) {
				ItemStack stack = invSingle.getStackInSlot(index);
				if (!stack.isEmpty())
					dropItemStackAsEntityInsanely(worldIn, pos, stack);
			}
		}
	}
	
	/**
	 * Identical to {@link InventoryHelper#spawnItemStack} in terms of functionality,
	 * except that this one has insane implementation.
	 * @param worldIn The world that item stack will drop.
	 * @param pos The position reference.
	 * @param toDrop The item stack to drop.
	 */
	public static void dropItemStackAsEntityInsanely(World worldIn, BlockPos pos, @Nonnull ItemStack toDrop) {
		EntityItem entityItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), toDrop);
		entityItem.motionX = RAND.nextGaussian() * 0.05D;
		entityItem.motionY = RAND.nextGaussian() * 0.05D + 0.2D;
		entityItem.motionZ = RAND.nextGaussian() * 0.05D;
		worldIn.spawnEntity(entityItem);
	}
	
	public static ItemStack get1stChoiceFromOre(String entry) {
		try {
			return OreDictionary.getOres(entry).get(0).copy();
		} catch (Exception e) {
			return null; //fall back
		}
	}
	
	public static ItemStack[] asItemStackArray(String[] oreArray) {
		ItemStack[] array = new ItemStack[oreArray.length];
		for (int i=0;i<oreArray.length;i++) {
			array[i] = get1stChoiceFromOre(oreArray[i]);
		}
		return array;
	}
	
	public static Collection<ItemStack> toCollection(IItemHandler handler) {
		List<ItemStack> stacks = new ArrayList<>();
		final int slotsNum = handler.getSlots();
		for (int index = 0; index < slotsNum; index++) {
			stacks.add(handler.getStackInSlot(index));
		}
		return Collections.unmodifiableList(stacks);
	}
	
	public static boolean stackContains(ItemStack[] targetArray, ItemStack stack, final boolean oreDict, final boolean strictNBT, final boolean strictSize) {
		for (ItemStack aStack : targetArray) {
			if (oreDict) {
				int[] idArray = OreDictionary.getOreIDs(stack);
				ArrayList<String> entryArray = new ArrayList<>();
				for (int id : idArray) {
					entryArray.addAll(Collections.singletonList(OreDictionary.getOreName(id)));
				}
				ArrayList<ItemStack> stackArray = new ArrayList<>();
				entryArray.forEach(entry -> stackArray.addAll(OreDictionary.getOres(entry)));
				for (ItemStack examining : stackArray) {
					if (OreDictionary.itemMatches(examining, stack, true)) {
						return true;
					}
				}
			}
			
			boolean hasStack = OreDictionary.itemMatches(aStack, stack, true);
			if (strictSize)
				hasStack = aStack.getCount() == stack.getCount();
			if (strictNBT)
				hasStack = ItemStack.areItemStackTagsEqual(aStack, stack);
			
			if (hasStack)
				return true;
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
