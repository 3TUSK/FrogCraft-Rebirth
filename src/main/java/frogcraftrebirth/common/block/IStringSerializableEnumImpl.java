/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 2:12:17 PM, Dec 3, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.block;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public interface IStringSerializableEnumImpl extends IStringSerializable {
	
	String name(); // java.lang.Enum already has the same function. So...
	
	default String getName() {
		return this.name().toLowerCase(Locale.ENGLISH);
	}

}
