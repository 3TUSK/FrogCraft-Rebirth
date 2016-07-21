package frogcraftrebirth.common.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.common.lib.block.BlockFrogContainer;
/*import frogcraftrebirth.common.tile.TileInductionalCompressor;
import frogcraftrebirth.common.tile.TileInductionalEFurnace;
import frogcraftrebirth.common.tile.TileInductionalExtractor;
import frogcraftrebirth.common.tile.TileInductionalMacerator;*/
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
@Deprecated
public class BlockInductionMachine extends BlockFrogContainer {

	public BlockInductionMachine() {
		super(Material.iron);
		setBlockName("inductionalMachine");
		setSubNameArray("Furnace", "Extractor", "Macerator", "Compressor");
		this.iconArray = new IIcon[this.nameArray.length][6];
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		/*switch(meta) {
			case 0:
				return new TileInductionalEFurnace();
			case 1:
				return new TileInductionalMacerator();
			case 2:
				return new TileInductionalExtractor();
			case 3:
				return new TileInductionalCompressor();
			default:*/
				return null;
		//}
	}
	
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
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for (int i=0;i<nameArray.length;i++) {
			iconArray[i][0] = reg.registerIcon(TEXTURE_MAIN + "IndustrialDevice_Top");
			iconArray[i][1] = reg.registerIcon(TEXTURE_MAIN + "IndustrialDevice_Side");
			String path = "";
			switch (i) {
			case 0:
				path = TEXTURE_MAIN + "IndustrialFurnace_Front";
			case 1:
				path = TEXTURE_MAIN + "IndustrialMacerator_Front";
			case 2:
				path = TEXTURE_MAIN + "IndustrialExtractor_Front";
			case 3:
				path = TEXTURE_MAIN + "IndustrialCompressor_Front";
			}
			iconArray[i][2] = reg.registerIcon(path);
			iconArray[i][3] = reg.registerIcon(TEXTURE_MAIN + "IndustrialDevice_Side");
			iconArray[i][4] = reg.registerIcon(TEXTURE_MAIN + "IndustrialDevice_Side");
			iconArray[i][5] = reg.registerIcon(TEXTURE_MAIN + "IndustrialDevice_Side");
		}
	}

}
