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
import frogcraftrebirth.common.lib.FrogFluid;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;
import java.util.Objects;

final class RegHelper {

	static void registerModel(Block block) {
		registerModel(Item.getItemFromBlock(block), Objects.requireNonNull(block.getRegistryName()).getPath());
	}

	static void registerModel(Item item, String newResLoc) {
		registerModel0(Objects.requireNonNull(item), new ResourceLocation(FrogAPI.MODID, newResLoc));
	}
	
	private static void registerModel0(Item item, ResourceLocation path) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(path, "inventory"));
	}
	
	static void regFluidBlockTexture(Fluid fluid) {
		if (fluid instanceof FrogFluid) {
			if (fluid.getBlock() != null) {
				ModelResourceLocation aResource = new ModelResourceLocation("frogcraftrebirth:fluid", fluid.getName());
				ModelLoader.setCustomStateMapper(fluid.getBlock(), new StateMapperBase() {
					@Nonnull
					@Override
					protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
						return aResource;
					}
				});
			} else {
				FrogAPI.FROG_LOG.debug("Not registering models for block of fluid '{}' because it has no block", fluid.toString());
			}
		} else {
			FrogAPI.FROG_LOG.debug("Not registering models for block of fluid '{}' because it's not controlled by FrogCraft: Rebirth", fluid.toString(), fluid.getBlock());
		}
	}

}
