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

public class ItemCell extends ItemFrogCraft{

	private static final String CELL_ICON = "frogcraftrewrite:cell";
	
	HashMap<Integer, IIcon> iconMap = new HashMap<Integer, IIcon>();
	HashMap<Integer, String> nameMap = new HashMap<Integer, String>();
	
	{
		nameMap.put(0, "Ammonia");
		nameMap.put(1, "Argon");
		nameMap.put(2, "Benzene");
		nameMap.put(3, "Bromine");
		nameMap.put(4, "CO");
		nameMap.put(5, "CO2");
		nameMap.put(6, "CoalTar");
		nameMap.put(7, "Fluorine");
		nameMap.put(8, "HNO3");
		nameMap.put(9, "LiquifiedAir");
		nameMap.put(10, "NH4NO3");
		nameMap.put(11, "NO");
		nameMap.put(12, "Oxygen");
		nameMap.put(13, "SO2");
		nameMap.put(14, "SO3");
		nameMap.put(15, "Urea");
	}
	
	public ItemCell() {
		super(true);
		super.setUnlocalizedName("Item_Cells");
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+".cell_"+nameMap.get(stack.getItemDamage());
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister reg) {
		for (int n=0;n<nameMap.size();n++) {
			iconMap.put(n, reg.registerIcon(CELL_ICON+"/cell_"+nameMap.get(n)));
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int damage) {
		return iconMap.get(damage);
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(StatCollector.translateToLocal("item.Item_Cells.cell_"+nameMap.get(stack.getItemDamage())+".info"));
		return list;
	}

	@Override
	public int getSubItemNumber() {
		return nameMap.size();
	}

}
