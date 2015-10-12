package frogcraftrewrite.common.block.acwindmill;

import java.util.List;

import frogcraftrewrite.common.item.ItemFrogCraft;
import frogcraftrewrite.common.lib.FrogItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ACWindmillFan extends ItemFrogCraft {
	
	public ACWindmillFan() {
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
			player.inventory.consumeInventoryItem(FrogItems.acwinmillFan);
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

}
