/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 9:05:07 PM, Apr 11, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.asm;

import com.google.common.eventbus.EventBus;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class FrogCoremodContainer extends DummyModContainer {
	
	public FrogCoremodContainer() {
		super(new ModMetadata());
		ModMetadata meta = this.getMetadata();
		meta.name = "FrogCraft Coremod Plugin";
		meta.modId = "FrogCraftCore";
		meta.version = "0.0.1";
		meta.authorList = java.util.Arrays.asList("3TUSK");
		meta.description = "Coremod used by FrogCraft: Rebirth. Dont worry - it only modifies FrogCraft: Rebirth itself.";
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

}
