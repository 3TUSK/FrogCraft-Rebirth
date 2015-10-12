package frogcraftrewrite.common.item.itemblock;

import frogcraftrewrite.common.block.BlockHybridEStorage;
import net.minecraft.item.ItemBlock;

public class ItemBlockHybridEStorage extends ItemBlock{

	public ItemBlockHybridEStorage(BlockHybridEStorage block) {
		super(block);
		setHasSubtypes(true);
		setUnlocalizedName("Machines."+((BlockHybridEStorage)block).getInternalName());
	}

}
