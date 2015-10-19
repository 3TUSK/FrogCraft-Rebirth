package tritusk.trichemistry.parser;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tritusk.trichemistry.matter.Element;

public interface ElementArrayParser {
	
	static Logger parseLog = LogManager.getLogger("TrituskChemistry-Elements");
	
	/**
	 * @param file The file which is going to be parsed
	 * @param force Allow manual reload file
	 * @return An array that contains all chemcial elements
	 */
	Element[] parseElements(InputStream input, boolean force);

}
