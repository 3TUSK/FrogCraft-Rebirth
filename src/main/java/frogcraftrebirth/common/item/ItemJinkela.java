package frogcraftrebirth.common.item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Optional.Interface;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IWarpingGear;

@Interface(iface = "thaumcraft.api.IWarpingGear", modid = "thaumcraft")
public class ItemJinkela extends ItemFrogCraft implements IWarpingGear {

	public ItemJinkela() {
		super(false);
		setTextureName(TEXTURE_MAIN + "GoldClod");
		setUnlocalizedName("Item_Miscs.GoldClod");
	}
	
	//onItemUse

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		List<String> list = new ArrayList<String>();
		list.add(StatCollector.translateToLocal("item.Item_Miscs.GoldClod.info"));
		return list;
	}

	@Override
	public int getWarp(ItemStack stack, EntityPlayer player) {
		return 2;
	}

}
