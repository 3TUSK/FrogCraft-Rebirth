package frogcraftrebirth.common.block;

import frogcraftrebirth.common.lib.block.BlockFrog;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class BlockFrogOre extends BlockFrog {
	
	public static final PropertyEnum<Type> TYPE = PropertyEnum.create("variant", Type.class);

	public BlockFrogOre() {
		super(Material.ROCK, "ore", 0, 1, 2);
		setUnlocalizedName("mineral");
		setHardness(5.0F);
		setResistance(15.0f);
		setDefaultState(this.blockState.getBaseState().withProperty(TYPE, Type.CARNALLITE));
		setHarvestLevel("shovel", 2, new BlockStateContainer.Builder(this).add(TYPE).build().getBaseState().withProperty(TYPE, Type.CARNALLITE));
		setHarvestLevel("pickaxe", 2, new BlockStateContainer.Builder(this).add(TYPE).build().getBaseState().withProperty(TYPE, Type.DEWALQUITE));
		setHarvestLevel("pickaxe", 2, new BlockStateContainer.Builder(this).add(TYPE).build().getBaseState().withProperty(TYPE, Type.FLUORAPATITE));
	}

	@Override
	protected IProperty<?>[] getPropertyArray() {
		return new IProperty[] { TYPE };
	}
	
	@Override
	public boolean isToolEffective(String type, IBlockState state) {
		switch (state.getValue(TYPE)) {
			case CARNALLITE :
				return "shovel".equals(type);
			case DEWALQUITE : 
			case FLUORAPATITE :
				return "pickaxe".equals(type);
			default :
				return true;
		}
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(TYPE).ordinal();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE).ordinal();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.blockState.getBaseState().withProperty(TYPE, Type.values()[meta]);
	}
	
	public enum Type implements IStringSerializableEnumImpl {
		CARNALLITE, DEWALQUITE, FLUORAPATITE
    }

}
