package frogcraftrebirth.common.item;

import java.util.Arrays;
import java.util.List;

import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemJinkela extends ItemFrogCraft /*implements IWarpingGear*/ {

	public ItemJinkela() {
		super(false);
		setUnlocalizedName("Item_Miscs.GoldClod");
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!playerIn.canPlayerEdit(pos, facing, stack))
			return EnumActionResult.FAIL;
		
		IBlockState state = worldIn.getBlockState(pos);
		
		if (state.getBlock() instanceof IGrowable) {
			IGrowable igrowable = (IGrowable)state.getBlock();
			if (igrowable.canGrow(worldIn, pos, state, worldIn.isRemote)) {
				if (!worldIn.isRemote) {
					if (igrowable.canUseBonemeal(worldIn, worldIn.rand, pos, state)) {
						igrowable.grow(worldIn, worldIn.rand, pos, state);
						worldIn.playEvent(2005, pos, 0);
						return EnumActionResult.SUCCESS;
					}
				}
			}
		} 
		
		return EnumActionResult.PASS;
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		return Arrays.asList(I18n.format("item.Item_Miscs.GoldClod.info"));
	}

	//@Optional.Method(modid = "thaumcraft")
	//@Override
	//public int getWarp(ItemStack stack, EntityPlayer player) {
	//	return stack.stackSize;
	//}

}
