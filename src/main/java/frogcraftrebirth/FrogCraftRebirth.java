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

package frogcraftrebirth;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Entry class of the mod, FrogCraft: Rebirth.
 * By setting useMetadta = true, its metadata is provided via mcmod.info
 * file under resources folder.
 * Some of the metadata is hardcoded hereby to ensure that
 * ForgeModLoader won't complain at runtime.
 */
@Mod(
		modid = FrogAPI.MODID, 
		name = FrogAPI.NAME, 
		version = "@VERSION@",
		acceptedMinecraftVersions = "[1.12, 1.13)",
		guiFactory = "frogcraftrebirth.common.lib.config.ConfigGuiFactory", 
		useMetadata = true)
public final class FrogCraftRebirth {

	/**
	 * The singleton instance of the entry class.
	 */
	private static final FrogCraftRebirth INSTANCE = new FrogCraftRebirth();

	/**
	 * The getter of singleton instance of this entry class, FrogCraftRebirth.
	 * @return The singleton instance
	 */
	@Mod.InstanceFactory
	public static FrogCraftRebirth getInstance() {
		return INSTANCE;
	}

	/**
	 * The proxy instance to which the entry class delegate all its methods.
	 * <p>
	 *     Note that the actual value is injected during the mod construction
	 *     period. The actual value is determined by the physical server/client.
	 * </p>
	 */
	@SidedProxy(serverSide = "frogcraftrebirth.common.FrogProxy", clientSide = "frogcraftrebirth.client.FrogProxyClient")
	public static FrogProxy proxy;

	private FrogCraftRebirth() {
		net.minecraftforge.fluids.FluidRegistry.enableUniversalBucket();
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void imcInit(FMLInterModComms.IMCEvent event) {
		proxy.imcInit(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

}
