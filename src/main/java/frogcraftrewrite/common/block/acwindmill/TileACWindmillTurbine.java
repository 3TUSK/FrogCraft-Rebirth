package frogcraftrewrite.common.block.acwindmill;

import frogcraftrewrite.common.lib.FrogBlocks;
import frogcraftrewrite.common.tile.TileEntityFrog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
//import net.minecraftforge.common.util.ForgeDirection;

public class TileACWindmillTurbine extends TileEntityFrog {

	//public ForgeDirection dir;
	public boolean canGenEnergy, hasRotor, isBlocked, isHighEnough, isRaining;
	
	public TileACWindmillTurbine() {
		this.hasRotor = false;
	}
	
	int count = 0;
	@Override
	public void updateEntity() {
		super.updateEntity();
		//TODO Finish power generation
		this.isRaining = this.worldObj.isRaining();
		
		this.isHighEnough = this.yCoord > 78;

		for (int a=-5;a<6;a++) 
			for (int b=-5;b<6;b++) 
				for (int c=-5;c<6;c++) 
					if (!worldObj.isAirBlock(xCoord+a, yCoord+b, zCoord+c))
						++count;
		this.isBlocked = count < 13;
		count = 0;
		
		this.canGenEnergy = hasRotor && !isBlocked && !isRaining;
		markDirty();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		//this.dir = ForgeDirection.getOrientation(tag.getInteger("direction"));
		this.hasRotor = tag.getBoolean("rotor");
		this.isBlocked = tag.getBoolean("isBlocked");
		this.isHighEnough = tag.getBoolean("enoughHeight");
		this.isRaining = tag.getBoolean("raining");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		//tag.setInteger("direction", dir.ordinal());
		tag.setBoolean("rotor", hasRotor);
		tag.setBoolean("isBlocked", isBlocked);
		tag.setBoolean("enoughHeight", isHighEnough);
		tag.setBoolean("raining", isRaining);
	}
	
	@Override
	public short getFacing() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFacing(short facing) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(FrogBlocks.acwindmill, 1, 1);
	}
	
}