package frogcraftrewrite.common.item;

import frogcraftrewrite.common.block.BlockMPS;
import ic2.api.item.IElectricItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMobliePowerStation extends ItemFrogBlock implements IElectricItem {

	public ItemMobliePowerStation(Block block) {
		super(block);
		assert this.field_150939_a instanceof BlockMPS : new IllegalArgumentException("Failure on initialize MPS block!");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.BlockMobilePS";
	}

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
		return itemStack.stackTagCompound.getDouble("maxCharge");
	}

	@Override
	public int getTier(ItemStack itemStack) {
		return 1;
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		return 2048;
	}

}
