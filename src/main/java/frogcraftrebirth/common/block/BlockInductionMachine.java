package frogcraftrebirth.common.block;

import frogcraftrebirth.common.lib.block.BlockFrogContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
@Deprecated
public class BlockInductionMachine extends BlockFrogContainer {

	public BlockInductionMachine() {
		super(Material.IRON);
		setUnlocalizedName("inductionalMachine");
		setSubNameArray("Furnace", "Extractor", "Macerator", "Compressor");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}
	/* Inherited code from 1.7.10, which inherited from 1.6.2. To be fixed
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		world.func_147479_m(x, y, z);//markBlockForRenderUpdate(x, y, z)
		
		if (random.nextInt() > 90) {
			float var7 = (float)x + 1.0F;
    		float var8 = (float)y + 1.0F;
    		float var9 = (float)z + 1.0F;

    		for (int var10 = 0; var10 < 4; ++var10){
    			float var11 = -0.2F - random.nextFloat() * 0.6F;
    			float var12 = -0.1F + random.nextFloat() * 0.2F;
    			float var13 = -0.2F - random.nextFloat() * 0.6F;
    			world.spawnParticle("smoke", (double)(var7 + var11), (double)(var8 + var12), (double)(var9 + var13), 0.0D, 0.0D, 0.0D);
    		}
		}
	}*/

}
