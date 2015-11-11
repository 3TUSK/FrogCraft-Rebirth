package info.tritusk.tritchemlab.parser;

import java.io.InputStream;

import info.tritusk.tritchemlab.matter.Element;

public interface ElementArrayParser {
	
	/**
	 * @param input The input stream which is going to be parsed
	 * @param force Allow to manually reload elements
	 * @return An array that contains all needed chemical elements
	 */
	Element[] parseElements(InputStream input, boolean force);

}
