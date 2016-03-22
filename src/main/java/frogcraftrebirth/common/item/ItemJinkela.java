package frogcraftrebirth.common.item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.eventhandler.Event.Result;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import thaumcraft.api.IWarpingGear;

@Interface(iface = "thaumcraft.api.IWarpingGear", modid = "thaumcraft")
public class ItemJinkela extends ItemFrogCraft implements IWarpingGear {

	public ItemJinkela() {
		super(false);
		setTextureName(TEXTURE_MAIN + "GoldClod");
		setUnlocalizedName("Item_Miscs.GoldClod");
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ) {
		Block block = world.getBlock(x, y, z);
		BonemealEvent evt = new BonemealEvent(player, world, block, x, y, z);
		MinecraftForge.EVENT_BUS.post(evt);
		
		if (evt.getResult() == Result.ALLOW) {
			if (block instanceof IGrowable) {
				IGrowable growable = (IGrowable)block;
				if (growable.func_149851_a(world, x, y, z, world.isRemote) && !world.isRemote && growable.func_149852_a(world, world.rand, x, y, z))
					growable.func_149853_b(world, itemRand, x, y, z);
			}
		}
		return false;
	}

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
