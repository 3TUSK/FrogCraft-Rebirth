package frogcraftrebirth.api;

/**
 * Collection of constants used by the internal of FrogCraft: Rebirth.
 */
public final class FrogConstants {
	private FrogConstants() {
		throw new UnsupportedOperationException("No instance for you");
	}

	public static final String[] ORE_TYPES = {
			"carnallite",
			"dewalquite",
			"fluorapaite"
	};

	public static final String[] METALLIC_MATERIAL_TYPES = {
			"aluminium",
			"magnalium",
			"titanium"
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
			"shattered_coal_coke"
	};
}
