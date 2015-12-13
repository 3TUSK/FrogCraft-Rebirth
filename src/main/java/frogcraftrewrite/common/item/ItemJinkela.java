package frogcraftrewrite.common.item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.eventhandler.Event.Result;
import frogcraftrewrite.common.lib.item.ItemFrogCraft;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class ItemJinkela extends ItemFrogCraft {

	public ItemJinkela() {
		super(false);
		setTextureName("frogcraftrewrite:GoldClod");
		setUnlocalizedName("Item_Miscs.GoldClod");
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ) {
		Block block = world.getBlock(x, y, z);
		BonemealEvent event = new BonemealEvent(player, world, block, x, y, z);
		if (!MinecraftForge.EVENT_BUS.post(event))
			return false;
		System.out.println("wtf");
		if (event.getResult() == Result.ALLOW && block instanceof IGrowable) {
			if (((IGrowable)block).func_149851_a(world, x, y, z, !world.isRemote)) {
				player.addChatComponentMessage(new net.minecraft.util.ChatComponentText("妈妈的，金坷垃是我的"));
				if (((IGrowable)block).func_149852_a(world, world.rand, x, y, z)) {
					((IGrowable)block).func_149853_b(world, world.rand, x, y, z);
					world.playAuxSFX(2005, x, y, z, 0);
					return true;
				}
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

}
