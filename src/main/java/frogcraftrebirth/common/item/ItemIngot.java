package frogcraftrebirth.common.item;

import java.util.ArrayList;
import java.util.List;

import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemIngot extends ItemFrogCraft {

	public ItemIngot() {
		super(true);
		setUnlocalizedName("Item_Ingots");
		setSubNameArray("K", "P", "NaturalGasHydrate", "Briquette", "CoalCokeShattered");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+"."+nameArray[stack.getItemDamage()];
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(I18n.format("item.Item_Ingots."+nameArray[stack.getMetadata()]+".info"));
		switch (stack.getItemDamage()) {
			case 0: {
				list.add(I18n.format("item.Item_Ingots.ingotDesc"));
				break;
			}
			case 1: {
				list.add(I18n.format("item.Item_Ingots.ingotDesc"));
				break;
			}
			case 2: {
				list.add(I18n.format("item.Item_Ingots.nghDesc"));
				break;
			}
			default:
				break;
		}
		return list;
	}

}
