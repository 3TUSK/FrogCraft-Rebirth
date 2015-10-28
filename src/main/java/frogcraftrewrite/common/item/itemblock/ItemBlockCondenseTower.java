package frogcraftrewrite.common.item.itemblock;

import frogcraftrewrite.common.block.BlockCondenseTower;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockCondenseTower extends ItemBlockFrog {

	public ItemBlockCondenseTower(Block block) {
		super(block);
		setHasSubtypes(true);
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.Machines.CondenseTower." + BlockCondenseTower.nameArray[stack.getItemDamage()];
	}

}
