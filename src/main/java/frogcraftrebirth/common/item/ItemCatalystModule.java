package frogcraftrebirth.common.item;

import java.util.ArrayList;
import java.util.List;

import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemCatalystModule extends ItemFrogCraft {
	
	public ItemCatalystModule() {
		super(true);
		setSubNameArray("Heating", "Electrolize", "Ammonia", "V2O5");
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		List<String> list = new ArrayList<String>();
		list.add(I18n.format("item.Item_Miscs."+nameArray[stack.getItemDamage()]+"Module.info"));
		return list;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item.Item_Miscs."+nameArray[stack.getItemDamage()]+"Module";
	}
	
}
