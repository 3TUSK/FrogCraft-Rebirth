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

package frogcraftrebirth.common.lib.block;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.block.BlockHorizontal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;

public abstract class BlockFrog extends Block {

	/**
	 * Deprecated, due to this is not always used by any of its subclass.
	 * See {@link BlockHorizontal} and other new classes
	 * for replacement, which are fields with same name.
	 */
	@Deprecated
	protected static final PropertyBool WORKING = PropertyBool.create("working");

	/**
	 * Deprecated due to 1.13 flattening.
	 * No replacement available.
	 */
	@Deprecated
	private final int[] metaArrayForCreativeTab = null;

	/**
	 * @param material The block {@link Material}
	 * @param registryName The unique identifier for registry.
	 * @param metaForDisplay An array of integer, used for determining which blocks will show in creative tab.
	 *
	 * @implNote
	 * Parameter metaForDisplay will not function, due to 1.13 flattening.
	 */
	protected BlockFrog(Material material, String registryName, int... metaForDisplay) {
		super(material);
		setRegistryName(registryName); // TODO Remove this call, as we need to reuse class
		setCreativeTab(FrogAPI.TAB);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getBlock().getMetaFromState(state);
	}

}
