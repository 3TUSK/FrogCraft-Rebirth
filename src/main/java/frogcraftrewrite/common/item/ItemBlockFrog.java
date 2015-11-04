package frogcraftrewrite.common.item;

import frogcraftrewrite.common.block.BlockFrog;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockFrog extends ItemBlock {

	public ItemBlockFrog(Block block) {
		super(block);
		assert this.field_150939_a instanceof BlockFrog : new IllegalArgumentException("ItemBlockFrog can only be used internally for only FrogCraft!!!");
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "." + ((BlockFrog)this.field_150939_a).nameArray[stack.getItemDamage()] + ".name";
	}

}
