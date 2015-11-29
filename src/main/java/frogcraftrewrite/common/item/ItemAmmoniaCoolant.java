package frogcraftrewrite.common.item;

import java.util.ArrayList;
import java.util.List;

import frogcraftrewrite.common.lib.item.ItemFrogCraft;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class ItemAmmoniaCoolant extends ItemFrogCraft implements IReactorComponent{

	private int heatStorage;
	private String type;
	
	public ItemAmmoniaCoolant(String type, int storage) {
		super(false);
		this.heatStorage = storage;
		this.type = type;
		setUnlocalizedName("CoolantAmmonia"+type);
		setTextureName("frogcraftrewrite:Coolant_NH3_"+type);
		setMaxDamage(10000);
	}
	
	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(StatCollector.translateToLocalFormatted("item.CoolantAmmonia.info", type));
		return list;
	}

	@Override
	public void processChamber(IReactor reactor, ItemStack yourStack, int x, int y, boolean heatrun) {}

	@Override
	public boolean acceptUraniumPulse(IReactor reactor, ItemStack yourStack,
			ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY,
			boolean heatrun) {
		return false;
	}

	@Override
	public boolean canStoreHeat(IReactor reactor, ItemStack yourStack, int x, int y) {
		return true;
	}

	@Override
	public int getMaxHeat(IReactor reactor, ItemStack yourStack, int x, int y) {
		return heatStorage;
	}

	@Override
	public int getCurrentHeat(IReactor reactor, ItemStack yourStack, int x, int y) {
		return yourStack.stackTagCompound.getInteger("heat");
	}

	//Currently the ammonia coolant won't be used up...
	@Override
	public int alterHeat(IReactor reactor, ItemStack yourStack, int x, int y, int heat) {
		int coolantHeat;
		try {
			coolantHeat = yourStack.stackTagCompound.getInteger("heat");
		} catch (NullPointerException e) {
			yourStack.stackTagCompound = new NBTTagCompound();
			yourStack.stackTagCompound.setInteger("heat", 0);
			coolantHeat = 0;
		}
		
		if (coolantHeat/getMaxHeat(reactor, yourStack, x, y) >= 1.0F) {
			return heat;
		}
		
		coolantHeat += heat;
		
		if (coolantHeat < 0) {
			heat = coolantHeat;
			coolantHeat = 0;
		} else {
			heat = 0;
		}
		
		yourStack.stackTagCompound.setInteger("heat", coolantHeat);
		return heat;
	}

	@Override
	public float influenceExplosion(IReactor reactor, ItemStack yourStack) {
		return 0;
	}

	@Override
	public int getSubItemNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
