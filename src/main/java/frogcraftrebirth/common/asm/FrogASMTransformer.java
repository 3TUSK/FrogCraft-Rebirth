/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 9:18:25 PM, Apr 11, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class FrogASMTransformer implements IClassTransformer {

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		try {
			Class.forName("ic2.api.energy.tile.IEnergySourceInfo");
			return basicClass; //If no exception is thrown, then there is no need to use this transformer.
		} catch (Exception e) {}
		
		if (transformedName.equals("frogcraftrebirth/lib/tile/TileFrogEStorage") || transformedName.equals("frogcraftrebirth/lib/tile/TileFrogGenerator")) {
			ClassReader reader = new ClassReader(basicClass);
			ClassNode node = new ClassNode();
			reader.accept(node, 0);
			boolean attemptRemoving = node.interfaces.remove("ic2/api/energy/tile/IEnergySourceInfo");
			if (attemptRemoving) {
				ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
				node.accept(writer);
				FrogASMPlugin.ic2ClassicDetected = true;
				return writer.toByteArray();
			}
		}
		return basicClass;
	}

}
