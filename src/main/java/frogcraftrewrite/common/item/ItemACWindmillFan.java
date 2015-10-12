package frogcraftrewrite.common.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.tile.TileACWindmillTurbine;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemACWindmillFan extends ItemFrogCraft {
	
	public ItemACWindmillFan() {
		super(false);
		setMaxDamage(0);
		setMaxStackSize(1);
		setNoRepair();
		setTextureName("frogcraftrewrite:ACWindMill_Fan");
		setUnlocalizedName("ACWindMill_Rotor.name");
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack s, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileACWindmillTurbine) {
			((TileACWindmillTurbine)tile).hasRotor = true;
			player.inventory.consumeInventoryItem(this);
			return true;
		}
		return false;
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		return new java.util.ArrayList<String>();
	}

	@Override
	public int getSubItemNumber() {
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List list) {
		list.add(new ItemStack(item, 1, 0));
	}

}
