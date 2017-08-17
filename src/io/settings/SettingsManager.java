package io.settings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SettingsManager {
	// DataArray: id, username, passwordhash, email, ip,
	List<String> xmlData = new ArrayList<>();

	public SettingsManager() {
		// System.out.println("Settingsmanager");
	}

	public void writeXML(List<String> fields, List<String> data) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("settings");
			doc.appendChild(rootElement);

			// staff elements
			Element serverdata = doc.createElement("serverdata");
			rootElement.appendChild(serverdata);

			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue("1");
			serverdata.setAttributeNode(attr);

			// shorten way
			// staff.setAttribute("id", "1");

			int index = 0;

			for (String fieldsString : fields) {
				// System.out.println(fieldsString);
				Element element = doc.createElement(fieldsString);
				// System.out.println(data.get(index));
				element.appendChild(doc.createTextNode(data.get(index)));
				serverdata.appendChild(element);
				index++;
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(System.getProperty("user.home"), ".TelegramSettings.xml"));

			transformer.transform(source, result);

			// System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public List<String> readXML(NodeList nodeList) {
		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get node name and value
				// System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				xmlData.add(tempNode.getNodeName());
				xmlData.add(tempNode.getTextContent());
				// System.out.println("Node Value =" + tempNode.getTextContent());

				if (tempNode.hasChildNodes()) {

					this.readXML(tempNode.getChildNodes());

				}

				// System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

			}
		}
		return xmlData;
	}

	public String getDataOf(String XMLNodename) {
		List<String> data = this.readData();
		if (!data.contains(XMLNodename))
			throw new IllegalArgumentException("Couldn't find " + XMLNodename);
		for (int i = 4; i < data.size(); i++) {
			if (i % 2 == 0 && data.get(i) == XMLNodename) {
				return data.get(i + 1);
			}
		}
		return XMLNodename;
	}

	public List<String> readData() {
		List<String> data = new ArrayList<>();
		try {
			File fXmlFile = new File(System.getProperty("user.home"), ".TelegramSettings.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			// System.out.println("Root element :" +
			// doc.getDocumentElement().getNodeName());

			// System.out.println("----------------------------");
			data = readXML(doc.getChildNodes());

		} catch (Exception e) {
			e.printStackTrace();
		}
		xmlData = new ArrayList<>();
		return data;
	}
}
