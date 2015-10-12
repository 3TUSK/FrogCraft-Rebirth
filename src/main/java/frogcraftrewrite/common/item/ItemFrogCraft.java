package frogcraftrewrite.common.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.FrogCraftRebirth;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public abstract class ItemFrogCraft extends Item{

	public ItemFrogCraft(boolean hasSubType){
		this.setCreativeTab(FrogCraftRebirth.TAB_FC);
		//General item properties start.
		this.setHasSubtypes(hasSubType);
	}
	
	public abstract List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv);
	
	public abstract int getSubItemNumber();
	
	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List aList, boolean what) {
		aList.addAll(getToolTip(stack, player, what));
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return StatCollector.translateToLocal(getUnlocalizedName(stack));
	}
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs tabs, @SuppressWarnings("rawtypes")List list) {
		if (!getHasSubtypes()) super.getSubItems(item, tabs, list);
		for (int i=0;i<getSubItemNumber();i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

}
