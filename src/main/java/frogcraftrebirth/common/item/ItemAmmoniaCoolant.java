package frogcraftrebirth.common.item;

import java.util.Arrays;
import java.util.List;

import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemAmmoniaCoolant extends ItemFrogCraft implements IReactorComponent {

	private final int heatStorage;
	private final String type;
	
	public ItemAmmoniaCoolant(String type, int storage) {
		super(false);
		this.heatStorage = storage;
		this.type = type;
		setUnlocalizedName("CoolantAmmonia"+type);
		setMaxDamage(10000);
	}
	
	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		return Arrays.asList(I18n.format("item.CoolantAmmonia.info", type));
	}
	
	@Override
	public boolean canBePlacedIn(ItemStack stack, IReactor reactor) {
		return true;
	}

	@Override
	public void processChamber(ItemStack yourStack, IReactor reactor, int x, int y, boolean heatrun) {}

	@Override
	public boolean acceptUraniumPulse(ItemStack yourStack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
		return false;
	}

	@Override
	public boolean canStoreHeat(ItemStack yourStack, IReactor reactor, int x, int y) {
		return true;
	}

	@Override
	public int getMaxHeat(ItemStack yourStack, IReactor reactor, int x, int y) {
		return heatStorage;
	}

	@Override
	public int getCurrentHeat(ItemStack yourStack, IReactor reactor, int x, int y) {
		return yourStack.getTagCompound().getInteger("heat");
	}

	@Override
	public int alterHeat(ItemStack yourStack, IReactor reactor, int x, int y, int heat) {
		int coolantHeat;
		try {
			coolantHeat = yourStack.getTagCompound().getInteger("heat");
		} catch (NullPointerException e) {
			yourStack.setTagCompound(new NBTTagCompound());
			coolantHeat = 0;
		}
		
		if (coolantHeat > this.heatStorage) {
			reactor.setItemAt(x, y, null);
			heat = this.heatStorage - coolantHeat + 1;
			return heat;
		}
		
		coolantHeat += heat;
		
		if (coolantHeat < 0) {
			heat = coolantHeat;
			coolantHeat = 0;
		} else {
			heat = 0;
		}
		
		yourStack.setItemDamage(this.getMaxDamage() * coolantHeat / this.heatStorage );
		yourStack.getTagCompound().setInteger("heat", coolantHeat);
		return heat;
	}

	@Override
	public float influenceExplosion(ItemStack yourStack, IReactor reactor) {
		return 0;
	}

}
