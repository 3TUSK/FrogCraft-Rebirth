package frogcraftrewrite.common.tile;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import frogcraftrewrite.api.tile.IPersonal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
/**
 * This is The very first step of re-writing FrogCraft.
 * As its name shown, mobile power station shall has following features:
 * <br> 1.Four slots for charging items
 * <br> 2.Four slots for discharging items
 * <br> 3.Allow player to determine whether the MPS is personal or not
 * <br> 4.Top face allows player to open a crafting table.
 * <p> Created by 3TUSK on Jul. 13th 2015
 * */
public class TileMobilePowerStation extends TileEntity implements IPersonal, IInventory, IEnergySource {
	/**Allow the tileEntity to record the player's info, in case that the player doesn't want other to use it.*/
	public GameProfile profile;
	/**The inventory of MPS. It should be defined in constructor. Also, some certain slots need to be specified.*/
	public ItemStack[] inv;
	/**Determine whether the MPS is for personal usage.*/
	public boolean isPrivate;
	/**Energy amount and its maximum.*/
	public double storedEnergy, maxEnergy;
	
	public boolean isInENet;

	public TileMobilePowerStation(boolean privateMode) {
		this.inv = new ItemStack[12]; //Will get a increase upon 10 more, due to the further usage extension.
		this.isPrivate = privateMode;
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
		profile = new GameProfile(UUID.fromString(tag.getString("UUID")), tag.getString("name"));
		storedEnergy = tag.getDouble("storedEnergy");
		maxEnergy = tag.getDouble("maxEnergy");
		isPrivate = tag.getBoolean("isPrivate");
		
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
		tag.setString("UUID", profile.getId().toString());
		tag.setString("name", profile.getName());
		tag.setDouble("storedEnergy", storedEnergy);
		tag.setDouble("maxEnergy", maxEnergy);
		tag.setBoolean("isPrivate", isPrivate);
		
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
		return Math.min(storedEnergy, getSourceTier()*32);
	}

	@Override
	public void drawEnergy(double amount) {
		if (this.maxEnergy > (storedEnergy + amount))
				storedEnergy += amount ;
		else storedEnergy = maxEnergy;
	}

	@Override
	public int getSourceTier() {
		return 1; //LV is 32EU/t.
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int decrNum) {
		ItemStack stack = getStackInSlot(slot);
		if (stack == null) return stack;
		if (stack.stackSize == decrNum) {
			stack.stackSize = 0;
			return stack;
		}
		
		stack.stackSize -= decrNum;
		return stack;
	}

	@Override
    public ItemStack getStackInSlotOnClosing(int slot) {
    	ItemStack stack = getStackInSlot(slot);
    	if (stack != null) 
    		setInventorySlotContents(slot, null);
    	return stack;
    }

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit())
        	stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInventoryName() {
		return "TileEntityMPS";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	/**Determine whether a player can use it, regardless anything else.*/
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (!isPrivate)
			return true;
		else return profile.getId() == player.getGameProfile().getId();
	}

	/**Do not touch it.*/
	@Override
	public void openInventory() {}

	/**Do not touch it.*/
	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int aSlot, ItemStack aStack) {
		return aSlot < 4;
	}

	@Override
	public GameProfile getOwnerProfile() {
		return profile;
	}

	@Override
	public String getOwnerName() {
		return profile.getName();
	}

	@Override
	public boolean match(GameProfile beingChecked) {
		return this.profile == beingChecked;
	}

	@Override
	public String getWaringInfo(EntityPlayer player) {
		return "Warning: This block is under wip";
	}

	@Override
	public IPersonal setOwnerProfile(GameProfile profile) {
		this.profile = profile;
		return this;
	}

}
