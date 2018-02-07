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

package frogcraftrebirth.common.world;

import frogcraftrebirth.api.FrogGameObjects;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class FrogWorldGenerator {

	// TODO Fix these guys
	private static final WorldGenerator CARNALLITE_GEN = new WorldGenClayFake(FrogGameObjects.CARNALLITE.getDefaultState(), 3);
	private static final WorldGenerator DEWALQUITE_GEN = new WorldGenMinable(FrogGameObjects.DEWALQUITE.getDefaultState(), 15);
	private static final WorldGenerator FLUORAPATITE_GEN = new WorldGenMinable(FrogGameObjects.FLUORAPATITE.getDefaultState(), 15);

	@SubscribeEvent
	public void onOreGen(OreGenEvent.Post event) {
		if (TerrainGen.generateOre(event.getWorld(), event.getRand(), CARNALLITE_GEN, event.getPos(), OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
			CARNALLITE_GEN.generate(event.getWorld(), event.getRand(), event.getWorld().getTopSolidOrLiquidBlock(event.getPos().add(8, 0, 8)));
		}
		if (TerrainGen.generateOre(event.getWorld(), event.getRand(), DEWALQUITE_GEN, event.getPos(), OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
			for (int i = 0; i < 20; i++) {
				DEWALQUITE_GEN.generate(event.getWorld(), event.getRand(), event.getPos().add(event.getRand().nextInt(16), event.getRand().nextInt(127), event.getRand().nextInt(16)));
			}
		}
		if (TerrainGen.generateOre(event.getWorld(), event.getRand(), FLUORAPATITE_GEN, event.getPos(), OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
			for (int i = 0; i < 20; i++) {
				FLUORAPATITE_GEN.generate(event.getWorld(), event.getRand(), event.getPos().add(event.getRand().nextInt(16), event.getRand().nextInt(127), event.getRand().nextInt(16)));
			}
		}
	}
	
}
