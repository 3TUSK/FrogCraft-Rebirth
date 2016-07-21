package frogcraftrebirth.common.item;

import java.util.ArrayList;
import java.util.List;

import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemDust extends ItemFrogCraft {

	public ItemDust() {
		super(true);
		setSubNameArray("Al2O3", "CaF2", "CaO", "CaOH2", "Carnallite", "CaSiO3", "Dewalquite", "Fluorapatite", "KCl", "Magnalium", "MgBr2", "NH4NO3", "TiO2", "Urea", "V2O5");
		setUnlocalizedName("Item_Dusts");
	}
	
	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(I18n.format("item.Item_Dusts."+nameArray[stack.getItemDamage()]+".info"));
		return list;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+"."+nameArray[stack.getItemDamage()];
	}

}
