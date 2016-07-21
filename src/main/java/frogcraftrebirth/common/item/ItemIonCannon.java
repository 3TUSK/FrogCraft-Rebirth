package frogcraftrebirth.common.item;

import java.util.ArrayList;
import java.util.List;

import frogcraftrebirth.common.entity.EntityIonCannonBeam;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIonCannon extends ItemFrogCraft implements IElectricItem {
	
	public static DamageSource railgun = new DamageSource("IonCannon")
			.setDamageBypassesArmor()
			.setExplosion()
			.setFireDamage()
			.setProjectile();
	
	public final int energyMax;
	
	public ItemIonCannon(int maxEnergy) {
		super(false);
		setFull3D();
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
		setUnlocalizedName("ItemMiniIonCannon.name");
		this.energyMax = maxEnergy;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack){
		return 1D - (ElectricItem.manager.getCharge(stack) / getMaxCharge(stack));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs aTab, List<ItemStack> subItems) {
		ItemStack stack = new ItemStack(item, 1);
		subItems.add(stack);

		ItemStack charged = new ItemStack(item, 1);
		ElectricItem.manager.charge(charged, 2147483647, getTier(charged), true, false);
		subItems.add(charged);
	}
	
	public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(I18n.format("item.ItemMiniIonCannon.info"));
		if (ElectricItem.manager.getCharge(stack) <= 100000) {
			list.add(I18n.format("item.ItemMiniIonCannon.coolingDown"));
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
		if (stack.hasTagCompound() && !stack.getTagCompound().hasKey("RailgunID")) {
			NBTTagCompound info = new NBTTagCompound();
			info.setString("OwnerUUID", player.getUniqueID().toString());
			stack.getTagCompound().setTag("railgunID", info);
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (worldIn.isRemote)
			return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
		return new ActionResult<ItemStack>(EnumActionResult.PASS, this.ionCannonLogic(itemStackIn, worldIn, playerIn, hand));
    }
	
	private ItemStack ionCannonLogic(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		boolean active = itemStackIn.getTagCompound().getBoolean("active");
		if (!worldIn.isRemote && playerIn.isSneaking()) {
			this.setStatus(itemStackIn, active);
			return itemStackIn;
		}
		
		if (active && ElectricItem.manager.canUse(itemStackIn, 5000000)) {
			worldIn.spawnEntityInWorld(new EntityIonCannonBeam(worldIn, playerIn));
			playerIn.addChatMessage(new net.minecraft.util.text.TextComponentTranslation("item.ItemMiniIonCannon.warning"));
		}
		return itemStackIn;
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
		stack.getTagCompound().setBoolean("active", status);
		return stack;
	}

}
