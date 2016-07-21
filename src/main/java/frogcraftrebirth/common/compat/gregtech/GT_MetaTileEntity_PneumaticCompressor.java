package frogcraftrebirth.common.compat.gregtech;

import java.util.ArrayList;

import frogcraftrebirth.api.FrogAPI;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.gui.GT_GUIContainer_MultiMachine;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_Recipe;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_MetaTileEntity_PneumaticCompressor 
	extends GT_MetaTileEntity_MultiBlockBase {
	
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
				I18n.format("tooltip.pncompressor.1"), //"Controller Block for the Pneumatic Compressor",
				I18n.format("tooltip.pncompressor.2"), //"Size: 3x3x3 (Hollow)",
				I18n.format("tooltip.pncompressor.3"), //"Controller (front centered)",
				I18n.format("tooltip.pncompressor.4"), //"1x Input (anywhere)", 
				I18n.format("tooltip.pncompressor.5"), //"1x Output (anywhere)", 
				I18n.format("tooltip.pncompressor.6"), //"1x Energy Hatch (anywhere)", 
				I18n.format("tooltip.pncompressor.7"), //"1x Maintenance Hatch (anywhere)", 
				I18n.format("tooltip.pncompressor.8"), //"1x Muffler Hatch (top centered)", 
				I18n.format("tooltip.pncompressor.9"), //"At most 4x Air Pump Hatch (side or bottom centerd)",
				I18n.format("tooltip.pncompressor.10"),//"Stable Titanium Casings for the rest (12 at least!)",
				I18n.format("tooltip.pncompressor.11"),//"Powered by FrogCraft (TM)",
				I18n.format("tooltip.pncompressor.12"),//"If you need user manual, or you are wondering any other questions",
				I18n.format("tooltip.pncompressor.13"),//"Please contact FrogCraft: Rebirth Dev Team directly",
				I18n.format("tooltip.pncompressor.14") //"DO NOT REPORT ANY BUG(s) ABOUT THIS MACHINE TO GREGORIUST!"
		};
	}
	
	public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) {
			return new ITexture[]{Textures.BlockIcons.CASING_BLOCKS[50], new GT_RenderedTexture(aActive ? Textures.BlockIcons.OVERLAY_FRONT_IMPLOSION_COMPRESSOR_ACTIVE : Textures.BlockIcons.OVERLAY_FRONT_IMPLOSION_COMPRESSOR)};
		}
		return new ITexture[]{Textures.BlockIcons.CASING_BLOCKS[50]};
    }
	
	//After all it is an alternative to implosion compressor.
	public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
		return new GT_GUIContainer_MultiMachine(aPlayerInventory, aBaseMetaTileEntity, getLocalName(), "ImplosionCompressor.png");
	}
	
	@Override
	public GT_Recipe.GT_Recipe_Map getRecipeMap() {
		return FrogAPI.sPneumaticImplosionRecipes;
	}
	
	@Override
	public boolean isCorrectMachinePart(ItemStack aStack) {
		return true;
	}
	
	@Override
	public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
		mAirPumpHatches.clear();
		
		final int xAxisDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetX;
		final int zAxisDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetZ;
		if (!aBaseMetaTileEntity.getAirOffset(xAxisDir, 0, zAxisDir))
			return false;
		
		if (!this.addMufflerToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xAxisDir, 1, zAxisDir), 50))
			return false;
		
		int nonAirPumps = 4;
		if (this.addAirPumpHatchToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xAxisDir, -1, zAxisDir), 50))
			--nonAirPumps;
		if (this.addAirPumpHatchToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xAxisDir - 1, 0, zAxisDir - 1), 50))
			--nonAirPumps;
		if (this.addAirPumpHatchToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xAxisDir + 1, 0, zAxisDir - 1), 50))
			--nonAirPumps;
		if (this.addAirPumpHatchToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xAxisDir - 1, 0, zAxisDir + 1), 50))
			--nonAirPumps;
		if (this.addAirPumpHatchToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xAxisDir + 1, 0, zAxisDir + 1), 50))
			--nonAirPumps;
		if (nonAirPumps < 0) nonAirPumps = 0;
		
		int casings = 0;
		for (int x = -1; x < 2; x++) {
			for (int z = -1; z < 2; z++) {
				for (int y = -1; y < 2; y++) {
					if (((y != 0) || (((xAxisDir + x != 0) || (zAxisDir + z != 0))) && (((x != 0) || (z != 0))))) {
						IGregTechTileEntity aGTTile = aBaseMetaTileEntity.getIGregTechTileEntityOffset(xAxisDir + x, y, zAxisDir + z);
						if ((!addMaintenanceToMachineList(aGTTile, 50)) && (!addInputToMachineList(aGTTile, 50)) && (!addOutputToMachineList(aGTTile, 50)) && (!addEnergyInputToMachineList(aGTTile, 50))) {
							Block aBlock = aBaseMetaTileEntity.getBlockOffset(xAxisDir + x, y, zAxisDir + z);
							byte aMetadata = aBaseMetaTileEntity.getMetaIDOffset(xAxisDir + x, y, zAxisDir + z);
							if (((aBlock != GregTech_API.sBlockCasings4) || (aMetadata != 2))) {
								return false;
							}
							casings++;
						}
					}
				}
			}
		}

		return casings >= 12 + nonAirPumps;
	}
	
	@Override
	public boolean checkRecipe(ItemStack aStack) {
		ArrayList<ItemStack> allInputs = this.getStoredInputs();
		// TODO: Auto-merge stack
		
		ItemStack anInput = allInputs.get(0);
		if (anInput != null) {
			GT_Recipe aRecipe = FrogAPI.sPneumaticImplosionRecipes.findRecipe(getBaseMetaTileEntity(), false, 9223372036854775807L, null, anInput);
			if ((aRecipe != null) && (aRecipe.isRecipeInputEqual(true, null, anInput))) {
				this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
				this.mEfficiencyIncrease = 2500 * this.mAirPumpHatches.size();

				this.mEUt = (-aRecipe.mEUt);
				this.mMaxProgresstime = Math.max(1, aRecipe.mDuration);
				this.mOutputItems = new ItemStack[] {aRecipe.getOutput(0), aRecipe.getOutput(1)};
				sendLoopStart((byte) 20);
				updateSlots();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean explodesOnComponentBreak(ItemStack aStack) {
		return false;
	}

	@Override
	public int getAmountOfOutputs() {
		return 1;
	}

	@Override
	public int getDamageToComponent(ItemStack aStack) {
		return 0;
	}

	@Override
	public int getMaxEfficiency(ItemStack aStack) {
		return 10000;
	}

	@Override
	public int getPollutionPerTick(ItemStack aStack) {
		return 1000;
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
