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

package frogcraftrebirth.client;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogGameObjects;
import frogcraftrebirth.common.FrogFluids;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = FrogAPI.MODID, value = Side.CLIENT)
public final class FrogTextures {

	private static void mapModel(Block block) {
		mapModel(Objects.requireNonNull(Item.getItemFromBlock(block), "You don't need map model for block without its item form."));
	}

	private static void mapModel(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
	}

	@SubscribeEvent
	public static void regModel(ModelRegistryEvent event) {
		mapModel(FrogGameObjects.AMMONIA_COOLANT_60K);
		mapModel(FrogGameObjects.AMMONIA_COOLANT_180K);
		mapModel(FrogGameObjects.AMMONIA_COOLANT_360K);
		mapModel(FrogGameObjects.URANIUM_DECAY_BATTERY);
		mapModel(FrogGameObjects.THORIUM_DECAY_BATTERY);
		mapModel(FrogGameObjects.PLUTONIUM_DECAY_BATTERY);
		mapModel(FrogGameObjects.JINKELA);
		mapModel(FrogGameObjects.HEATING_MODULE);
		mapModel(FrogGameObjects.ELECTROLYSIS_MODULE);
		mapModel(FrogGameObjects.AMMONIA_CATALYST_MODULE);
		mapModel(FrogGameObjects.SULFUR_TRIOXIDE_MODULE);

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

		mapModel(FrogGameObjects.CARNALLITE);
		mapModel(FrogGameObjects.DEWALQUITE);
		mapModel(FrogGameObjects.FLUORAPATITE);
		mapModel(FrogGameObjects.MPS);
		mapModel(FrogGameObjects.CONDENSE_TOWER_CORE);
		mapModel(FrogGameObjects.CONDENSE_TOWER_CYLINDER);
		mapModel(FrogGameObjects.CONDENSE_TOWER_OUTLET);
		mapModel(FrogGameObjects.ADV_CHEM_REACTOR);
		mapModel(FrogGameObjects.AIR_PUMP);
		mapModel(FrogGameObjects.PYROLYZER);
		mapModel(FrogGameObjects.LIQUEFIER);
		mapModel(FrogGameObjects.ADV_BLAST_FURNACE);
		mapModel(FrogGameObjects.COMBUSTION_FURNACE);
		mapModel(FrogGameObjects.HSU);
		mapModel(FrogGameObjects.UHSU);

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
