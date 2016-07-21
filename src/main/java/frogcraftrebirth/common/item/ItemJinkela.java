package frogcraftrebirth.common.item;

import java.util.ArrayList;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class ItemJinkela extends ItemFrogCraft /*implements IWarpingGear*/ {

	public ItemJinkela() {
		super(false);
		setUnlocalizedName("goldClod");
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState block = worldIn.getBlockState(pos);

		BonemealEvent event = new BonemealEvent(playerIn, worldIn, pos, block);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return EnumActionResult.FAIL;
		}

		if (event.getResult() == Result.ALLOW) {
			if (!worldIn.isRemote) {
			}
			return EnumActionResult.FAIL;
		}

		if (block instanceof IGrowable) {
			IGrowable igrowable = (IGrowable) block;

			if (igrowable.canGrow(worldIn, pos, block, worldIn.isRemote)) {
				if (!worldIn.isRemote) {
					if (igrowable.canUseBonemeal(worldIn, worldIn.rand, pos, block)) {
						igrowable.grow(worldIn, worldIn.rand, pos, block);
						return EnumActionResult.SUCCESS;
					}
				}
			}
		}

		return EnumActionResult.PASS;
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		List<String> list = new ArrayList<String>();
		list.add(I18n.format("item.Item_Miscs.GoldClod.info"));
		return list;
	}

	//@Optional.Method(modid = "thaumcraft")
	//@Override
	//public int getWarp(ItemStack stack, EntityPlayer player) {
	//	return stack.stackSize;
	//}

}
