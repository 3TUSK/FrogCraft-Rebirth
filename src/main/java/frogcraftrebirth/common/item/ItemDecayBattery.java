/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
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

import ic2.api.item.IElectricItemManager;
import ic2.api.item.ISpecialElectricItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDecayBattery extends ItemFrog implements ISpecialElectricItem {

	private static final IElectricItemManager DECAY_BATTERY_ELECTRIC_MANAGER = new DecayBatteryElectricManager();

	public ItemDecayBattery() {
		super();
		setMaxDamage(0);
		setMaxStackSize(1);
		setNoRepair();
	}

	@Override
	public IElectricItemManager getManager(ItemStack itemStack) {
		return DECAY_BATTERY_ELECTRIC_MANAGER; // BTM Moon: read & write NBT is expensive
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format("item.decay_battery.info.0"));
		tooltips.add(I18n.format("item.decay_battery.info.1"));
	}

	private static final class DecayBatteryElectricManager implements IElectricItemManager {
		@Override
		public double charge(ItemStack itemStack, double v, int i, boolean b, boolean b1) {
			return 0.0;
		}

		@Override
		public double discharge(ItemStack itemStack, double v, int i, boolean b, boolean b1, boolean b2) {
			return 1.0;
		}

		@Override
		public double getCharge(ItemStack itemStack) {
			return 1.0;
		}

		@Override
		public double getMaxCharge(ItemStack itemStack) {
			return 1.0;
		}

		@Override
		public boolean canUse(ItemStack itemStack, double v) {
			return true;
		}

		@Override
		public boolean use(ItemStack itemStack, double v, EntityLivingBase entityLivingBase) {
			return true;
		}

		@Override
		public void chargeFromArmor(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

		@Override //Side note: there is no need to translate - EU is the symbol of IC2 electricity unit
		public String getToolTip(ItemStack itemStack) {
			return "1 /1 EU";
		}

		@Override
		public int getTier(ItemStack itemStack) {
			return 1;
		}
	}

}
