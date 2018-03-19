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
import org.apache.commons.lang3.StringUtils;

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
		ItemInfoTuple(final String id, final int meta) {
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

	private static void flatteningFrogCraftRebirthItems(NBTTagCompound tag) {
		String mapResult = FLATTENING_MAP.get(new ItemInfoTuple(tag.getString("id"), tag.getInteger("Damage")));
		if (!StringUtils.isEmpty(mapResult)) {
			tag.setString("id", mapResult);
			tag.setShort("Damage", (short) 0);
		}
	}

	static {
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore", 0), "frogcraftrebirth:carnallite");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore", 1), "frogcraftrebirth:dewalquite");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore", 2), "frogcraftrebirth:fluorapatite");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:condense_tower", 0), "frogcraftrebirth:condense_tower_core");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:condense_tower", 1), "frogcraftrebirth:condense_tower_cylinder");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:condense_tower", 2), "frogcraftrebirth:condense_tower_outlet");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:generator", 0), "frogcraftrebirth:combustion_furnace");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:hybrid_storage_unit", 0), "frogcraftrebirth:hsu");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:hybrid_storage_unit", 1), "frogcraftrebirth:uhsu");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:machine", 0), "frogcraftrebirth:advanced_chemical_reactor");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:machine", 1), "frogcraftrebirth:air_pump");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:machine", 2), "frogcraftrebirth:pyrolyzer");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:machine", 3), "frogcraftrebirth:liquefier");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:machine2", 0), "frogcraftrebirth:advanced_blast_furnace");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:catalyst_module", 0), "frogcraftrebirth:heating_module");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:catalyst_module", 1), "frogcraftrebirth:electrolysis_module");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:catalyst_module", 2), "frogcraftrebirth:ammonia_catalyst_module");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:catalyst_module", 3), "frogcraftrebirth:sulfur_trioxide_module");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:plutoium_decay_battery", 0), "frogcraftrebirth:plutonium_decay_battery");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_ingot", 0), "frogcraftrebirth:aluminium_ingot");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_ingot", 1), "frogcraftrebirth:magnalium_ingot");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_ingot", 2), "frogcraftrebirth:titanium_ingot");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_ingot", 3), "frogcraftrebirth:magnesium_ingot");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_dust", 0), "frogcraftrebirth:aluminium_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_dust", 1), "frogcraftrebirth:magnalium_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_dust", 2), "frogcraftrebirth:titanium_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_dust", 3), "frogcraftrebirth:magnesium_dust");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_dust_tiny", 0), "frogcraftrebirth:tiny_aluminium_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_dust_tiny", 1), "frogcraftrebirth:tiny_magnalium_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_dust_tiny", 2), "frogcraftrebirth:tiny_titanium_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_dust_tiny", 3), "frogcraftrebirth:tiny_magnesium_dust");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_plate", 0), "frogcraftrebirth:aluminium_plate");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_plate", 1), "frogcraftrebirth:magnalium_plate");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_plate", 2), "frogcraftrebirth:titanium_plate");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_plate", 3), "frogcraftrebirth:magnesium_plate");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_plate_dense", 0), "frogcraftrebirth:dense_aluminium_plate");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_plate_dense", 1), "frogcraftrebirth:dense_magnalium_plate");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_plate_dense", 2), "frogcraftrebirth:dense_titanium_plate");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_plate_dense", 3), "frogcraftrebirth:dense_magnesium_plate");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_casing", 0), "frogcraftrebirth:aluminium_casing");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_casing", 1), "frogcraftrebirth:magnalium_casing");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_casing", 2), "frogcraftrebirth:titanium_casing");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:metal_casing", 3), "frogcraftrebirth:magnesium_casing");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_crushed", 0), "frogcraftrebirth:crushed_carnallite_ore");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_crushed", 1), "frogcraftrebirth:crushed_dewalquite_ore");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_crushed", 2), "frogcraftrebirth:crushed_fluorapatite_ore");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_purified", 0), "frogcraftrebirth:purified_carnallite_ore");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_purified", 1), "frogcraftrebirth:purified_dewalquite_ore");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_purified", 2), "frogcraftrebirth:purified_fluorapatite_ore");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_dust", 0), "frogcraftrebirth:carnallite_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_dust", 1), "frogcraftrebirth:dewalquite_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_dust", 2), "frogcraftrebirth:fluorapatite_dust");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_dust_tiny", 0), "frogcraftrebirth:tiny_carnallite_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_dust_tiny", 1), "frogcraftrebirth:tiny_dewalquite_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:ore_dust_tiny", 2), "frogcraftrebirth:tiny_fluorapatite_dust");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust", 0), "frogcraftrebirth:ammonium_nitrate_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust", 1), "frogcraftrebirth:calcite_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust", 2), "frogcraftrebirth:calcium_silicate_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust", 3), "frogcraftrebirth:gypsum_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust", 4), "frogcraftrebirth:quicklime_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust", 5), "frogcraftrebirth:silica_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust", 6), "frogcraftrebirth:slaked_lime_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust", 7), "frogcraftrebirth:urea_dust");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust_tiny", 0), "frogcraftrebirth:tiny_ammonium_nitrate_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust_tiny", 1), "frogcraftrebirth:tiny_calcite_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust_tiny", 2), "frogcraftrebirth:tiny_calcium_silicate_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust_tiny", 3), "frogcraftrebirth:tiny_gypsum_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust_tiny", 4), "frogcraftrebirth:tiny_quicklime_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust_tiny", 5), "frogcraftrebirth:tiny_silica_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust_tiny", 6), "frogcraftrebirth:tiny_slaked_lime_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:non_metal_dust_tiny", 7), "frogcraftrebirth:tiny_urea_dust");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:intermediate_product", 0), "frogcraftrebirth:aluminium_oxide_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:intermediate_product", 1), "frogcraftrebirth:calcium_fluoride_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:intermediate_product", 2), "frogcraftrebirth:magnesium_bromide_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:intermediate_product", 3), "frogcraftrebirth:potassium_chloride_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:intermediate_product", 4), "frogcraftrebirth:sodium_chloride_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:intermediate_product", 5), "frogcraftrebirth:titanium_oxide_dust");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:intermediate_product", 6), "frogcraftrebirth:vanadium_oxide_dust");

		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:inflammable", 0), "frogcraftrebirth:briquette");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:inflammable", 1), "frogcraftrebirth:lipid");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:inflammable", 2), "frogcraftrebirth:phosphorus");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:inflammable", 3), "frogcraftrebirth:potassium");
		FLATTENING_MAP.put(new ItemInfoTuple("frogcraftrebirth:inflammable", 4), "frogcraftrebirth:shattered_coal_coke");

	}

}
