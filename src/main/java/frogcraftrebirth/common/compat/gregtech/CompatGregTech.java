package frogcraftrebirth.common.compat.gregtech;

import frogcraftrebirth.api.ICompatModuleFrog;
import frogcraftrebirth.common.FrogItems;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;

public class CompatGregTech implements ICompatModuleFrog {

	@Override
	public void init() {
		FrogItems.pneumaticCompressor = new GT_MetaTileEntity_PneumaticCompressor(11504, "multimachine.pneumaticcompressor", "Pneumatic Compressor").getStackForm(1L);

		GT_ModHandler.addCraftingRecipe(
		FrogItems.pneumaticCompressor, 
		GT_ModHandler.RecipeBits.DISMANTLEABLE | 
		GT_ModHandler.RecipeBits.NOT_REMOVABLE | 
		GT_ModHandler.RecipeBits.REVERSIBLE | 
		GT_ModHandler.RecipeBits.BUFFERED, 
		new Object[] {"OOO", "PMP", "WCW", 
				Character.valueOf('M'), ItemList.Casing_StableTitanium, 
				Character.valueOf('O'), OrePrefixes.stone.get(Materials.GraniteBlack), 
				Character.valueOf('P'), ItemList.Electric_Pump_MV,
				Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Advanced), 
				Character.valueOf('W'), OrePrefixes.cableGt01.get(Materials.Electrum)});
	}

}
