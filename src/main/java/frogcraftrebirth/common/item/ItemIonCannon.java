package frogcraftrebirth.common.item;

import java.util.List;

import frogcraftrebirth.common.entity.EntityIonCannonBeam;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIonCannon extends ItemFrogCraft implements IElectricItem {
	
	private final int energyMax;
	
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
	public void getSubItems(CreativeTabs aTab, NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(aTab)) {
			ItemStack stack = new ItemStack(this, 1);
			subItems.add(stack.copy());
			ElectricItem.manager.charge(stack, 2147483647, getTier(stack), true, false);
			subItems.add(stack);
		}
	}
	
	public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format("item.ItemMiniIonCannon.info"));
		/*if (player.getCooldownTracker().hasCooldown(stack.getItem())) {
			tooltips.add(I18n.format("item.ItemMiniIonCannon.coolingDown"));
		}*/
		if (ElectricItem.manager.getCharge(stack) <= 100000) {
			tooltips.add(I18n.format("item.ItemMiniIonCannon.discharged"));
		}
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
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (worldIn.isRemote)
			return super.onItemRightClick(worldIn, playerIn, hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, this.ionCannonLogic(playerIn.getHeldItem(hand), worldIn, playerIn, hand));
    }
	
	private ItemStack ionCannonLogic(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		boolean active = itemStackIn.getTagCompound().getBoolean("active");
		if (!worldIn.isRemote && playerIn.isSneaking()) {
			this.setStatus(itemStackIn, !active);
			return itemStackIn;
		}
		
		if (!worldIn.isRemote && active && ElectricItem.manager.canUse(itemStackIn, 500000)) {
			ElectricItem.manager.discharge(itemStackIn, 500000, 4, true, false, false);
			worldIn.spawnEntity(new EntityIonCannonBeam(worldIn, playerIn));
			playerIn.getCooldownTracker().setCooldown(itemStackIn.getItem(), 200);
			playerIn.sendMessage(new TextComponentTranslation("item.ItemMiniIonCannon.warning"));
		}
		return itemStackIn;
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
		return 4;
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
