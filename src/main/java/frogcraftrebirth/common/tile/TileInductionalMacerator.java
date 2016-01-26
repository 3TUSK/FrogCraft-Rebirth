package frogcraftrebirth.common.tile;

import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;

public class TileInductionalMacerator extends TileFrogInductionalDevice{

	public TileInductionalMacerator() {
		super("TileEntityIndustrialCompressor");
	}

	@Override
	protected boolean canProcess() {
		for (ItemStack input : inv) {
			ItemStack output = getOutputFrom(input);
			if (output != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected ItemStack getOutputFrom(ItemStack input) {
		return Recipes.macerator.getOutputFor(input, false).items.get(0);
	}

}
