package frogcraftrewrite.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.lib.item.ItemFrogCraft;
import ic2.api.item.IElectricItem;

public class ItemDecayBattery extends ItemFrogCraft implements IElectricItem {
	
	public ItemDecayBattery(String name) {
		super(false);
		setMaxDamage(0);
		setMaxStackSize(1);
		setNoRepair();
		setTextureName("frogcraftrewrite:Battery"+name);
		setUnlocalizedName(name+"Battery.name");
	}
	
	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		setCharge(itemStack);
		return true;
	}

	@Override
	public Item getChargedItem(ItemStack itemStack) {
		setCharge(itemStack);
		return this;
	}

	@Override
	public Item getEmptyItem(ItemStack itemStack) {
		setCharge(itemStack);
		return this;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		setCharge(itemStack);
		return 1;
	}

	@Override
	public int getTier(ItemStack itemStack) {
		setCharge(itemStack);
		return 1;
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		setCharge(itemStack);
		return 1;
	}
	
	private void setCharge(ItemStack stack) {
		if (stack.hasTagCompound())
			stack.stackTagCompound.setInteger("charge", 1);
		else {
			stack.stackTagCompound = new net.minecraft.nbt.NBTTagCompound();
			stack.stackTagCompound.setInteger("charge", 1);
		}
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		return java.util.Arrays.asList(new String[] {
				StatCollector.translateToLocal("item.DecayBattery.info.0"),
				StatCollector.translateToLocal("item.DecayBattery.info.1")
		});
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
