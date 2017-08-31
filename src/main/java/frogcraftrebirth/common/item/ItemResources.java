/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 9:57:13 AM, Jun 21, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.item;

import java.util.List;

import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemResources extends ItemFrogCraft {

	public ItemResources(String unlocalizedName, String... subNameArray) {
		this(true, unlocalizedName, subNameArray);
	}
	
	public ItemResources(boolean hasSubType, String unlocalizedName, String... subNameArray) {
		super(hasSubType);
		setUnlocalizedName(unlocalizedName);
		if (hasSubType && subNameArray.length > 0)
			setSubNameArray(subNameArray);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format(getUnlocalizedName(stack) + ".info"));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return nameArray != null ? super.getUnlocalizedName()+"."+nameArray[stack.getMetadata()] : super.getUnlocalizedName(stack);
	}

}
