/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.block;

import frogcraftrebirth.common.lib.block.BlockFrog;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class BlockFrogOre extends BlockFrog {
	
	public static final PropertyEnum<Type> TYPE = PropertyEnum.create("variant", Type.class);

	public BlockFrogOre() {
		super(Material.ROCK, "ore", 0, 1, 2);
		setUnlocalizedName("mineral");
		setHardness(5.0F);
		setResistance(15.0f);
		setDefaultState(this.blockState.getBaseState().withProperty(TYPE, Type.CARNALLITE));
		setHarvestLevel("shovel", 2, new BlockStateContainer.Builder(this).add(TYPE).build().getBaseState().withProperty(TYPE, Type.CARNALLITE));
		setHarvestLevel("pickaxe", 2, new BlockStateContainer.Builder(this).add(TYPE).build().getBaseState().withProperty(TYPE, Type.DEWALQUITE));
		setHarvestLevel("pickaxe", 2, new BlockStateContainer.Builder(this).add(TYPE).build().getBaseState().withProperty(TYPE, Type.FLUORAPATITE));
	}

	@Override
	protected IProperty<?>[] getPropertyArray() {
		return new IProperty[] { TYPE };
	}
	
	@Override
	public boolean isToolEffective(String type, IBlockState state) {
		switch (state.getValue(TYPE)) {
			case CARNALLITE :
				return "shovel".equals(type);
			case DEWALQUITE : 
			case FLUORAPATITE :
				return "pickaxe".equals(type);
			default :
				return true;
		}
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(TYPE).ordinal();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE).ordinal();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.blockState.getBaseState().withProperty(TYPE, Type.values()[meta]);
	}
	
	public enum Type implements IStringSerializableEnumImpl {
		CARNALLITE, DEWALQUITE, FLUORAPATITE
    }

}
