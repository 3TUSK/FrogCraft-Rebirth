package frogcraftrebirth.common.item;

import java.util.Collection;
import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.item.FluidArmorPotionEffectManager;
import ic2.api.item.IMetalArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

/**
 * @author 3TUSK
 * Created at 7:46:59 PM Sep 6, 2015 EST 2015
 */
public class ItemFluidArmor extends ItemArmor implements IMetalArmor {

	public static final ArmorMaterial FLUID_ARMOR = EnumHelper.addArmorMaterial("fluidArmor", "armorMaterial.fluidArmor", 1000, new int[] {1,1,1,1}, 5, null, 1);
	
	protected final int capacity;
	
	public ItemFluidArmor(int capacity) {
		super(FLUID_ARMOR, 0, EntityEquipmentSlot.CHEST);//0->cloth(leather) renderer; render stuff is WIP
		setMaxStackSize(1);
		setCreativeTab(FrogAPI.TAB);
		setRegistryName("fluid_armor");
		setUnlocalizedName("fluidArmor");
		this.capacity = capacity;
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
			Collection<PotionEffect> effectList = FluidArmorPotionEffectManager.INSTANCE.getEffect(currentFluid.getFluid());
			for (PotionEffect effect : effectList) {
				player.addPotionEffect(effect);
			}
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
	
	
	
}
