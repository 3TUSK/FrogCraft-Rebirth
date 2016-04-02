package frogcraftrebirth.common.tile;

import com.mojang.authlib.GameProfile;

import frogcraftrebirth.api.tile.IMobilePowerStation;
import frogcraftrebirth.api.tile.IPersonal;
import frogcraftrebirth.common.lib.tile.TileFrogInventory;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileMobilePowerStation extends TileFrogInventory implements IEnergySource, IMobilePowerStation {
	
	/**The inventory of MPS. It should be defined in constructor. Also, some certain slots need to be specified.*/
	public ItemStack[] inv;
	/**Energy amount and its maximum.*/
	public double charge, maxCharge;
	
	public boolean isInENet;

	public TileMobilePowerStation() {
		super(12, "TileEntityMPS");
		//Will get a increase upon 10 more, due to the further usage extension.
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		if (!worldObj.isRemote && isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote && !isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}
		//TODO
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		charge = tag.getDouble("charge");
		maxCharge = tag.getDouble("maxCharge");
		
		NBTTagList invList = tag.getTagList("inventory", 10);
		for (int n = 0; n < invList.tagCount(); n++) {
			NBTTagCompound aItem = invList.getCompoundTagAt(n);
			byte slot = aItem.getByte("slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(aItem);
			}
		}
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setDouble("charge", charge);
		tag.setDouble("maxCharge", maxCharge);
		
		NBTTagList invList = new NBTTagList();
		for (int n = 0; n < inv.length; n++) {
			ItemStack stack = inv[n];
			if (stack != null) {
				NBTTagCompound tagStack = new NBTTagCompound();
				tagStack.setByte("slot", (byte) n);
				stack.writeToNBT(tagStack);
				invList.appendTag(tagStack);
			}
		}
		tag.setTag("inventory", invList);
	}

	/**Determine whether this tile emit energy to a certain direction.*/
	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return direction != ForgeDirection.UP;
	}

	/**Called when it is going to emit energy to somewhere.*/
	@Override
	public double getOfferedEnergy() {
		return Math.min(charge, getSourceTier()*32);
	}

	@Override
	public void drawEnergy(double amount) {
		if (this.maxCharge > (charge + amount))
				charge += amount ;
		else charge = maxCharge;
	}

	@Override
	public int getSourceTier() {
		return 1; //LV is 32EU/t.
	}

	@Override
	public GameProfile getOwnerProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOwnerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPersonal setOwnerProfile(GameProfile profile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean match(GameProfile beingChecked) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getWaringInfo(EntityPlayer player) {
		// TODO Auto-generated method stub
		return null;
	}

}
