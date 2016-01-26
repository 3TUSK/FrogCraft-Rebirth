package frogcraftrebirth.api.impl.chemlab;

import static frogcraftrebirth.FrogCraftRebirth.FROG_LOG;

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
			FROG_LOG.info("FrogCraft has finished elements parsing. Call will be denied.");
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
				short atoNum = Short.parseShort(elementEntry.getAttributes().getNamedItem("num").getTextContent());
				String name = elementEntry.getAttributes().getNamedItem("name").getTextContent();
				String abbr = elementEntry.getAttributes().getNamedItem("symbol").getTextContent();
				elements[n] = new FrogElement(atoNum, name, abbr, 0F);//todo
			}
			parsingFinished = true;
			return elements;
		} catch (Exception e) {
			FROG_LOG.error("An error has occurred and loading cannot continue.");
			return null;
		}
	}

}
