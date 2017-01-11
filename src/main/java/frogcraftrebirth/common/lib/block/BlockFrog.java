package frogcraftrebirth.common.lib.block;

import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockFrog extends Block {
	
	public static final PropertyBool WORKING = PropertyBool.create("working");

	public static final Material ORE = Material.ROCK;
	public static final Material MACHINE = Material.IRON;
	public static final Material TIBERIUM = Material.GLASS;
	
	protected final int[] metaArrayForCreativeTab;

	/**
	 * @param material The block {@link Material}
	 * @param registryName The unique identifier for registry.
	 * @param metaForDisplay An array of integer, used for determining which blocks will show in creative tab
	 */
	protected BlockFrog(Material material, String registryName, int... metaForDisplay) {
		super(material);
		setRegistryName(registryName);
		setCreativeTab(FrogAPI.TAB);
		this.metaArrayForCreativeTab = metaForDisplay;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, getPropertyArray());
	}
	
	protected abstract IProperty<?>[] getPropertyArray();
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getBlock().getMetaFromState(state);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for (int i : metaArrayForCreativeTab) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	protected TileEntity getTileEntitySafe(IBlockAccess access, BlockPos pos) {
		if (access instanceof ChunkCache) {
			return ((ChunkCache)access).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK);
		} else {
			return access.getTileEntity(pos);
		}
	}

}
