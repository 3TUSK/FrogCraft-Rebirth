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

package frogcraftrebirth.client;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogGameObjects;
import frogcraftrebirth.common.FrogFluids;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = FrogAPI.MODID, value = Side.CLIENT)
public final class FrogTextures {

	@SubscribeEvent
	public static void regModel(ModelRegistryEvent event) {
		RegHelper.registerModel(FrogGameObjects.AMMONIA_COOLANT_60K, "ammonia_coolant_60k");
		RegHelper.registerModel(FrogGameObjects.AMMONIA_COOLANT_180K, "ammonia_coolant_180k");
		RegHelper.registerModel(FrogGameObjects.AMMONIA_COOLANT_360K, "ammonia_coolant_360k");
		RegHelper.registerModel(FrogGameObjects.URANIUM_DECAY_BATTERY, "uranium_decay_battery");
		RegHelper.registerModel(FrogGameObjects.THORIUM_DECAY_BATTERY, "thorium_decay_battery");
		RegHelper.registerModel(FrogGameObjects.PLUTONIUM_DECAY_BATTERY, "plutonium_decay_battery");
		RegHelper.registerModel(FrogGameObjects.JINKELA, "jinkela");

		RegHelper.registerModel(FrogGameObjects.HEATING_MODULE, "module_heating");
		RegHelper.registerModel(FrogGameObjects.ELECTROLYSIS_MODULE, "module_electrolysis");
		RegHelper.registerModel(FrogGameObjects.AMMONIA_CATALYST_MODULE, "module_ammonia");
		RegHelper.registerModel(FrogGameObjects.SULFUR_TRIOXIDE_MODULE, "module_sulfuric_acid");

		RegHelper.registerModel(FrogGameObjects.CRUSHED_CARNALLITE_ORE, "ore/crushed/carnallite");
		RegHelper.registerModel(FrogGameObjects.PURIFIED_CARNALLITE_ORE, "ore/purified/carnallite");
		RegHelper.registerModel(FrogGameObjects.CARNALLITE_DUST, "ore/dust/carnallite");
		RegHelper.registerModel(FrogGameObjects.TINY_CARNALLITE_DUST, "ore/dust_tiny/carnallite");
		RegHelper.registerModel(FrogGameObjects.CRUSHED_DEWALQUITE_ORE, "ore/crushed/dewalquite");
		RegHelper.registerModel(FrogGameObjects.PURIFIED_DEWALQUITE_ORE, "ore/purified/dewalquite");
		RegHelper.registerModel(FrogGameObjects.DEWALQUITE_DUST, "ore/dust/dewalquite");
		RegHelper.registerModel(FrogGameObjects.TINY_DEWALQUITE_DUST, "ore/dust_tiny/dewalquite");
		RegHelper.registerModel(FrogGameObjects.CRUSHED_FLUORAPATITE_ORE, "ore/crushed/fluorapatite");
		RegHelper.registerModel(FrogGameObjects.PURIFIED_FLUORAPATITE_ORE, "ore/purified/fluorapatite");
		RegHelper.registerModel(FrogGameObjects.FLUORAPATITE_DUST, "ore/dust/fluorapatite");
		RegHelper.registerModel(FrogGameObjects.TINY_FLUORAPATITE_DUST, "ore/dust_tiny/fluorapatite");
		RegHelper.registerModel(FrogGameObjects.ALUMINIUM_INGOT, "metal/ingot/aluminium");
		RegHelper.registerModel(FrogGameObjects.ALUMINIUM_CASING, "metal/casing/aluminium");
		RegHelper.registerModel(FrogGameObjects.ALUMINIUM_PLATE, "metal/plate/aluminium");
		RegHelper.registerModel(FrogGameObjects.DENSE_ALUMINIUM_PLATE, "metal/plate_dense/aluminium");
		RegHelper.registerModel(FrogGameObjects.ALUMINIUM_DUST, "metal/dust/aluminium");
		RegHelper.registerModel(FrogGameObjects.TINY_ALUMINIUM_DUST, "metal/dust_tiny/aluminium");
		RegHelper.registerModel(FrogGameObjects.MAGNALIUM_INGOT, "metal/ingot/magnalium");
		RegHelper.registerModel(FrogGameObjects.MAGNALIUM_CASING, "metal/casing/magnalium");
		RegHelper.registerModel(FrogGameObjects.MAGNALIUM_PLATE, "metal/plate/magnalium");
		RegHelper.registerModel(FrogGameObjects.DENSE_MAGNALIUM_PLATE, "metal/plate_dense/magnalium");
		RegHelper.registerModel(FrogGameObjects.MAGNALIUM_DUST, "metal/dust/magnalium");
		RegHelper.registerModel(FrogGameObjects.TINY_MAGNALIUM_DUST, "metal/dust_tiny/magnalium");
		RegHelper.registerModel(FrogGameObjects.TITANIUM_INGOT, "metal/ingot/titanium");
		RegHelper.registerModel(FrogGameObjects.TITANIUM_CASING, "metal/casing/titanium");
		RegHelper.registerModel(FrogGameObjects.TITANIUM_PLATE, "metal/plate/titanium");
		RegHelper.registerModel(FrogGameObjects.DENSE_TITANIUM_PLATE, "metal/plate_dense/titanium");
		RegHelper.registerModel(FrogGameObjects.TITANIUM_DUST, "metal/dust/titanium");
		RegHelper.registerModel(FrogGameObjects.TINY_TITANIUM_DUST, "metal/dust_tiny/titanium");
		RegHelper.registerModel(FrogGameObjects.MAGNESIUM_INGOT, "metal/ingot/magnesium");
		RegHelper.registerModel(FrogGameObjects.MAGNESIUM_CASING, "metal/casing/magnesium");
		RegHelper.registerModel(FrogGameObjects.MAGNESIUM_PLATE, "metal/plate/magnesium");
		RegHelper.registerModel(FrogGameObjects.DENSE_MAGNESIUM_PLATE, "metal/plate_dense/magnesium");
		RegHelper.registerModel(FrogGameObjects.MAGNESIUM_DUST, "metal/dust/magnesium");
		RegHelper.registerModel(FrogGameObjects.TINY_MAGNESIUM_DUST, "metal/dust_tiny/magnesium");
		RegHelper.registerModel(FrogGameObjects.AMMONIUM_NITRATE_DUST, "non_metal/dust/ammonium_nitrate");
		RegHelper.registerModel(FrogGameObjects.CALCITE_DUST, "non_metal/dust/calcite");
		RegHelper.registerModel(FrogGameObjects.CALCIUM_SILICATE_DUST, "non_metal/dust/calcium_silicate");
		RegHelper.registerModel(FrogGameObjects.GYPSUM_DUST, "non_metal/dust/gypsum");
		RegHelper.registerModel(FrogGameObjects.QUICKLIME_DUST, "non_metal/dust/quicklime");
		RegHelper.registerModel(FrogGameObjects.SILICA_DUST, "non_metal/dust/silica");
		RegHelper.registerModel(FrogGameObjects.SLAKED_LIME_DUST, "non_metal/dust/slaked_lime");
		RegHelper.registerModel(FrogGameObjects.UREA_DUST, "non_metal/dust/urea");
		RegHelper.registerModel(FrogGameObjects.TINY_AMMONIUM_NITRATE_DUST, "non_metal/dust_tiny/ammonium_nitrate");
		RegHelper.registerModel(FrogGameObjects.TINY_CALCITE_DUST, "non_metal/dust_tiny/calcite");
		RegHelper.registerModel(FrogGameObjects.TINY_CALCIUM_SILICATE_DUST, "non_metal/dust_tiny/calcium_silicate");
		RegHelper.registerModel(FrogGameObjects.TINY_GYPSUM_DUST, "non_metal/dust_tiny/gypsum");
		RegHelper.registerModel(FrogGameObjects.TINY_QUICKLIME_DUST, "non_metal/dust_tiny/quicklime");
		RegHelper.registerModel(FrogGameObjects.TINY_SILICA_DUST, "non_metal/dust_tiny/silica");
		RegHelper.registerModel(FrogGameObjects.TINY_SLAKED_LIME_DUST, "non_metal/dust_tiny/slaked_lime");
		RegHelper.registerModel(FrogGameObjects.TINY_UREA_DUST, "non_metal/dust_tiny/urea");
		RegHelper.registerModel(FrogGameObjects.ALUMINIUM_OXIDE_DUST, "intermediate/aluminium_oxide");
		RegHelper.registerModel(FrogGameObjects.CALCIUM_FLUORIDE_DUST, "intermediate/calcium_fluoride");
		RegHelper.registerModel(FrogGameObjects.MAGNESIUM_BROMIDE_DUST, "intermediate/magnesium_bromide");
		RegHelper.registerModel(FrogGameObjects.POTASSIUM_CHLORIDE_DUST, "intermediate/potassium_chloride");
		RegHelper.registerModel(FrogGameObjects.SODIUM_CHLORIDE_DUST, "intermediate/sodium_chloride");
		RegHelper.registerModel(FrogGameObjects.TITANIUM_OXIDE_DUST, "intermediate/titanium_oxide");
		RegHelper.registerModel(FrogGameObjects.VANADIUM_OXIDE_DUST, "intermediate/vanadium_oxide");
		RegHelper.registerModel(FrogGameObjects.BRIQUETTE, "inflammable/briquette");
		RegHelper.registerModel(FrogGameObjects.LIPID, "inflammable/lipids_cluster");
		RegHelper.registerModel(FrogGameObjects.PHOSPHORUS, "inflammable/phosphorus");
		RegHelper.registerModel(FrogGameObjects.POTASSIUM, "inflammable/potassium");
		RegHelper.registerModel(FrogGameObjects.SHATTERED_COAL_COKE, "inflammable/shattered_coal_coke");
		RegHelper.registerModel(FrogGameObjects.SOAP, "soap");

		RegHelper.registerModel(FrogGameObjects.CARNALLITE, "carnallite");
		RegHelper.registerModel(FrogGameObjects.DEWALQUITE, "dewalquite");
		RegHelper.registerModel(FrogGameObjects.FLUORAPATITE, "fluorapatite");
		RegHelper.registerModel(FrogGameObjects.MPS, "mobile_power_station");
		RegHelper.registerModel(FrogGameObjects.CONDENSE_TOWER_CORE, "condensetower/core");
		RegHelper.registerModel(FrogGameObjects.CONDENSE_TOWER_CYLINDER, "condensetower/cylinder");
		RegHelper.registerModel(FrogGameObjects.CONDENSE_TOWER_OUTLET, "condensetower/outlet");
		RegHelper.registerModel(FrogGameObjects.ADV_CHEM_REACTOR, "machine/advanced_chemical_reactor");
		RegHelper.registerModel(FrogGameObjects.AIR_PUMP, "machine/air_pump");
		RegHelper.registerModel(FrogGameObjects.PYROLYZER, "machine/pyrolyzer");
		RegHelper.registerModel(FrogGameObjects.LIQUEFIER, "machine/liquefier");
		RegHelper.registerModel(FrogGameObjects.ADV_BLAST_FURNACE, "machine/advanced_blast_furnace");
		RegHelper.registerModel(FrogGameObjects.COMBUSTION_FURNACE, "combustion_furnace");
		RegHelper.registerModel(FrogGameObjects.HSU, "hybrid_storage_unit");
		RegHelper.registerModel(FrogGameObjects.UHSU, "ultra_hybrid_storage_unit");

		RegHelper.regFluidBlockTexture(FrogFluids.coalTar);
		RegHelper.regFluidBlockTexture(FrogFluids.nitricAcid);
		RegHelper.regFluidBlockTexture(FrogFluids.sulfuricAcid);
	}

