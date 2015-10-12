package frogcraftrewrite.common.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.FrogCraftRebirth;
import frogcraftrewrite.common.item.ItemIngot;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockNitricAcid extends BlockFluidClassic {
	
	IIcon[] icons;
	
	public BlockNitricAcid(Fluid fluid) {
		super(fluid, Material.water);
		this.setBlockName("nitricAcid");
		this.setCreativeTab(FrogCraftRebirth.TAB_FC);
		this.setDensity(fluid.getDensity());
		this.setQuantaPerBlock(8);
		this.setTickRate(10);
	}
	
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) {
			return false;
		}
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) {
			return false;
		}
		return super.displaceIfPossible(world, x, y, z);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return side > 1 ? icons[1] : icons[0];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister r) {
		icons = new IIcon[2];
		icons[0] = r.registerIcon("frogcraftrewrite:fluids/HNO3");
		icons[1] = r.registerIcon("frogcraftrewrite:fluids/HNO3_flow");
	}

	
	@Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (rand.nextBoolean()) return;
		for (int m=-3;m<3;m++) {
			for (int n=-3;n<3;n++) {
				int randInt = rand.nextInt(10);
				if (world.getBlock(x+m, y, z+n) == Blocks.grass && randInt < 7)
					world.setBlock(x+m, y, z+n, Blocks.dirt);
				if (world.getBlock(x+m, y, z+n) == Blocks.dirt && randInt < 5)
					world.setBlock(x+m, y, z+n, Blocks.sand);
				if (world.getBlock(x+m, y, z+n) == Blocks.stone && randInt < 5)
					world.setBlock(x+m, y, z+n, Blocks.cobblestone);
				if (world.getBlock(x+m, y, z+n) == Blocks.cobblestone && randInt < 8)
					world.setBlock(x+m, y, z+n, Blocks.gravel);
				if (world.getBlock(x+m, y, z+n) == Blocks.gravel && randInt < 6)
					world.setBlock(x+m, y, z+n, Blocks.sand);
			}
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		super.onEntityCollidedWithBlock(world, x, y, z, entity);
		if (entity instanceof EntityItem) {
			ItemStack stack = ((EntityItem)entity).getEntityItem();
			if (stack.getItem() instanceof ItemIngot && stack.getItemDamage() == 0) {
				//Check potassium.
				world.createExplosion(entity, x, y, z, 15F, true);
				//TODO: modify explosion scale
			}
		}
	}

}
