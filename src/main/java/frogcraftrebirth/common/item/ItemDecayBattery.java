package frogcraftrebirth.common.item;

import java.util.Arrays;
import java.util.List;

import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import ic2.api.item.IElectricItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDecayBattery extends ItemFrogCraft implements IElectricItem {
	
	public ItemDecayBattery(String name) {
		super(false);
		setMaxDamage(0);
		setMaxStackSize(1);
		setNoRepair();
		setUnlocalizedName(name + "Battery.name");
	}
	
	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		setCharge(itemStack);
		return true;
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
			stack.getTagCompound().setInteger("charge", 1);
		else {
			stack.setTagCompound(new net.minecraft.nbt.NBTTagCompound());
			stack.getTagCompound().setInteger("charge", 1);
		}
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		return Arrays.asList(new String[] {
				I18n.format("item.DecayBattery.info.0"),
				I18n.format("item.DecayBattery.info.1")
		});
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(item, 1, 0));
	}

}
