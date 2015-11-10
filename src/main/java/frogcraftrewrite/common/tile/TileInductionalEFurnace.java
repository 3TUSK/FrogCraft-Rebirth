package frogcraftrewrite.common.tile;

import frogcraftrewrite.common.lib.tile.TileFrogInductionalDevice;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class TileInductionalEFurnace extends TileFrogInductionalDevice{

	@Override
	public String getInventoryName() {
		return "TileEntityIndustrialEFurnace";
	}

	@Override
	protected boolean canProcess() {
		for (ItemStack input : inv) {
			ItemStack output = FurnaceRecipes.smelting().getSmeltingResult(input);
			if (output != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected ItemStack getOutputFrom(ItemStack input) {
		return FurnaceRecipes.smelting().getSmeltingResult(input).copy();
	}

}
