package frogcraftrewrite.common.item;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import frogcraftrewrite.api.FrogAPI;
import ic2.api.item.IMetalArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
/**
 * @author 3TUSK
 * Created at 7:46:59 PM Sep 6, 2015 EST 2015
 */
public class ItemFluidArmor extends ItemArmor implements IMetalArmor, IFluidContainerItem {

	public static final ArmorMaterial FLUID_ARMOR;
	
	static {
		FLUID_ARMOR = EnumHelper.addArmorMaterial("fluidArmor", 1000, new int[] {1,1,1,1}, 5);
	}
	
	protected int capacity;
	
	public ItemFluidArmor(int capacity) {
		super(FLUID_ARMOR, 0, 2);//2->chestplate; 0->cloth(leather) renderer; render stuff is WIP
		setMaxStackSize(1);
		setCreativeTab(FrogAPI.frogTab);
		this.capacity = capacity;
	}

	@Override
	public boolean isMetalArmor(ItemStack itemstack, EntityPlayer player) {
		return true;
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if (world.isRemote) return;
		
		Collection<PotionEffect> effectList = fluidSideEffect.get(this.getFluid(itemStack).getFluid());
		Iterator<PotionEffect> iter = effectList.iterator();
		while (iter.hasNext()) {
			player.addPotionEffect(iter.next());
		}
	}

	@Override
	public FluidStack getFluid(ItemStack container) {
		if (container.stackTagCompound == null) return null;
		if (!container.stackTagCompound.hasKey("Fluid")) return null;
		
		return FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));
	}

	@Override
	public int getCapacity(ItemStack container) {
		return capacity;
	}

	@Override
	public int fill(ItemStack container, FluidStack resource, boolean doFill) {
		if (!doFill) return 0;
		
		NBTTagCompound fluidTag;
		try {
			fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
		} catch (Exception e) {
			fluidTag = new NBTTagCompound();
			resource.writeToNBT(fluidTag);
			container.stackTagCompound.setTag("Fluid", fluidTag);
			return resource.amount;
		}
		
		FluidStack current = FluidStack.loadFluidStackFromNBT(fluidTag);
		
		if (current == null) {
			resource.writeToNBT(fluidTag);
			container.stackTagCompound.setTag("Fluid", fluidTag);
			return resource.amount;
		}
		
		if (current.getFluid() == resource.getFluid()) {
			int newAmount = current.amount + resource.amount;
			fluidTag.setInteger("Amount", newAmount);
			container.stackTagCompound.setTag("Fluid", fluidTag);
			return newAmount > capacity ? resource.amount : capacity - current.amount;
		}
		
		return 0;
	}

	@Override
	public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
		if (!doDrain) return null;
		
		NBTTagCompound fluidTag;
		try {
			fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
		} catch (Exception e) {
			fluidTag = new NBTTagCompound();
			container.stackTagCompound.setTag("Fluid", fluidTag);
			return null;
		}
		
		FluidStack current = FluidStack.loadFluidStackFromNBT(fluidTag);
		
		if (current == null) return null;
		
		if (maxDrain > capacity || current.amount <= maxDrain) {
			container.stackTagCompound.setTag("Fluid", new NBTTagCompound());
			return current;
		} else {
			int isDraining = current.amount - maxDrain;
			current.amount -= isDraining;
			current.writeToNBT(fluidTag);
			container.stackTagCompound.setTag("Fluid", fluidTag);
			return new FluidStack(current.getFluid(), isDraining);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List info, boolean adv) {
		FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.stackTagCompound.getCompoundTag("Fluid"));
		info.add("Name:" + fluid.getLocalizedName());
		info.add("Amount" + fluid.amount);
	}

	//Fluid armor side-effect system start, TODO: finish this system
	
	private static Multimap<Fluid, PotionEffect> fluidSideEffect = ArrayListMultimap.<Fluid, PotionEffect>create();
	
	public static boolean registerFluidArmorSideEffect(Fluid fluid, PotionEffect potion) {
		return fluidSideEffect.put(fluid, potion);
	}
	
}
