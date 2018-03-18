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

package frogcraftrebirth.common;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.lib.*;
import frogcraftrebirth.common.network.NetworkHandler;
import frogcraftrebirth.common.world.FrogWorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class FrogProxy {

	public void preInit(FMLPreInitializationEvent event) {
		NetworkHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(FrogCraftRebirth.getInstance(), new FrogGuiHandler());
	}

	public void init(FMLInitializationEvent event) {
		FrogAPI.managerABF = new AdvBlastFurnaceRecipeManager();
		FrogAPI.managerACR = new AdvChemRecRecipeManager();
		FrogAPI.managerCT = new CondenseTowerRecipeManager();
		FrogAPI.managerPyrolyzer = new PyrolyzerRecipeManger();
		FrogRecipes.init();
		if (FrogConfig.enableWorldGen) {
			MinecraftForge.ORE_GEN_BUS.register(new FrogWorldGenerator());
		}
		FMLCommonHandler.instance().getDataFixer().init(FrogAPI.MODID, FrogAPI.DATA_FIXER_REMARK);
	}
	
	public final void imcInit(FMLInterModComms.IMCEvent event) {
		FrogIMCHandler.resolveIMCMessage(event.getMessages());
	}

	public void postInit(FMLPostInitializationEvent event) {
		FrogRecipes.postInit();
		MinecraftForge.EVENT_BUS.register(new FrogEventListener());
	}

}
