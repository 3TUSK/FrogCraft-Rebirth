package frogcraftrebirth.common.block;

import java.util.Random;

import frogcraftrebirth.common.lib.block.BlockFrog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;

public class BlockFrogOre extends BlockFrog {
	
	public static final PropertyEnum<Type> TYPE = PropertyEnum.<Type>create("variant", Type.class);

	public BlockFrogOre() {
		super(ORE, "ore", 0, 1, 2);
		setUnlocalizedName("mineral");
		setHardness(5.0F);
		setResistance(15.0f);
		setDefaultState(this.blockState.getBaseState().withProperty(TYPE, Type.CARNALLITE));
	}

	@Override
	protected IProperty<?>[] getPropertyArray() {
		return new IProperty[] { TYPE };
	}
	
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		if (state.getBlock().getMetaFromState(state) ==5) {
			int i = 1;
			for (int n = 0; n < fortune; n++) {
			if (random.nextInt(3) == 1)
				++i;
			}
			return i;
		}
		
		return 1;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE).ordinal();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.blockState.getBaseState().withProperty(TYPE, Type.values()[meta]);
	}
	
	public static enum Type implements IStringSerializable {
		CARNALLITE, DEWALQUITE, FLUORAPATITE/*, NGH*/;
		//Natural Gas Hydrate is canceled again...

		@Override
		public String getName() {
			return this.name().toLowerCase();
		}
	}

}
