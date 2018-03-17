/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
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

package frogcraftrebirth.common.migration;

import frogcraftrebirth.api.FrogAPI;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

@ParametersAreNonnullByDefault
public final class LegacyFrogCraftRebirthItemRemapper implements IFixableData {

	private static Map<ItemInfoTuple, String> FLATTENING_MAP = new HashMap<>();

	static class ItemInfoTuple {
		final String id;
		final int meta;
		public ItemInfoTuple(final String id, final int meta) {
			this.id = id;
			this.meta = meta;
		}
		@Override
		public boolean equals(Object o) {
			return o == this || o instanceof ItemInfoTuple && ((ItemInfoTuple) o).id.equals(this.id) && ((ItemInfoTuple) o).meta == this.meta;
		}

		@Override
		public int hashCode() {
			return 17 * this.id.hashCode() + meta;
		}
	}

	@Override
	public int getFixVersion() {
		return FrogAPI.DATA_FIXER_REMARK;
	}

	@Nonnull
	@Override
	public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
		flatteningFrogCraftRebirthItems(compound);
		return compound;
	}

	private void flatteningFrogCraftRebirthItems(NBTTagCompound tag) {
		switch (tag.getString("id")) {
			case "frogcraftrebirth:catalyst_module": {
				tag.setString("id", FLATTENING_MAP.getOrDefault(new ItemInfoTuple("frogcraftrebirth:catalyst_module", tag.getInteger("Damage")), "frogcraftrebirth:heating_module"));
				tag.setInteger("Damage", 0);
			}
		}
	}

	static {
		// TODO Add all mapping here
	}

}
