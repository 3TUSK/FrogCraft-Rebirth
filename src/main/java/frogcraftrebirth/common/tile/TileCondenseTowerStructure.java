/*
 * Copyright (c) 2015 - 2019 3TUSK, et al.
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

package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.api.tile.ICondenseTowerPart;
import frogcraftrebirth.common.lib.tile.TileFrog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileCondenseTowerStructure extends TileFrog implements ICondenseTowerPart {

	private ICondenseTowerCore mainBlock;

	@Override
	public ICondenseTowerCore getMainBlock() {
		return mainBlock;
	}
	
	@Override
	public void setMainBlock(@Nullable ICondenseTowerCore core) {
		this.mainBlock = core;
	}
	
	@Override
	public boolean isFunctional() {
		return false;
	}

	@Override
	public void writePacketData(DataOutputStream output) {}

	@Override
	public void readPacketData(DataInputStream input) {}


	@Override
	public void onBlockDestroyed(World worldIn, BlockPos pos, IBlockState state) {
		if (this.mainBlock != null) {
			mainBlock.onPartRemoved(this);
		}
	}
}
