package frogcraftrebirth.common.lib.item;

import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.lib.block.BlockFrog;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public /*abstract*/ class ItemFrogBlock extends ItemBlock {
	
	static {
		Item.registerItemBlock(FrogBlocks.condenseTowerPart, new ItemFrogBlock(FrogBlocks.condenseTowerPart));
		Item.registerItemBlock(FrogBlocks.frogOres, new ItemFrogBlock(FrogBlocks.generators));
		
	}

	public ItemFrogBlock(Block block) {
		super(block);
		if (!(this.getBlock() instanceof BlockFrog))
			throw new IllegalArgumentException("ItemBlockFrog can only be used internally for only FrogCraft!!!");
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "." + ((BlockFrog)this.getBlock()).getSubNamesArray()[stack.getItemDamage()];
	}

}
