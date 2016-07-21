package frogcraftrebirth.common.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.block.BlockFrogContainer;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.tile.TileCondenseTower;
import frogcraftrebirth.common.tile.TileCondenseTowerStructure;
import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCondenseTower extends BlockFrogContainer {

	public BlockCondenseTower() {
		super(Material.IRON, 2);
		setBlockName("multiBlockMachine.CondenseTower");
		setHardness(15.0F);
		setResistance(20.0f);
		setSubNameArray("Core", "Cylinder", "FluidOutput");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		case 0:
			return new TileCondenseTower();
		case 1:
			return new TileCondenseTowerStructure();
		case 2:
			return new TileFluidOutputHatch();
		}
		
		return null;
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return false;
		else {
			if (world.getTileEntity(x, y, z) instanceof TileFrog) {
				player.openGui(FrogCraftRebirth.instance, 2, world, x, y, z);
				return true;
			}
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister r) {
		iconArray[0][0] = r.registerIcon(TEXTURE_MAIN + "CondenseTower_Front");
		iconArray[0][1] = r.registerIcon(TEXTURE_MAIN + "CondenseTower_Front");
		iconArray[0][2] = r.registerIcon(TEXTURE_MAIN + "CondenseTower_Front");
		iconArray[0][3] = r.registerIcon(TEXTURE_MAIN + "CondenseTower_Back");
		iconArray[0][4] = r.registerIcon(TEXTURE_MAIN + "CondenseTower_Side");
		iconArray[0][5] = r.registerIcon(TEXTURE_MAIN + "CondenseTower_Side");
		
		iconArray[1][0] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerCylinder_Top");
		iconArray[1][1] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerCylinder_Top");
		iconArray[1][2] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerCylinder_Side");
		iconArray[1][3] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerCylinder_Side");
		iconArray[1][4] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerCylinder_Side");
		iconArray[1][5] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerCylinder_Side");
		
		iconArray[2][0] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerCylinder_Top");
		iconArray[2][1] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerCylinder_Top");
		iconArray[2][2] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerOutput_Front");
		iconArray[2][3] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerCylinder_Side");
		iconArray[2][4] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerCylinder_Side");
		iconArray[2][5] = r.registerIcon(TEXTURE_MAIN + "CondenseTowerCylinder_Side");
	}

}
