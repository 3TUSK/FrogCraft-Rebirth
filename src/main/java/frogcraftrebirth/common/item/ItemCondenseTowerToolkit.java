/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:40:41 PM, Aug 19, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.item;

import java.util.Collections;
import java.util.List;

import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCondenseTowerToolkit extends ItemFrogCraft {

	public ItemCondenseTowerToolkit() {
		super(false);
		setMaxStackSize(1);
		setMaxDamage(0);
		setNoRepair();
		setUnlocalizedName("toolkitCondenseTower");
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		return Collections.emptyList();
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof ICondenseTowerCore && !((ICondenseTowerCore)tile).isCompleted()) {
				if (((ICondenseTowerCore)tile).checkStructure())
					((ICondenseTowerCore)tile).onConstruct((ICondenseTowerCore)tile);;
			}
		}
		
		return EnumActionResult.PASS;
	}

}
