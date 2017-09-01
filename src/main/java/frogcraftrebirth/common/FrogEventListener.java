package frogcraftrebirth.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogRegistees;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.oredict.OreDictionary;

public class FrogEventListener {
	
	public static final Random RAND = new Random();
	
	@SubscribeEvent
	public void onExplosion(ExplosionEvent event) {
		// TODO rewrite the logic
	}
	
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		if (event.getSource() == FrogAPI.TIBERIUM) {
			event.getEntity().entityDropItem(new ItemStack(FrogRegistees.TIBERIUM, RAND.nextInt(3), RAND.nextInt(10)), 0.50001F);
		}
	}
	
	@SubscribeEvent
	public void onPlayerDrop(PlayerDropsEvent event) {
		if (event.getSource() == FrogAPI.TIBERIUM) {
			Iterator<EntityItem> iterator = event.getDrops().iterator();
			ArrayList<EntityItem> newDrops = new ArrayList<>();
			while (iterator.hasNext()) {
				EntityItem item = iterator.next();
				if (RAND.nextInt(10) == 0) {
					EntityItem replacement = new EntityItem(item.getEntityWorld(), item.posX, item.posY, item.posZ, new ItemStack(FrogRegistees.TIBERIUM, RAND.nextInt(3), item.getItem().getCount()));
					replacement.motionX = item.motionX;
					replacement.motionY = item.motionY;
					replacement.motionZ = item.motionZ;
					newDrops.add(replacement);
					iterator.remove();
				}
			}
			event.getDrops().addAll(newDrops);
		}
	}

	@SubscribeEvent
	public void onFuelValueQueried(FurnaceFuelBurnTimeEvent event) {
		if (OreDictionary.doesOreNameExist("dustSulfur")) {
			for (ItemStack stack : OreDictionary.getOres("dustSulfur")) {
				if (stack.isItemEqual(event.getItemStack())) {
					event.setBurnTime(1600);
					return;
				}
			}
		}
	}

}
