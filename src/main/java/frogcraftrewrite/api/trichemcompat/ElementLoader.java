package frogcraftrewrite.api.trichemcompat;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
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
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder construct = factory.newDocumentBuilder();		
			ResourceLocation location = new ResourceLocation("frogcraftrewrite", "config/PeriodicTable.xml");
			IResource rawFile = Minecraft.getMinecraft().getResourceManager().getResource(location);
			Document xmlDoc = construct.parse(rawFile.getInputStream());
			xmlDoc.getDocumentElement().normalize();
			parseLog.info("Found target file. Loading...");
			NodeList array = xmlDoc.getElementsByTagName("elementData");
			for (int n=0;n<array.getLength();n++) {
				Node elementEntry = array.item(n);
				int atoNum = Integer.parseInt(elementEntry.getAttributes().getNamedItem("num").getTextContent());
				String name = elementEntry.getAttributes().getNamedItem("name").getTextContent();
				String abbr = elementEntry.getAttributes().getNamedItem("symbol").getTextContent();
				elements[n] = new FrogElement(atoNum, name, abbr);
			}
			parsingFinished = true;
			return elements;
		} catch (Exception e) {
			parseLog.error("An error has occured and loading cannot continue.");
			e.printStackTrace();
			return null;
		}
	}

}
