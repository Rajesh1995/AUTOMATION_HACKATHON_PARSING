package com.cerner.automation.hackathon.main;

import java.io.IOException;

import org.jdom2.JDOMException;

import com.cerner.automation.hackathon.xml.XmlConverter;

public class Main {
	public static void main(String a[]) throws JDOMException, IOException {
		String pathToPom = "C:\\pom.xml";
		String tag = "version";
		String value = "ggwp";
		XmlConverter xml = new XmlConverter();
		System.out.println(xml.getValueFromXMLTag(xml.pomToModel(pathToPom), tag));
		xml.modifyFile(pathToPom, tag, value);
		System.out.println(xml.getValueFromXMLTag(xml.pomToModel(pathToPom), tag));

	}
}
