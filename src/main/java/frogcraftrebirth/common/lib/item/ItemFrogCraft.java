package frogcraftrebirth.common.lib.item;

import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import frogcraftrebirth.api.FrogAPI;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public abstract class ItemFrogCraft extends Item {
	
	protected String[] nameArray;

	public ItemFrogCraft(boolean hasSubType){
		this.setCreativeTab(FrogAPI.TAB);
		if (hasSubType) {
			this.setMaxDamage(0);
			this.setHasSubtypes(true);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.translateToLocal(getUnlocalizedName(stack));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(CreativeTabs tabs, NonNullList<ItemStack> list) {
		if (this.isInCreativeTab(tabs)) {
			if (!getHasSubtypes()) {
				super.getSubItems(tabs, list);
				return;
			}
			for (int i = 0; i < nameArray.length; i++) {
				list.add(new ItemStack(this, 1, i));
			}
		}
	}
	
	protected ItemFrogCraft setSubNameArray(String... names) {
		this.nameArray = names;
		return this;
	}

}
