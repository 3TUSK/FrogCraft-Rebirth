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

package frogcraftrebirth.api.recipes;

import frogcraftrebirth.common.lib.MultiTypeStorage;
import it.unimi.dsi.fastutil.ints.*;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.stream.IntStream;

public interface AdvChemReaction {

	List<IFrogRecipeInput> getInputs();

	List<Object> getOutputs(); // TODO Do we need a wrapper?

	ItemStack getCatalyst();

	int getTime();

	int getEnergyRate();

	final class MultiTypeMatcher {

		private final MultiTypeStorage inv;

		private final List<IFrogRecipeInput> inputs;

		private int matchResults[] = null;

		private boolean hasRunMatching = false;

		public MultiTypeMatcher(MultiTypeStorage inv, List<IFrogRecipeInput> inputs) {
			this.inv = inv;
			this.inputs = inputs;
		}

		public boolean match() {
			if (this.matchResults != null) {
				return true;
			}

			if (this.hasRunMatching) {
				return false;
			}

			this.hasRunMatching = true;

			if (inv.size() != inputs.size()) {
				return false;
			}

			IntList indexes = IntArrayList.wrap(IntStream.range(0, inputs.size()).toArray());

			for (int i : indexes) {
				IntSet undiscovered = new IntArraySet(indexes);
				IntArrayFIFOQueue visited = new IntArrayFIFOQueue();
				undiscovered.rem(i);
				visited.enqueue(i);
				this.matchResults = visit(undiscovered, visited);
				if (this.matchResults != null) {
					break;
				}
			}

			return this.matchResults != null;
		}

		/**
		 * DFS-esque searching of the first matched index mapping, implemented using recursion.
		 * @param undiscoveredNodes Nodes that have not been visited
		 * @param discoveredNodes Nodes that have been visited and enqueued
		 * @return the matched result; {@code null} indicates failure of matching.
		 */
		private int[] visit(IntSet undiscoveredNodes, IntArrayFIFOQueue discoveredNodes) {
			if (undiscoveredNodes.isEmpty()) {
				IntList results = new IntArrayList(discoveredNodes.size());
				int index = 0;
				while (!discoveredNodes.isEmpty()) {
					int test = discoveredNodes.firstInt();
					if (this.inputs.get(index).matches(this.inv.indexOf(test).getView())) {
						results.set(index, test);
						index++;
						discoveredNodes.dequeueInt();
					} else {
						return null;
					}
				}
				return results.toIntArray();
			}
			for (int i : undiscoveredNodes) {
				undiscoveredNodes.rem(i);
				discoveredNodes.enqueue(i);
				int result[] = visit(new IntArraySet(undiscoveredNodes), discoveredNodes);
				if (result == null) {
					undiscoveredNodes.add(i);
				} else {
					return result;
				}
			}
			return null; // Catch all - it means we failed to match anything.
		}

	}

}
