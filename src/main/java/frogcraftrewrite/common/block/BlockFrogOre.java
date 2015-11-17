package frogcraftrewrite.common.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.lib.block.BlockFrog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockFrogOre extends BlockFrog {
	
	IIcon[] iconArray;

	public BlockFrogOre() {
		super(Material.rock);
		setBlockName("mineral");
		setHardness(10.0F);
		setResistance(15.0f);
		setSubNameArray("stoneBasalt", "stoneMarble", "oreCarnallite", "oreDewalquite", "oreFluorapatite", "oreNGH", "oreRuby", "oreSapphire", "oreGreenSapphire");
		this.iconArray = new IIcon[this.nameArray.length];
	}
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		switch (world.getBlockMetadata(x, y, z)) {
			case 0: return 10.0F;
			case 1: return 10.0F;
			case 2: return 2.0F;
			case 3: return 13.0F;
			case 4: return 20.0F;
			case 5: return 1.0F;
			case 6: return 15.0F;
			case 7: return 15.0F;
			case 8: return 15.0F;
		}
		return super.getBlockHardness(world, x, y, z);
	}
	
	public int quantityDropped(Random rand) {
		return 2*rand.nextInt(5);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister r) {
		for (int i=0;i<iconArray.length;i++) {
			iconArray[i] = r.registerIcon("frogcraftrewrite:ores/"+this.nameArray[i]);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iconArray[meta];
	}

}