	@SubscribeEvent
	public static void regFluidSpirit(TextureStitchEvent.Pre event) {
		TextureMap textureMap = event.getMap();
		textureMap.registerSprite(FrogFluids.ammonia.getFlowing());
		textureMap.registerSprite(FrogFluids.ammonia.getStill());
		textureMap.registerSprite(FrogFluids.argon.getFlowing());
		textureMap.registerSprite(FrogFluids.argon.getStill());
		textureMap.registerSprite(FrogFluids.benzene.getFlowing());
		textureMap.registerSprite(FrogFluids.benzene.getStill());
		textureMap.registerSprite(FrogFluids.bromine.getFlowing());
		textureMap.registerSprite(FrogFluids.bromine.getStill());
		textureMap.registerSprite(FrogFluids.carbonDioxide.getFlowing());
		textureMap.registerSprite(FrogFluids.carbonDioxide.getStill());
		textureMap.registerSprite(FrogFluids.carbonOxide.getFlowing());
		textureMap.registerSprite(FrogFluids.carbonOxide.getStill());
		textureMap.registerSprite(FrogFluids.chlorine.getFlowing());
		textureMap.registerSprite(FrogFluids.chlorine.getStill());
		textureMap.registerSprite(FrogFluids.fluorine.getFlowing());
		textureMap.registerSprite(FrogFluids.fluorine.getStill());
		textureMap.registerSprite(FrogFluids.glycerol.getFlowing());
		textureMap.registerSprite(FrogFluids.glycerol.getStill());
		textureMap.registerSprite(FrogFluids.liquefiedAir.getFlowing());
		textureMap.registerSprite(FrogFluids.liquefiedAir.getStill());
		textureMap.registerSprite(FrogFluids.methane.getFlowing());
		textureMap.registerSprite(FrogFluids.methane.getStill());
		textureMap.registerSprite(FrogFluids.nitrogen.getFlowing());
		textureMap.registerSprite(FrogFluids.nitrogen.getStill());
		textureMap.registerSprite(FrogFluids.nitrogenOxide.getFlowing());
		textureMap.registerSprite(FrogFluids.nitrogenOxide.getStill());
		textureMap.registerSprite(FrogFluids.oleum.getFlowing());
		textureMap.registerSprite(FrogFluids.oleum.getStill());
		textureMap.registerSprite(FrogFluids.sulfurDioxide.getFlowing());
		textureMap.registerSprite(FrogFluids.sulfurDioxide.getStill());
		textureMap.registerSprite(FrogFluids.sulfurTrioxide.getFlowing());
		textureMap.registerSprite(FrogFluids.sulfurTrioxide.getStill());
	}

}
