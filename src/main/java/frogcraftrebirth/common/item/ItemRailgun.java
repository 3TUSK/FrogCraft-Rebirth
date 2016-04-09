package frogcraftrebirth.common.item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.common.entity.EntityIonCannonBeam;
import frogcraftrebirth.common.entity.EntityRailgunCoin;
import frogcraftrebirth.common.lib.config.ConfigMain;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemRailgun extends ItemFrogCraft implements IElectricItem {
	
	public static DamageSource railgun = new DamageSource("FrogRailgun").
			setDamageBypassesArmor().
			setExplosion().
			setFireDamage().
			setMagicDamage().
			setProjectile();
	
	public int energyMax;
	
	public ItemRailgun(int maxEnergy) {
		super(false);
		setFull3D();
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
		setTextureName(TEXTURE_MAIN + "RailGun");
		setUnlocalizedName(ConfigMain.isAcademyCraftLoaded ? "ItemMiniIonCannon.name" : "ItemRailgun.name");
		this.energyMax = maxEnergy;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack){
		return 1D - (ElectricItem.manager.getCharge(stack) / getMaxCharge(stack));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs aTab, @SuppressWarnings("rawtypes")List subItems) {
		ItemStack stack = new ItemStack(item, 1);
		if (getEmptyItem(stack) == this) {
			subItems.add(stack);
		}
		if (getChargedItem(stack) == this) {
			ItemStack charged = new ItemStack(item, 1);
			ElectricItem.manager.charge(charged, 2147483647, getTier(charged), true, false);
			subItems.add(charged);
		}	
	}
	
	public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(StatCollector.translateToLocal(ConfigMain.isAcademyCraftLoaded ? "item.ItemMiniIonCannon.info" : "item.ItemRailgun.info"));
		if (ElectricItem.manager.getCharge(stack) <= 100000) {
			list.add(StatCollector.translateToLocal(ConfigMain.isAcademyCraftLoaded ? "item.ItemMiniIonCannon.coolingDown" : "item.ItemRailgun.tooTired"));
		}
		return list;
	}
	
	@Override
	public boolean isRepairable() {
	    return false;
	}
	
	/**
	 * Record the player data for further usage.
	 */
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		if (stack.hasTagCompound() && !stack.stackTagCompound.hasKey("RailgunID")) {
			NBTTagCompound info = new NBTTagCompound();
			info.setString("OwnerUUID", player.getUniqueID().toString());
			stack.stackTagCompound.setTag("railgunID", info);
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (world.isRemote)
			return stack;

		if (ConfigMain.isAcademyCraftLoaded)
			return this.railgunLogic(stack, world, player);
		else
			return this.ionCannonLogic(stack, world, player);
    }
	
	private ItemStack railgunLogic(ItemStack stack, World world, EntityPlayer player) {
		boolean active = stack.stackTagCompound.getBoolean("active");
		if (!world.isRemote && player.isSneaking()) {
			active = !active ? true : false;
			player.addChatComponentMessage(new net.minecraft.util.ChatComponentText("Debug: isRailgunActive="+Boolean.toString(active)));
			this.setStatus(stack, active);
			return stack;
		}
		
		if (active && ElectricItem.manager.canUse(stack, 100000)) {
			if (player.inventory.consumeInventoryItem(ic2.api.item.IC2Items.getItem("coin").getItem())) {
				world.spawnEntityInWorld(new EntityRailgunCoin(world, player));
				ElectricItem.manager.use(stack, 100000, player);
			}
			return stack;
		}
		
		return stack;
	}
	
	private ItemStack ionCannonLogic(ItemStack stack, World world, EntityPlayer player) {
		boolean active = stack.stackTagCompound.getBoolean("active");
		if (active && ElectricItem.manager.canUse(stack, 5000000)) {
			world.spawnEntityInWorld(new EntityIonCannonBeam(world, player));
		}
		return stack;
	}
	
	public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean isHolding) {
		if (world.isRemote)
			return;
	}

	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return false;
	}

	@Override
	public Item getChargedItem(ItemStack itemStack) {
		return this;
	}

	@Override
	public Item getEmptyItem(ItemStack itemStack) {
		return this;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		return energyMax;
	}

	@Override
	public int getTier(ItemStack itemStack) {
		return 3;
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		return 1000;
	}
	
	public ItemStack setStatus(ItemStack stack, boolean status) {
		stack.stackTagCompound.setBoolean("active", status);
		return stack;
	}

}
