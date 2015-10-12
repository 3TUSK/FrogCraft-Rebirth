package frogcraftrewrite.common.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public abstract class ItemBlockFrog extends ItemBlock {

	public ItemBlockFrog(Block block) {
		super(block);
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}

}
