package frogcraftrebirth.common.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.common.lib.block.BlockFrog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFrogOre extends BlockFrog {

	private IIcon[] iconArraySingle;

	public BlockFrogOre() {
		super(Material.rock);
		setBlockName("mineral");
		setHardness(5.0F);
		setResistance(15.0f);
		setSubNameArray("oreCarnallite", "oreDewalquite", "oreFluorapatite", "oreNGH");
		this.iconArraySingle = new IIcon[this.nameArray.length];
	}
	
	public int damageDropped(int meta) {
		return meta == 3 ? 0 : meta;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		if (world.getBlockMetadata(x, y, z) == 3)
			return 1.0F;
		else
			return super.getBlockHardness(world, x, y, z);
	}
	
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random rand) {
		if (meta ==5) {
			int i = 1;
			for (int n = 0; n < fortune; n++) {
			if (rand.nextInt(3) == 1)
				++i;
			}
			return i;
		}
		
		return 1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister r) {
		for (int i = 0; i < nameArray.length; i++) {
			iconArraySingle[i] = r.registerIcon(TEXTURE_MAIN + "ores/" + this.nameArray[i]);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return iconArraySingle[world.getBlockMetadata(x, y, z)];
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iconArraySingle[meta];
	}

}
