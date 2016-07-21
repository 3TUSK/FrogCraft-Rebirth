package frogcraftrebirth.common.item;

import java.util.ArrayList;
import java.util.List;

import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemCell extends ItemFrogCraft{
	
	public ItemCell() {
		super(true);
		super.setUnlocalizedName("Item_Cells");
		setSubNameArray("Ammonia", "Argon", "Benzene", "Bromine", "CO", "CO2", "CoalTar", "Fluorine", "HNO3", "LiquifiedAir", "NH4NO3", "NO", "Oxygen", "SO2", "SO3", "Urea");
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+".cell_"+this.nameArray[stack.getItemDamage()];
	}
	
	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(I18n.format("item.Item_Cells.cell_"+nameArray[stack.getItemDamage()]+".info"));
		return list;
	}

}
