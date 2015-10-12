package frogcraftrewrite.common.item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.IGrowable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemJinkela extends ItemFrogCraft {

	public ItemJinkela() {
		super(false);
		setTextureName("frogcraftrewrite:GoldClod");
		setUnlocalizedName("Item_Miscs.GoldClod");
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ) {
		if (world.isRemote)
			return false;
		if (world.getBlock(x, y, z) instanceof IGrowable) {
			((IGrowable)world.getBlock(x, y, z)).func_149851_a(world, x, y, z, world.isRemote);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List list) {
		list.add(new ItemStack(item, 1, 0));
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		List<String> list = new ArrayList<String>();
		list.add(StatCollector.translateToLocal("item.Item_Miscs.GoldClod.info"));
		return list;
	}

	@Override
	public int getSubItemNumber() {
		return 1;
	}

}
