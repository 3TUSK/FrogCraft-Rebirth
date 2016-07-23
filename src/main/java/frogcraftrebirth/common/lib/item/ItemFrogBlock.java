package frogcraftrebirth.common.lib.item;

import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.lib.block.BlockFrog;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ItemFrogBlock extends ItemBlock {
	
	public static void initItemBlock() {
		//Item.registerItemBlock(FrogBlocks.condenseTowerPart, new ItemFrogBlock(FrogBlocks.condenseTowerPart));
		//Item.registerItemBlock(FrogBlocks.frogOres, new ItemFrogBlock(FrogBlocks.frogOres));
		Item.registerItemBlock(FrogBlocks.fluidNitricAcid, new ItemBlock(FrogBlocks.fluidNitricAcid).setRegistryName("nitric_acid_block"));
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

}
