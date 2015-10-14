package frogcraftrewrite.common.tile;

//import frogcraftrewrite.api.FrogAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileAdvChemReactor extends TileFrogMachine {

	public int process, processMax;
	
	public TileAdvChemReactor() {
		super(2, 100000);
		//0 for module, 1-5 for input, 6-10 for output, 11 for cell input and 12 for cell output
		this.inv = new ItemStack[13];
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		//TODO: Real process.
		if (!worldObj.isRemote) {
			//1.check inv
			//AdvChemReactorRecipe recipe = FrogAPI.managerACR.getRecipe(input)
			//2.check charge
			//3.if available, consume material and start react
			//4.process++ until finish
			//5.clean up
		}
	}
	

	@Override
	public short getFacing() {
		return 2;
	}

	@Override
	public void setFacing(short facing) {
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(this.blockType, 1, this.blockMetadata);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int decrNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		// TODO Auto-generated method stub
		return false;
	}

}
