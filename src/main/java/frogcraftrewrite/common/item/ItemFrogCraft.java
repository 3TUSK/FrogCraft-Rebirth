package frogcraftrewrite.common.item;

import java.util.List;

import frogcraftrewrite.FrogCraftRebirth;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public abstract class ItemFrogCraft extends Item{

	public ItemFrogCraft (){
		setCreativeTab(FrogCraftRebirth.TAB_FC);
		System.out.println("Availability:");
		//General item properties start.
	}
	
	public abstract List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv);
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return StatCollector.translateToLocal(getUnlocalizedName(stack));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List aList, boolean what) {
		aList.addAll(getToolTip(stack, player, what));
	}

}
