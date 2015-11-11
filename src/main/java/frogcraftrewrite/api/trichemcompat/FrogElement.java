package frogcraftrewrite.api.trichemcompat;

import info.tritusk.tritchemlab.matter.Element;

public class FrogElement implements Element {
	
	private final int atomicNumber;
	@SuppressWarnings("unused")
	private final String name, abbr;

	public FrogElement(int num, String name, String abbr) {
		this.atomicNumber = num;
		this.name = name;
		this.abbr = abbr;
	}
	
	@Override
	public int atomicNumber() {
		return this.atomicNumber;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public int neutronNumber() {
		return 0; //Technically this is unnecessary method.
	}

}
