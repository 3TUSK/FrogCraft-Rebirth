package frogcraftrewrite.common.tile;

import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;

public class TileInductionalExtractor extends TileFrogInductionalDevice{

	public TileInductionalExtractor() {
		super("TileEntityIndustrialExtrator");
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
		return Recipes.extractor.getOutputFor(input, false).items.get(0);
	}

}
