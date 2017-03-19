package frogcraftrebirth.common.lib.item;

import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemFrogBlock extends ItemBlock {
	
	public static void initFluidItemBlock() {
		//Item.registerItemBlock(FrogBlocks.FLUID_NITRIC_ACID, new ItemBlock(FrogBlocks.FLUID_NITRIC_ACID));
	}
	
	private final Function<ItemStack, String> subName;
	
	public ItemFrogBlock(Block block, Function<ItemStack, String> subNameGetter) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
		this.subName = subNameGetter;
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "." + subName.apply(stack);
	}
	
	public static void registerItemBlockFor(Block blockIn, Function<ItemStack, String> subNameGetter) {
		registerItemBlock(blockIn, new ItemFrogBlock(blockIn, subNameGetter));
	}
	
	public static void registerItemBlockFor(Block blockIn, Item itemIn) {
		registerItemBlock(blockIn, itemIn);
	}

}
