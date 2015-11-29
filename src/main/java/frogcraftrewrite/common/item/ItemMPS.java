package frogcraftrewrite.common.item;

import frogcraftrewrite.common.block.BlockMPS;
import frogcraftrewrite.common.lib.item.ItemFrogBlock;
import ic2.api.item.IElectricItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMPS extends ItemFrogBlock implements IElectricItem {

	public ItemMPS(Block block) {
		super(block);
		if (!(this.field_150939_a instanceof BlockMPS))
			throw new IllegalArgumentException("Failure on initialize MPS block!");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.BlockMobilePS";
	}
	
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ) {
		if (world.isRemote) 
			return false;
		Block aBlock = world.getBlock(x, y, z);
		@SuppressWarnings("unused")
		int flag = aBlock.onBlockPlaced(world, y, y, z, side, clickX, clickY, clickZ, world.getBlockMetadata(x, y, z));
		//to be continued.
		return false;
	}

	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return true;
	}

	@Override
	public Item getChargedItem(ItemStack itemStack) {
		return this;
	}

	@Override
	public Item getEmptyItem(ItemStack itemStack) {
		return this;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		return itemStack.stackTagCompound.getDouble("maxCharge");
	}

	@Override
	public int getTier(ItemStack itemStack) {
		return 1;
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		return 2048;
	}

}
