package frogcraftrewrite.common.item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.lib.item.ItemFrogCraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemIngot extends ItemFrogCraft{
	
	private static final String INGOT_ICON = "frogcraftrewrite:ingot/";
	
	public ItemIngot() {
		super(true);
		setUnlocalizedName("Item_Ingots");
		setSubNameArray("K", "P", "Ruby", "Sapphire", "GreenSapphire", "NaturalGasHydrate", "Briquette", "CoalCokeShattered");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int damage) {
		return iconArray[damage];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister reg) {
		for (int n=0;n<nameArray.length;n++) {
			iconArray[n] = reg.registerIcon(INGOT_ICON+nameArray[n]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+"."+nameArray[stack.getItemDamage()];
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(StatCollector.translateToLocal("item.Item_Ingots."+nameArray[stack.getItemDamage()]+".info"));
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
			case 5: {
				list.add(StatCollector.translateToLocal("item.Item_Ingots.nghDesc"));
				break;
			}
			case 6:
			case 7:
			default:
				break;
		}
		return list;
	}

}
