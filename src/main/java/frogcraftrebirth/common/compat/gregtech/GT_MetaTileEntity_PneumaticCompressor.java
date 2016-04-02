package frogcraftrebirth.common.compat.gregtech;

import frogcraftrebirth.api.FrogAPI;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_ImplosionCompressor;

public class GT_MetaTileEntity_PneumaticCompressor 
	extends GT_MetaTileEntity_ImplosionCompressor {

	public GT_MetaTileEntity_PneumaticCompressor(int aID, String aName, String aNameRegional) {
		super(aID, aName, aNameRegional);
	}

	public GT_MetaTileEntity_PneumaticCompressor(String aName) {
		super(aName);
	}

	public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_PneumaticCompressor(this.mName);
	}
	
	@Override
	public String[] getDescription() {
		return new String[] {
				"Controller Block for the Pneumatic Compressor",
				"Size: 3x3x3 (Hollow)",
				"Controller (front centered)",
				"1x Input (anywhere)", 
				"1x Output (anywhere)", 
				"1x Energy Hatch (anywhere)", 
				"1x Maintenance Hatch (anywhere)", 
				"1x Muffler Hatch (anywhere)", 
				"At most 4x Air Pump Hatch (anywhere)",
				"Unknown Casings for the rest (12 at least!)",
				"Powered by FrogCraft (TM)",
				"If you need user manual, or you are wondering any other questions",
				"Please contact FrogCraft: Rebirth Dev Team directly",
				"DO NOT REPORT ANY BUG(s) ABOUT THIS MACHINE TO GREGORIUST!"
		};
	}
	
	@Override
	public GT_Recipe.GT_Recipe_Map getRecipeMap() {
		return FrogAPI.sImplosionRecipes_No_ITNT_Version;
	}

}
