package frogcraftrebirth.common.item;

import frogcraftrebirth.common.block.BlockMPS;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import ic2.api.item.IElectricItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMPS extends ItemFrogBlock implements IElectricItem {

	public ItemMPS(BlockMPS block) {
		super(block, (ItemStack aStack) -> {
			return "normal";
		});
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) 
			return EnumActionResult.PASS;
		
		IBlockState state = worldIn.getBlockState(pos);
		@SuppressWarnings("unused")
		IBlockState placed = state.getBlock().onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, state.getBlock().getMetaFromState(state), playerIn);
		//to be continued.
		return EnumActionResult.SUCCESS;
	}

	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return true;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		return itemStack.getTagCompound().getDouble("maxCharge");
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
