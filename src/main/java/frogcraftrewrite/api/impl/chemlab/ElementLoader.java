package frogcraftrewrite.api.impl.chemlab;

import static frogcraftrewrite.FrogCraftRebirth.frogLogger;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import info.tritusk.tritchemlab.matter.Element;
import info.tritusk.tritchemlab.parser.ElementArrayParser;

public final class ElementLoader implements ElementArrayParser {

	public static final ElementArrayParser FROG_PARSER = new ElementLoader();

	private ElementLoader() {}

	private boolean parsingFinished = false;

	@Override
	public synchronized Element[] parseElements(InputStream input, boolean force) {
		if (parsingFinished && force == false) {
			frogLogger.info("FrogCraft has finished elements parsing. Call will be denied.");
			return null;
		}
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder construct = factory.newDocumentBuilder();
			Document xmlDoc = construct.parse(input);
			xmlDoc.getDocumentElement().normalize();
			NodeList array = xmlDoc.getElementsByTagName("elementData");
			Element[] elements = new Element[array.getLength()];
			for (int n = 0; n < array.getLength(); n++) {
				Node elementEntry = array.item(n);
				int atoNum = Integer.parseInt(elementEntry.getAttributes().getNamedItem("num").getTextContent());
				String name = elementEntry.getAttributes().getNamedItem("name").getTextContent();
				String abbr = elementEntry.getAttributes().getNamedItem("symbol").getTextContent();
				elements[n] = new FrogElement(atoNum, name, abbr, 0F);//todo
			}
			parsingFinished = true;
			return elements;
		} catch (Exception e) {
			frogLogger.error("An error has occurred and loading cannot continue.");
			return null;
		}
	}

}
