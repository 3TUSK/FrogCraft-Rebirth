package frogcraftrebirth.common.lib.item;

import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemFrogBlock extends ItemBlock {
	
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

}
