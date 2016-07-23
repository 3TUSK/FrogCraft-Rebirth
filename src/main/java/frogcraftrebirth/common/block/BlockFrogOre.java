package frogcraftrebirth.common.block;

import java.util.Random;

import frogcraftrebirth.common.lib.block.BlockFrog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFrogOre extends BlockFrog {

	public BlockFrogOre() {
		super(ORE);
		setUnlocalizedName("mineral");
		setHardness(5.0F);
		setResistance(15.0f);
	}
	
	public int damageDropped(int meta) {
		return meta == 3 ? 0 : meta;
	}

	@Deprecated
	@Override
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
		if (blockState.getBlock().getMetaFromState(blockState) == 3)
			return 1.0F;
		else
			return super.getBlockHardness(blockState, worldIn, pos);
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
	
	public static enum Type {
		CARNALLITE, DEWALQUITE, FLUORAPATITE, NGH;
	}

}
