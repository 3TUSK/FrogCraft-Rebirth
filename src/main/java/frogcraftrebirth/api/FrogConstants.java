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

package frogcraftrebirth.api;

/**
 * @deprecated Merge into {@link FrogGameObjects}.
 *
 * <p>
 * Collection of constants used by the internal of FrogCraft: Rebirth.
 * </p>
 */
@Deprecated
public final class FrogConstants {
	private FrogConstants() {
		throw new UnsupportedOperationException("No instance for you");
	}

	public static final String[] ORE_TYPES = {
			"carnallite",
			"dewalquite",
			"fluorapatite"
	};

	public static final String[] METALLIC_MATERIAL_TYPES = {
			"aluminium",
			"magnalium",
			"titanium",
			"magnesium"
	};

	public static final String[] NON_METAL_MATERIAL_TYPES = {
			"ammonium_nitrate",
			"calcite",
			"calcium_silicate",
			"gypsum",
			"quicklime",
			"silica",
			"slaked_lime",
			"urea"
	};

	public static final String[] INTERMEDIATE_TYPES = {
			"aluminium_oxide",
			"calcium_fluoride",
			"magnesium_bromide",
			"potassium_chloride",
			"sodium_chloride",
			"titanium_oxide",
			"vanadium_oxide"
	};

	public static final String[] INFLAMMABLE = {
			"briquette",
			"lipids_cluster",
			"phosphorus",
			"potassium",
			"shattered_coal_coke"
	};
}
