package controller;

import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import properties.XmlPropertyParsingException;
import properties.XmlPropertyUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class XmlPropertyFileManager {
	
	private static final Logger LOGGER = LogManager.getLogger(XmlPropertyFileManager.class);
	
	private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder documentBuilder;
	private Document document;
	
	private ArrayList<XmlProperty> xmlProperties = new ArrayList<>();

	
	public XmlPropertyFileManager() {
		this.parseXmlFile(new File("src/main/resources/propertiesFormat.xml"));
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
	        				XmlProperty newProp = new XmlStringListProperty(propertyNode);
	        				this.xmlProperties.add(newProp);
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
	
	////////// GETTERS AND SETTERS //////////
	
	public ArrayList<XmlProperty> getXmlProperties() {
		return xmlProperties;
	}

	public void setXmlProperties(ArrayList<XmlProperty> xmlProperties) {
		this.xmlProperties = xmlProperties;
	}
}
