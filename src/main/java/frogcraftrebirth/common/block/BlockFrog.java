/*
 * Copyright (c) 2015 - 2020 3TUSK, et al.
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

package frogcraftrebirth.common.block;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.api.FrogAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * Base block for all blocks added by FrogCraft: Rebirth.
 * Tweaked for better i18n support.
 */
public class BlockFrog extends Block {

	private String langKey;

	BlockFrog(Material material, MapColor color) {
		super(material, color);
		this.setCreativeTab(FrogAPI.TAB);
	}

	BlockFrog(Material material) {
		super(material);
		this.setCreativeTab(FrogAPI.TAB);
	}

	@Override
	public String getLocalizedName() {
		return FrogCraftRebirth.sideDelegate.translate(this.getTranslationKey());
	}

	@Override
	public String getTranslationKey() {
		return this.langKey;
	}

	@Override
	public Block setTranslationKey(String key) {
		this.langKey = "block.frogcraftrebirth." + key;
		return this;
	}
}
