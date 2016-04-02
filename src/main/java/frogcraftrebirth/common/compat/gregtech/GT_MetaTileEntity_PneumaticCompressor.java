package frogcraftrebirth.common.compat.gregtech;

import java.util.ArrayList;

import frogcraftrebirth.api.FrogAPI;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_Recipe;
import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_ImplosionCompressor;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_MetaTileEntity_PneumaticCompressor 
	extends GT_MetaTileEntity_ImplosionCompressor {
	
	public ArrayList<GT_MetaTileEntity_AirPumpHatch> mAirPumpHatches = new ArrayList<GT_MetaTileEntity_AirPumpHatch>();

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
				"1x Muffler Hatch (top centered)", 
				"At most 4x Air Pump Hatch (side or bottom centerd)",
				"Stable Titanium Casings for the rest (12 at least!)",
				"Powered by FrogCraft (TM)",
				"If you need user manual, or you are wondering any other questions",
				"Please contact FrogCraft: Rebirth Dev Team directly",
				"DO NOT REPORT ANY BUG(s) ABOUT THIS MACHINE TO GREGORIUST!"
		};
	}
	
	public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) {
			return new ITexture[]{Textures.BlockIcons.CASING_BLOCKS[50], new GT_RenderedTexture(aActive ? Textures.BlockIcons.OVERLAY_FRONT_IMPLOSION_COMPRESSOR_ACTIVE : Textures.BlockIcons.OVERLAY_FRONT_IMPLOSION_COMPRESSOR)};
		}
		return new ITexture[]{Textures.BlockIcons.CASING_BLOCKS[50]};
    }
	
	@Override
	public GT_Recipe.GT_Recipe_Map getRecipeMap() {
		return FrogAPI.sImplosionRecipes_No_ITNT_Version;
	}
	
	@Override
	public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
		mAirPumpHatches.clear();
		
		int xAxisDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetX;
		int zAxisDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetZ;
		if (!aBaseMetaTileEntity.getAirOffset(xAxisDir, 0, zAxisDir))
			return false;
		
		if (!this.addMufflerToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xAxisDir, 1, zAxisDir), 50))
			return false;
		
		int nonAirPumps = 4;
		if (!this.addAirPumpHatchToMachineList(aBaseMetaTileEntity.getIGregTechTileEntity(xAxisDir, -1, zAxisDir), 50))
			--nonAirPumps;
		if (!this.addAirPumpHatchToMachineList(aBaseMetaTileEntity.getIGregTechTileEntity(xAxisDir - 1, 0, zAxisDir - 1), 50))
			--nonAirPumps;
		if (!this.addAirPumpHatchToMachineList(aBaseMetaTileEntity.getIGregTechTileEntity(xAxisDir + 1, 0, zAxisDir - 1), 50))
			--nonAirPumps;
		if (!this.addAirPumpHatchToMachineList(aBaseMetaTileEntity.getIGregTechTileEntity(xAxisDir - 1, 0, zAxisDir + 1), 50))
			--nonAirPumps;
		if (!this.addAirPumpHatchToMachineList(aBaseMetaTileEntity.getIGregTechTileEntity(xAxisDir + 1, 0, zAxisDir + 1), 50))
			--nonAirPumps;
		if (nonAirPumps < 0) nonAirPumps = 0;
		
		int casings = 0;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					IGregTechTileEntity aTile = aBaseMetaTileEntity.getIGregTechTileEntity(xAxisDir + x, y, zAxisDir + z);
					if ((!addMaintenanceToMachineList(aTile, 50)) && (!addInputToMachineList(aTile, 50)) && (!addOutputToMachineList(aTile, 50)) && (!addEnergyInputToMachineList(aTile, 50))) {
						Block aBlock = aBaseMetaTileEntity.getBlockOffset(xAxisDir + x, y, zAxisDir + z);
						byte aMetadata = aBaseMetaTileEntity.getMetaID(xAxisDir + x, y, zAxisDir + z);
						if (((aBlock != GregTech_API.sBlockCasings4) || (aMetadata != 2))) {
							return false;
						}
                        casings++;
					}
				}
			}
		}

		return casings >= 12 + nonAirPumps;
	}
	
	public boolean addAirPumpHatchToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
		if (aTileEntity == null) 
			return false;
		
		IMetaTileEntity aMetaTile = aTileEntity.getMetaTileEntity();
		if (aMetaTile == null)
			return false;
		
		if (aMetaTile instanceof GT_MetaTileEntity_AirPumpHatch) {
			((GT_MetaTileEntity_Hatch)aMetaTile).mMachineBlock = (byte)aBaseCasingIndex;
			return mAirPumpHatches.add((GT_MetaTileEntity_AirPumpHatch)aMetaTile);
		}
		
		return false;
	}

}
