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

package frogcraftrebirth.common.lib.util;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.item.ItemFrog;
import frogcraftrebirth.common.item.ItemResource;
import net.minecraft.item.Item;

public final class ItemFactory {

	/**
	 * Create a ready-to-go {@link Item} instance.
	 * @param uid The unique identifier of Item. Must be all lowercase.
	 * @return the Item instance with given registry name
	 */
	public final Item create(String uid) {
		return new ItemFrog().setUnlocalizedName(uid).setRegistryName(FrogAPI.MODID, uid);
	}

	/**
	 * Create a ready-to-go {@link Item} instance with custom unlocalized name
	 * @param uid The unique identifier of Item. Must be all lowercase.
	 * @param langKey The localization key of name of the Item
	 * @return the Item instance
	 */
	public final Item create(String uid, String langKey) {
		return new ItemFrog().setUnlocalizedName(langKey).setRegistryName(FrogAPI.MODID, uid);
	}

	public final Item createWithTooltip(String uid) {
		return new ItemResource().setUnlocalizedName(uid).setRegistryName(uid).setRegistryName(FrogAPI.MODID, uid);
	}

}
