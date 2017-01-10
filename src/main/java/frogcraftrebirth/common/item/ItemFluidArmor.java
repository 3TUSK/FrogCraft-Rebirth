package frogcraftrebirth.common.item;

import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.item.FluidArmorPotionEffectManager;
import ic2.api.item.IMetalArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

/**
 * @author 3TUSK
 * Created at 7:46:59 PM Sep 6, 2015 EST 2015
 */
public class ItemFluidArmor extends ItemArmor implements IMetalArmor, ISpecialArmor {

	public static final ArmorMaterial FLUID_ARMOR = EnumHelper.addArmorMaterial("fluidArmor", "armorMaterial.fluidArmor", 10, new int[] {0,0,0,0}, 5, null, 1);
	
	public final int capacity;
	
	public ItemFluidArmor(int capacity) {
		super(FLUID_ARMOR, 0, EntityEquipmentSlot.CHEST);
		setMaxStackSize(1);
		setCreativeTab(FrogAPI.TAB);
		setUnlocalizedName("fluidArmor");
		this.capacity = capacity;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return "frogcraftrebirth:textures/items/fluid_armor.png";
	}

	@Override
	public boolean isMetalArmor(ItemStack itemstack, EntityPlayer player) {
		return true;
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if (world.isRemote) return;
		
		FluidStack currentFluid = itemStack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).getTankProperties()[0].getContents();
		if (currentFluid != null) {
			FluidArmorPotionEffectManager.INSTANCE.getEffect(currentFluid.getFluid()).forEach(effect -> player.addPotionEffect(effect));
		}
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound tag) {
		return new FluidHandlerItemStack(stack, capacity);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> info, boolean adv) {
		FluidStack fluid = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).getTankProperties()[0].getContents();
		if (fluid != null) {
			info.add("Name:" + fluid.getLocalizedName());
			info.add("Amount: " + fluid.amount);
		} else {
			info.add("No fluid is in armor now");
		}
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, 1.0, 100);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		
	}

}
