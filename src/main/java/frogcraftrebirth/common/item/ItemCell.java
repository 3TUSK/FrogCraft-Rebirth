package frogcraftrebirth.common.item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemCell extends ItemFrogCraft{

	private static final String CELL_ICON = TEXTURE_MAIN + "cell/";
	
	public ItemCell() {
		super(true);
		super.setUnlocalizedName("Item_Cells");
		setSubNameArray("Ammonia", "Argon", "Benzene", "Bromine", "CO", "CO2", "CoalTar", "Fluorine", "HNO3", "LiquifiedAir", "NH4NO3", "NO", "Oxygen", "SO2", "SO3", "Urea");
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+".cell_"+this.nameArray[stack.getItemDamage()];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister reg) {
		for (int n=0;n<nameArray.length;n++) {
			FrogCraftRebirth.FROG_LOG.debug(nameArray[n]);
			iconArray[n] = reg.registerIcon(CELL_ICON+"cell_"+this.nameArray[n]);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int damage) {
		return iconArray[damage];
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(StatCollector.translateToLocal("item.Item_Cells.cell_"+nameArray[stack.getItemDamage()]+".info"));
		return list;
	}

}
