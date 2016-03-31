package frogcraftrebirth.common.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.common.FrogItems;
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
		setSubNameArray("stoneBasalt", "stoneMarble", "oreCarnallite", "oreDewalquite", "oreFluorapatite", "oreNGH",
				"oreRuby", "oreSapphire", "oreGreenSapphire");
		this.iconArraySingle = new IIcon[this.nameArray.length];
	}
	
	public int damageDropped(int meta) {
		return meta >= 6 ? meta - 4 : meta;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		switch (world.getBlockMetadata(x, y, z)) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
			return 3.0F;
		case 5:
			return 1.0F;
		default:
			return super.getBlockHardness(world, x, y, z);
		}
	}
	
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return meta >= 6 ? FrogItems.itemIngot : Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(Random rand) {
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
