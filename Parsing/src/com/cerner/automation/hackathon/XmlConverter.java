package com.cerner.automation.hackathon.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlConverter {
	public String getValueFromXMLTag(Element projectElement, String tag) {
		List<Element> elements = projectElement.getChildren();
		Element element;
		String name, value = "";
		Iterator<Element> iterate = elements.iterator();
		while (iterate.hasNext()) {
			element = iterate.next();
			name = element.getName();
			if (tag != null && tag.equals(name)) {
				value = element.getValue();
				break;
			}
		}
		return value;

	}

	public void modifyFile(String pathToPom, String tag, String value) throws JDOMException, IOException {
		Element projectElement = pomToModel(pathToPom);
		List<Element> elements = projectElement.getChildren();
		Element element = null;
		Iterator<Element> iterate = elements.iterator();
		while (iterate.hasNext()) {
			element = iterate.next();
			String name = element.getName();
			if (tag != null && tag.equals(name)) {
				element.setText(value);
				break;
			}
		}
		Document document = element.getDocument();
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(document, new FileWriter(pathToPom));

	}

	public Element pomToModel(String pathToPom) throws JDOMException, IOException {
		File inputFile = new File(pathToPom);
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(inputFile);
		Element projectElement = document.getRootElement();
		return projectElement;
	}
}