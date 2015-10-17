package frogcraftrewrite.api.trichemcompat;

import java.io.File;

import tritusk.trichemistry.matter.Element;
import tritusk.trichemistry.parser.ElementArrayParser;

public final class ElementLoader implements ElementArrayParser {
	
	public static final ElementArrayParser FROG_PARSER = new ElementLoader();
	
	private ElementLoader() {}
	
	private volatile boolean parsingFinished = false;
	
	@Override
	public synchronized Element[] parseElements(File file, boolean force) {
		if (parsingFinished && force == false) {
			parseLog.info("FrogCraft has finished elements parsing. Call will be denied.");
			return null;
		}
		
		try {
			Element[] elements = new Element[103];
			
			parsingFinished = true;
			return elements;//will be done soon...
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
