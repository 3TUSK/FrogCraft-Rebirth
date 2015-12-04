package frogcraftrewrite.api.impl.chemlab;

import info.tritusk.tritchemlab.matter.Element;

public class FrogElement implements Element {
	
	private final int atomicNum;
	private final float relativeMass;
	private final String name, abbr;

	public FrogElement(int num, String name, String abbr, float relativeAtomMass) {
		this.atomicNum = num;
		this.relativeMass = relativeAtomMass;
		this.name = name;
		this.abbr = abbr;
	}
	
	@Override
	public int atomicNumber() {
		return this.atomicNum;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public float relativeAtomicMass() {
		return relativeMass;
	}

	@Override
	public String symbol() {
		return abbr;
	}

}
