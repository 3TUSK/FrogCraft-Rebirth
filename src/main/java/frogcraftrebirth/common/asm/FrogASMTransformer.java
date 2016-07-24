/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 9:18:25 PM, Apr 11, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class FrogASMTransformer implements IClassTransformer {

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (transformedName.equals("net.minecraft.world.biome.BiomeProvider")) {
			FrogASMPlugin.LOGGER.info("Due to technical reasons, IC2-ex10 cannot work properly in newer version of Forge. This will try to fix that.");
			
			ClassReader reader = new ClassReader(basicClass);
			ClassNode node = new ClassNode();
			reader.accept(node, 0);

			MethodVisitor meth = node.visitMethod(Opcodes.ACC_PUBLIC, "getBiomeGenerator", "(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/biome/Biome;)Lnet/minecraft/world/biome/Biome;", null, null);
			meth.visitVarInsn(Opcodes.ALOAD, 0);
			meth.visitFieldInsn(Opcodes.GETFIELD, "net/minecraft/world/biome/BiomeProvider", "biomeCache", "Lnet/minecraft/world/biome/BiomeCache;");
			meth.visitVarInsn(Opcodes.ALOAD, 1);
			meth.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "net/minecraft/util/math/BlockPos", "getX", "()I", false);
			meth.visitVarInsn(Opcodes.ALOAD, 1);
			meth.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "net/minecraft/util/math/BlockPos", "getZ", "()I", false);
			meth.visitVarInsn(Opcodes.ALOAD, 2);
			meth.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "net/minecraft/world/biome/BiomeCache", "getBiome", "(IILnet/minecraft/world/biome/Biome;)Lnet/minecraft/world/biome/Biome;", false);
			meth.visitInsn(Opcodes.ARETURN);
			node.visitEnd();
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			node.accept(writer);
			return writer.toByteArray();
		}
		return basicClass;
	}

}
