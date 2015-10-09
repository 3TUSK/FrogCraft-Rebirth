package frogcraftrewrite.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

import ic2.api.item.IElectricItem;

public class ItemDecayBattery extends ItemFrogCraft implements IElectricItem {
	
	public ItemDecayBattery(String name) {
		super();
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
				"A radioactive decay battery",
				"Provide you with infinity electricity with high cost!"
		});
	}

}
