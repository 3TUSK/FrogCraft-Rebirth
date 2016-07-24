package frogcraftrebirth.common.lib.item;

import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.lib.block.BlockFrog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ItemFrogBlock extends ItemBlock {
	
	public static void initItemBlock() {
		Item.registerItemBlock(FrogBlocks.condenseTowerPart, new ItemFrogBlock(FrogBlocks.condenseTowerPart));
		Item.registerItemBlock(FrogBlocks.frogOres, new ItemFrogBlock(FrogBlocks.frogOres));
		Item.registerItemBlock(FrogBlocks.generators, new ItemFrogBlock(FrogBlocks.generators));
		Item.registerItemBlock(FrogBlocks.hybridStorageUnit, new ItemFrogBlock(FrogBlocks.hybridStorageUnit));
		Item.registerItemBlock(FrogBlocks.machines, new ItemFrogBlock(FrogBlocks.machines));
		Item.registerItemBlock(FrogBlocks.mobilePowerStation, new ItemFrogBlock(FrogBlocks.mobilePowerStation));
		Item.registerItemBlock(FrogBlocks.tiberium, new ItemFrogBlock(FrogBlocks.tiberium));
		
		Item.registerItemBlock(FrogBlocks.fluidNitricAcid, new ItemBlock(FrogBlocks.fluidNitricAcid));
	}

	public ItemFrogBlock(BlockFrog block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}

}
