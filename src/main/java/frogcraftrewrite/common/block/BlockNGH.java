package frogcraftrewrite.common.block;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.FrogCraftRebirth;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockNGH extends BlockPackedIce {

	public BlockNGH() {
		this.slipperiness = 0.95F;
		this.setCreativeTab(FrogCraftRebirth.TAB_FC);
		this.setTickRandomly(true);
		this.setBlockName("naturalGasHydrate");
		flammableBlocks = new ArrayList<Block>();
		flammableBlocks.add(Blocks.torch);
	}
	
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int anInt = rand.nextInt(10);
		int air = 0;
		
		if (anInt < 5) {
			for (int n = -5; n < 6; n++) {
				if (world.getBlock(x+n, y+n, z+n) == Blocks.air) {
					++air;
				}
				
				if (world.getBlock(x+n, y+n, z+n) == Blocks.fire) {
					if (air > 10 && rand.nextInt(100) < 25) {
						world.newExplosion((Entity)null, x, y, z, 10.0F, true, true);//Big Bang!
					} else if (rand.nextInt(100) < 5) {
						world.setBlock(x, y+1, z, Blocks.fire);
					}
				}
			}
		}
		
		if (world.getBlock(x, y+1, z) == Blocks.fire && rand.nextInt(10) < 2) {
			world.newExplosion((Entity)null, x, y, z, 5.0F, true, true);//Big Bang again.
		}
	}
	
	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return face == ForgeDirection.UP;
	}
	
	//Feature like netherrack.
	@Override
	public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
		return side == ForgeDirection.UP;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister iconReg)
    {
        this.blockIcon = iconReg.registerIcon("frogcraft:Ores/oreNGH");
    }
	
	private ArrayList<Block> flammableBlocks;

}
