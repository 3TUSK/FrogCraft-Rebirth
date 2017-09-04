package frogcraftrebirth.common.item;

import java.util.List;

import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import ic2.api.item.IElectricItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
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
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format("item.DecayBattery.info.0"));
		tooltips.add(I18n.format("item.DecayBattery.info.1"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (this.isInCreativeTab(tab)) {
			list.add(new ItemStack(this, 1, 0));
		}
	}

}
