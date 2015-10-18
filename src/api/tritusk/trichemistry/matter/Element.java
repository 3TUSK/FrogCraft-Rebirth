package tritusk.trichemistry.matter;

public interface Element {
	
	/**
	 * Unique atomic number
	 */
	int atomicNumber();
	
	/**
	 * Unique IUPAC name
	 */
	String name();
	
	/**
	 * return null for unavailable
	 */
	default String[] alternativeName() {
		return null;
	}
	
	/**
	 * Mathematically equivalent.
	 */
	default int protonNumber() {
		return atomicNumber();
	}
	
	/**
	 * Only for most common nucleus
	 */
	int neutronNumber();

}
