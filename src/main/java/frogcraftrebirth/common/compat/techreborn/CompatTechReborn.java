/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 8:44:28 AM, Aug 5, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.techreborn;

import java.util.Arrays;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.ICompatModuleFrog;
import frogcraftrebirth.api.OreStack;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.FrogItems;
import frogcraftrebirth.common.lib.AdvChemRecRecipe;
import ic2.api.item.IC2Items;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CompatTechReborn implements ICompatModuleFrog {

	public static void preInit() {
		GameRegistry.<Block>register(new BlockPneumaticCompressor());
		
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			
		}
	}
	
	@Override
	public void init() {
		ItemStack cellCarbonMonoxide = IC2Items.getItem("fluid_cell");
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("fluid", new FluidStack(FrogFluids.carbonOxide, 1000).writeToNBT(new NBTTagCompound()));
		cellCarbonMonoxide.setTagCompound(tag);
		cellCarbonMonoxide.stackSize = 5;
		FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new OreStack("dustPhosphorous", 1), new OreStack("dustFlint", 3),new OreStack("dustCoal", 5)), Arrays.asList(new ItemStack(FrogItems.itemIngot, 2, 1), new ItemStack(FrogItems.itemDust, 3, 5), cellCarbonMonoxide), new ItemStack(FrogItems.itemReactionModule, 1, 0), 200, 50, 5, 0));
	}

}
