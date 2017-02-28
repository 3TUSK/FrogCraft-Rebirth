package frogcraftrebirth.common.lib.item;

import java.util.List;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import frogcraftrebirth.api.FrogAPI;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

@SuppressWarnings("deprecation")
public abstract class ItemFrogCraft extends Item {
	
	protected String[] nameArray;

	public ItemFrogCraft(boolean hasSubType){
		this.setCreativeTab(FrogAPI.TAB);
		if (hasSubType) {
			this.setMaxDamage(0);
			this.setHasSubtypes(true);
		}
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.translateToLocal(getUnlocalizedName(stack));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List<ItemStack> list) {
		if (!getHasSubtypes()) {
			super.getSubItems(item, tabs, list);
			return;
		}
		for (int i = 0; i < nameArray.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	protected ItemFrogCraft setSubNameArray(String... names) {
		this.nameArray = names;
		return this;
	}

}
