package frogcraftrewrite.common.block.mps;

import java.util.List;

import frogcraftrewrite.common.block.mps.BlockMPS;
import ic2.api.item.IElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
//What a heck.
public class ItemBlockMPS extends ItemBlock implements IElectricItem{
	
	public double energyStored, energyMax;

	public ItemBlockMPS() {
		super(new BlockMPS());
		setMaxStackSize(1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, @SuppressWarnings("rawtypes") List info, boolean p_77624_4_) {
		info.add(player.getDisplayName());
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return energyStored;
	}
	
	@Override
	public int getMaxDamage() {
		return (int)energyMax;
	}
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		//TODO
		return false;
	}
	
	// IC2 Start
	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return true;
	}

	@Override
	public Item getChargedItem(ItemStack itemStack) {
		return this;
	}

	@Override
	public Item getEmptyItem(ItemStack itemStack) {
		return this;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		return itemStack.stackTagCompound.getDouble("maxEnergy");
	}

	@Override
	public int getTier(ItemStack itemStack) {
		return 1;
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		return 2048;
	}
	
	//IC2 End

}
