package frogcraftrewrite.common.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.lib.item.ItemFrogCraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemDust extends ItemFrogCraft {
	
	private static final String DUST_ICON = "frogcraftrewrite:dust/";

	HashMap<Integer, IIcon> iconMap = new HashMap<Integer, IIcon>();
	HashMap<Integer, String> nameMap = new HashMap<Integer, String>();
	
	{
		nameMap.put(0, "Al2O3");
		nameMap.put(1, "CaF2");
		nameMap.put(2, "CaO");
		nameMap.put(3, "CaOH2");
		nameMap.put(4, "Carnallite");
		nameMap.put(5, "CaSiO3");
		nameMap.put(6, "Dewalquite");
		nameMap.put(7, "Fluorapatite");
		nameMap.put(8, "KCl");
		nameMap.put(9, "Magnalium");
		nameMap.put(10, "MgBr2");
		nameMap.put(11, "NH4NO3");
		nameMap.put(12, "TiO2");
		nameMap.put(13, "Urea");
		nameMap.put(14, "V2O5");
	}

	public ItemDust() {
		super(true);
		setUnlocalizedName("Item_Dusts");
	}
	
	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(StatCollector.translateToLocal("item.Item_Dusts."+nameMap.get(stack.getItemDamage())+".info"));
		return list;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int damage) {
		return iconMap.get(damage);
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
		for (int n=0;n<nameMap.size();n++) {
			iconMap.put(n, reg.registerIcon(DUST_ICON+nameMap.get(n)));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+"."+nameMap.get(stack.getItemDamage());
	}

	@Override
	public int getSubItemNumber() {
		return nameMap.size();
	}
	

}
