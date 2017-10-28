/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.item;

import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.item.FluidArmorPotionEffectManager;
import ic2.api.item.IMetalArmor;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
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
		setUnlocalizedName("fluid_armor");
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
			FluidArmorPotionEffectManager.INSTANCE.getEffect(currentFluid.getFluid()).forEach(player::addPotionEffect);
		}
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound tag) {
		return new FluidHandlerItemStack(stack, capacity);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> info, ITooltipFlag flag) {
		IFluidHandlerItem handler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		if (handler != null) {
			FluidStack fluid = handler.getTankProperties()[0].getContents();
			if (fluid != null) {
				info.add("Name:" + fluid.getLocalizedName());
				info.add("Amount: " + fluid.amount);
				return;
			}
		}

		info.add("No fluid is in armor now");
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
