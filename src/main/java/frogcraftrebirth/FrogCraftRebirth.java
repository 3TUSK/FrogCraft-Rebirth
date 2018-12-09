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

package frogcraftrebirth;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogConfig;
import frogcraftrebirth.common.FrogGuiHandler;
import frogcraftrebirth.common.FrogIMCHandler;
import frogcraftrebirth.common.FrogRecipes;
import frogcraftrebirth.common.lib.AdvBlastFurnaceRecipeManager;
import frogcraftrebirth.common.lib.AdvChemRecRecipeManager;
import frogcraftrebirth.common.lib.CondenseTowerRecipeManager;
import frogcraftrebirth.common.lib.PyrolyzerRecipeManger;
import frogcraftrebirth.common.migration.LegacyFrogCraftRebirthBlockRemapper;
import frogcraftrebirth.common.migration.LegacyFrogCraftRebirthItemRemapper;
import frogcraftrebirth.common.migration.LegacyFrogCraftRebirthTileEntityRemapper;
import frogcraftrebirth.common.network.NetworkHandler;
import frogcraftrebirth.common.world.FrogWorldGenerator;
import net.minecraft.util.datafix.FixTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ModFixs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

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

	private FrogCraftRebirth() {
		FluidRegistry.enableUniversalBucket();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(FrogCraftRebirth.getInstance(), new FrogGuiHandler());
		if (FrogConfig.modpackOptions.enableOreDictEntries) {
			FrogRecipes.initOreDict();
		}
		FrogAPI.managerABF = new AdvBlastFurnaceRecipeManager();
		FrogAPI.managerACR = new AdvChemRecRecipeManager();
		FrogAPI.managerCT = new CondenseTowerRecipeManager();
		FrogAPI.managerPyrolyzer = new PyrolyzerRecipeManger();
		if (FrogConfig.modpackOptions.enableRecipes) {
			FrogRecipes.init();
		}
		if (FrogConfig.enableWorldGen) {
			MinecraftForge.ORE_GEN_BUS.register(new FrogWorldGenerator());
		}
		ModFixs fixer = FMLCommonHandler.instance().getDataFixer().init(FrogAPI.MODID, FrogAPI.DATA_FIXER_REMARK);
		fixer.registerFix(FixTypes.ITEM_INSTANCE, new LegacyFrogCraftRebirthItemRemapper());
		fixer.registerFix(FixTypes.CHUNK, new LegacyFrogCraftRebirthBlockRemapper());
		fixer.registerFix(FixTypes.BLOCK_ENTITY, new LegacyFrogCraftRebirthTileEntityRemapper());
		FrogAPI.FROG_LOG.info("FrogCraft: Rebirth has completed initialization phase");
	}

	@Mod.EventHandler
	public void imcInit(FMLInterModComms.IMCEvent event) {
		FrogIMCHandler.resolveIMCMessage(event.getMessages());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (FrogConfig.modpackOptions.enableRecipes) {
			FrogRecipes.postInit();
		}
		FrogAPI.FROG_LOG.info("FrogCraft: Rebirth has completed post-initialization phase");
		FrogAPI.FROG_LOG.info("FrogCraft: Rebirth has finished loading. The era from chemistry will begin!");
	}

}
