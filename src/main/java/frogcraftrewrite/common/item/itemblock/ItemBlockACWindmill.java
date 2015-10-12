package frogcraftrewrite.common.item.itemblock;

import java.util.List;

import frogcraftrewrite.common.block.BlockACWindmill;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockACWindmill extends ItemBlock {

	public ItemBlockACWindmill(Block block) {
		super(block);
		assert ((BlockACWindmill)field_150939_a).nameBuffer.length > 1 : new RuntimeException("Fail on checking sub-name");
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes")List sub) {
		this.field_150939_a.getSubBlocks(item, tab, sub);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.Machines2."+((BlockACWindmill)this.field_150939_a).nameBuffer[stack.getItemDamage()];
	}

}
