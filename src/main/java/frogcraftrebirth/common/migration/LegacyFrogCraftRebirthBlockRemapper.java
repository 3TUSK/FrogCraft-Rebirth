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

package frogcraftrebirth.common.migration;

import frogcraftrebirth.api.FrogAPI;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.IFixableData;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.Validate;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class LegacyFrogCraftRebirthBlockRemapper implements IFixableData {

	private static final Map<BlockMetadataTuple, BlockMetadataTuple> MIGRATION_MAP = new HashMap<>();

	@Override
	public int getFixVersion() {
		return FrogAPI.DATA_FIXER_REMARK;
	}

	// https://minecraft.gamepedia.com/Chunk_format
	@Nonnull
	@Override
	public NBTTagCompound fixTagCompound(@Nonnull NBTTagCompound compound) {
		try {
			NBTTagCompound chunk = compound.getCompoundTag("Level");
			NBTTagList subChunkList = chunk.getTagList("Sections", Constants.NBT.TAG_COMPOUND);
			for (NBTBase nbt : subChunkList) {
				Validate.isTrue(nbt instanceof NBTTagCompound);
				NBTTagCompound subChunk = (NBTTagCompound)nbt;
				byte[] blocksRaw = subChunk.getByteArray("Blocks"); // Length 4096, 8 bit per block
				byte[] addendumRaw = subChunk.getByteArray("Add");  // Length 2048, 4 bit per block
				byte[] metadataRaw = subChunk.getByteArray("Data"); // Length 2048, 4 bit per block
				if (addendumRaw.length == 0) {
					continue; // Represents a sub-chunk that contains pure-vanilla blocks
				}
				NibbleArray addendum = new NibbleArray(addendumRaw);
				NibbleArray metadata = new NibbleArray(metadataRaw);
				for (int pos = 0; pos < blocksRaw.length; pos++) {
					int upperID = addendum.getFromIndex(pos);
					if (upperID == 0b0000) { // If it's vanilla block, the upper part shall not exist
						continue; // Ignore all vanilla blocks, the operation below is extremely time-consuming
					}
					upperID <<= 8; // Shift 8 bits left
					int lowerID = blocksRaw[pos]; // The last 8 bits of block id
					int meta = metadata.getFromIndex(pos);
					ResourceLocation namespace = Block.getBlockById(lowerID + upperID).getRegistryName();
					if (namespace != null && FrogAPI.MODID.equals(namespace.getNamespace())) {
						BlockMetadataTuple mapped = MIGRATION_MAP.get(new BlockMetadataTuple(namespace.toString(), meta));
						if (mapped != null) {
							Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(mapped.namespace));
							Objects.requireNonNull(block);
							int newBlockID = Block.getIdFromBlock(block);
							byte newMeta = (byte)mapped.metadata, newLowerID = (byte)(newBlockID & 255), newUpperID = (byte)(newBlockID >> 8);
							blocksRaw[pos] = newLowerID;
							addendum.setIndex(pos, newUpperID);
							metadata.setIndex(pos, newMeta);
						}
					}
				}
			}
		} catch (Exception e) {
			FrogAPI.FROG_LOG.catching(e);
		}
		return compound;
	}

	static final class BlockMetadataTuple {
		final String namespace;
		final int metadata;

		BlockMetadataTuple(String namespace, int metadata) {
			this.namespace = namespace;
			this.metadata = metadata;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			BlockMetadataTuple that = (BlockMetadataTuple) o;
			return metadata == that.metadata && this.namespace.equals(that.namespace);
		}

		@Override
		public int hashCode() {
			return (namespace.hashCode() << 4) + metadata;
		}
	}

	static {
		MIGRATION_MAP.put(new BlockMetadataTuple("frogcraftrebirth:ore", 0), new BlockMetadataTuple("frogcraftrebirth:carnallite", 0));
		MIGRATION_MAP.put(new BlockMetadataTuple("frogcraftrebirth:ore", 1), new BlockMetadataTuple("frogcraftrebirth:dewalquite", 0));
		MIGRATION_MAP.put(new BlockMetadataTuple("frogcraftrebirth:ore", 2), new BlockMetadataTuple("frogcraftrebirth:fluorapatite", 0));
	}
}
