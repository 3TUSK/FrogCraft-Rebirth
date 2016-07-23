package frogcraftrebirth.common.lib.item;

import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.lib.block.BlockFrog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ItemFrogBlock extends ItemBlock {
	
	public static void initItemBlock() {
		//Item.registerItemBlock(FrogBlocks.condenseTowerPart, new ItemFrogBlock(FrogBlocks.condenseTowerPart));
		//Item.registerItemBlock(FrogBlocks.frogOres, new ItemFrogBlock(FrogBlocks.frogOres));
		Item.registerItemBlock(FrogBlocks.fluidNitricAcid, new ItemBlock(FrogBlocks.fluidNitricAcid).setRegistryName("nitric_acid_block"));
	}

	public ItemFrogBlock(BlockFrog block) {
		super(block);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}

}
