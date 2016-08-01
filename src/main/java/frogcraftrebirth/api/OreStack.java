/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 4:44:30 PM, Mar 28, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class OreStack {

	private final String entry;

	private int amount;

	public OreStack(String entry) {
		this(entry, 1);
	}

	public OreStack(String entry, int amount) {
		this.entry = entry;
		this.amount = amount;
	}

	public OreStack increaseSize(int quantityChange) {
		this.amount += quantityChange;
		return this;
	}

	public OreStack decreaseSize(int quantityChange) {
		this.amount -= quantityChange;
		return this;
	}

	public String getEntry() {
		return this.entry;
	}

	public int getAmount() {
		return this.amount;
	}

	public boolean equals(OreStack stack) {
		return stack.getEntry().equals(this.entry) && stack.getAmount() == this.getAmount();
	}

	public boolean consumable(ItemStack stack) {
		return OreStack.entryHasStack(stack, this.entry) && stack.stackSize >= this.amount;
	}

	public void consume(ItemStack stack) {
		if (this.consumable(stack))
			stack.stackSize -= this.amount;
		if (stack.stackSize <= 0)
			stack = null;
	}

	public boolean stackable(ItemStack stack) {
		return OreStack.entryHasStack(stack, this.entry) && stack.getMaxStackSize() <= this.amount + stack.stackSize;
	}

	public void stack(ItemStack stack) {
		if (stack == null) {
			stack = OreDictionary.getOres(this.entry).get(0);
			stack.stackSize = this.amount;
			return;
		}

		stack.stackSize += this.amount;
	}
	
	public NBTTagCompound saveToNBT(NBTTagCompound tag) {
		tag.setString("oreName", entry);
		tag.setInteger("amount", amount);
		return tag;
	}

	public static boolean stackHasEntry(ItemStack stack, String ore) {
		if (!OreDictionary.doesOreNameExist(ore))
			return false;

		ArrayList<String> entries = new ArrayList<String>();
		for (int num : OreDictionary.getOreIDs(stack))
			entries.add(OreDictionary.getOreName(num));

		return entries.contains(ore);
	}

	public static boolean entryHasStack(ItemStack stack, String ore) {
		return OreDictionary.doesOreNameExist(ore) && OreDictionary.getOres(ore).contains(stack);
	}
	
	public static OreStack loadFromNBT(NBTTagCompound tag) {
		String name = tag.getString("oreName");
		int quantity = tag.getInteger("amount");
		return name != null && (!"".equals(name)) ? new OreStack(name, quantity) : null;
	}

}
