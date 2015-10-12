package frogcraftrewrite.common.item.itemblock;

import frogcraftrewrite.common.block.BlockFrogOre;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockOre extends ItemBlockFrog{

	public ItemBlockOre(Block block) {
		super(block);
		setHasSubtypes(true);
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fcOre." + BlockFrogOre.nameArray[stack.getItemDamage()];
	}

}
