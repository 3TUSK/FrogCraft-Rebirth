package frogcraftrebirth.common.lib.item;

import java.util.List;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import frogcraftrebirth.api.FrogAPI;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class ItemFrogCraft extends Item {
	
	protected String[] nameArray;

	public ItemFrogCraft(boolean hasSubType){
		this.setCreativeTab(FrogAPI.frogTab);
		if (hasSubType) {
			this.setMaxDamage(0);
			this.setHasSubtypes(true);
		}
	}
	
	/**
	 * @param stack The itemstack 
	 * @param player The player
	 * @param adv Require F3+H on?
	 * @return A list which contains tooltips you want to show.
	 */
	public abstract List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv);
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> aList, boolean adv) {
		if (getToolTip(stack, player, adv) != null)
			aList.addAll(getToolTip(stack, player, adv));
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.format(getUnlocalizedName(stack));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List<ItemStack> list) {
		if (!getHasSubtypes()) {
			super.getSubItems(item, tabs, list);
			return;
		}
		for (int i=0;i<nameArray.length;i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	protected ItemFrogCraft setSubNameArray(String... names) {
		this.nameArray = names;
		return this;
	}

}
