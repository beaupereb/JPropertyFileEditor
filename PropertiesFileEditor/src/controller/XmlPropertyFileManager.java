package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

import model.XmlBooleanProperty;
import model.XmlFloatProperty;
import model.XmlIntegerProperty;
import model.XmlListProperty;
import model.XmlProperty;
import model.XmlStringListProperty;
import model.XmlStringProperty;
import properties.XmlPropertyParsingException;
import properties.XmlPropertyUtils;
import views.RootMenuBar;

public class XmlPropertyFileManager {
	
	private static final Logger LOGGER = LogManager.getLogger(XmlPropertyFileManager.class);
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder;
	Document document;
	
	private TreeSet<XmlProperty> xmlProperties = new TreeSet<>();

	
	public XmlPropertyFileManager() {
		this.parseXmlFile(new File("src/resources/propertiesFormat.xml"));
	}
	
	private void parseXmlFile(File file) {
		try {
			documentBuilder = factory.newDocumentBuilder();
			this.document =  documentBuilder.parse(file);
			//document.normalize();
			Node properties = document.getFirstChild();
			//this.printNode(properties);
			// loop the supercar child node
	        NodeList nodeList = properties.getChildNodes();
	        for (int i = 0; i < nodeList.getLength(); i++) {
	        	Node propertyNode = nodeList.item(i);
	        	if (propertyNode.getNodeType() == Node.ELEMENT_NODE) {
	        		String nodeName = propertyNode.getNodeName();
	        		if(nodeName.equals(XmlPropertyUtils.PROPERTY_NODE_NAME)) {
	        			String propertyType = XmlPropertyUtils.parseAttribute(propertyNode, XmlPropertyUtils.TYPE_ATTRIBUTE_NAME);
	        			if(propertyType.equals(XmlPropertyUtils.TYPE_STRING_NAME)) {
	        				this.xmlProperties.add(new XmlStringProperty(propertyNode));
	        			} else if (propertyType.equals(XmlPropertyUtils.TYPE_INTEGER_NAME)) {
	        				this.xmlProperties.add(new XmlIntegerProperty(propertyNode));
	        			} else if (propertyType.equals(XmlPropertyUtils.TYPE_FLOAT_NAME)) {
	        				this.xmlProperties.add(new XmlFloatProperty(propertyNode));
	        			} else if (propertyType.equals(XmlPropertyUtils.TYPE_BOOLEAN_NAME)) {
	        				this.xmlProperties.add(new XmlBooleanProperty(propertyNode));
	        			} else if (propertyType.equals(XmlPropertyUtils.TYPE_STRING_LIST_NAME)) {
	        				this.xmlProperties.add(new XmlStringListProperty(propertyNode));
	        			}
	        		} 
	        	}
	        }
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XmlPropertyParsingException e) {
			e.printStackTrace();
		}	
	}
}
