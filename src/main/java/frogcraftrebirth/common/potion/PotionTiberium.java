/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:44:08 AM, Aug 4, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.potion;

import frogcraftrebirth.api.FrogAPI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

public class PotionTiberium extends Potion {

	public PotionTiberium(int liquidColorIn) {
		super(true, liquidColorIn);
		setPotionName("effect.tiberium");
		setRegistryName("tiberium");
	}
	
	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int foodStateValue) {
		entityLivingBaseIn.attackEntityFrom(FrogAPI.TIBERIUM, 2.0F);
	}

}
