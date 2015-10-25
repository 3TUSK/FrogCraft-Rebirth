package frogcraftrewrite.common.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.FrogCraftRebirth;
import frogcraftrewrite.common.tile.TileInductionalCompressor;
import frogcraftrewrite.common.tile.TileInductionalEFurnace;
import frogcraftrewrite.common.tile.TileInductionalExtractor;
import frogcraftrewrite.common.tile.TileInductionalMacerator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockInductionMachine extends BlockContainer {

	public BlockInductionMachine() {
		super(Material.iron);
		setCreativeTab(FrogCraftRebirth.TAB_FC);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch(meta) {
			case 0:
				return new TileInductionalEFurnace();
			case 1:
				return new TileInductionalMacerator();
			case 2:
				return new TileInductionalExtractor();
			case 3:
				return new TileInductionalCompressor();
			default:
				return null;
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		world.func_147479_m(x, y, z);//markBlockForRenderUpdate(x, y, z)
		
		if (random.nextInt() > 90) { //TODO specify which block has such a particle effect.
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
		
	}

}
