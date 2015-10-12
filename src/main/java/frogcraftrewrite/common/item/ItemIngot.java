package frogcraftrewrite.common.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemIngot extends ItemFrogCraft{
	
	private static final String INGOT_ICON = "frogcraftrewrite:ingot/";

	HashMap<Integer, IIcon> iconMap = new HashMap<Integer, IIcon>();
	HashMap<Integer, String> nameMap = new HashMap<Integer, String>();
	
	{
		nameMap.put(0, "K");
		nameMap.put(1, "P");
		nameMap.put(2, "Ruby");
		nameMap.put(3, "Sapphire");
		nameMap.put(4, "GreenSapphire");
		nameMap.put(5, "NaturalGasHydrate");
	}
	
	public ItemIngot() {
		super(true);
		setUnlocalizedName("Item_Ingots");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int damage) {
		return iconMap.get(damage);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister reg) {
		for (int n=0;n<nameMap.size();n++) {
			iconMap.put(n, reg.registerIcon(INGOT_ICON+nameMap.get(n)));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+"."+nameMap.get(stack.getItemDamage());
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(StatCollector.translateToLocal("item.Item_Ingots."+nameMap.get(stack.getItemDamage())+".info"));
		switch (stack.getItemDamage()) {
			case 0:
				list.add(StatCollector.translateToLocal("item.Item_Ingots.ingotDesc"));
				break;
			case 1:
				list.add(StatCollector.translateToLocal("item.Item_Ingots.ingotDesc"));
				break;
			case 2:
				list.add(StatCollector.translateToLocal("item.Item_Ingots.gemDesc"));
				break;
			case 3:
				list.add(StatCollector.translateToLocal("item.Item_Ingots.gemDesc"));
				break;
			case 4:
				list.add(StatCollector.translateToLocal("item.Item_Ingots.gemDesc"));
				break;
			case 5:
				list.add(StatCollector.translateToLocal("item.Item_Ingots.nghDesc"));
				break;
			default:
				break;
		}
		return list;
	}

	@Override
	public int getSubItemNumber() {
		return nameMap.size();
	}

}
