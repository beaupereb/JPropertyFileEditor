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

import model.XmlListProperty;
import model.XmlProperty;
import views.RootMenuBar;

public class PropertiesXmlFileManager {
	
	private static final String PROPERTY_NODE_NAME = "property";
	private static final String ATTRIBUTE_TYPE_NAME = "type";
	
	private static final String ATTRIBUTE_TYPE_STRING = "string";
	private static final String ATTRIBUTE_TYPE_STRING_LIST = "stringList";
	private static final String ATTRIBUTE_TYPE_BOOLEAN = "boolean";
	private static final String ATTRIBUTE_TYPE_INTEGER = "integer";
	
	private static final String NAME_NODE_NAME = "name";
	private static final String DEFAULT_NODE_NAME = "default";
	private static final String VALUE_NODE_NAME = "value";
	private static final String POSSIBLE_VALUES_NODE_NAME = "possibleValues";
	
	private static final String VALUE_NODE_BOOLEAN_TRUE = "true";
	private static final String VALUE_NODE_BOOLEAN_FALSE = "false";
	
	private static final Logger LOGGER = LogManager.getLogger(PropertiesXmlFileManager.class);
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder;
	Document document;
	
	private TreeSet<XmlProperty> xmlProperties = new TreeSet<>();

	
	public PropertiesXmlFileManager() {
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
	        	Node node = nodeList.item(i);
	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		String nodeName = node.getNodeName();
	        		if(nodeName.equals(PROPERTY_NODE_NAME)) {
	        			Node attributeTypeNode = node.getAttributes().getNamedItem(ATTRIBUTE_TYPE_NAME);
	        			if(attributeTypeNode != null) {
	        				String attributeTypeNodeValue = attributeTypeNode.getNodeValue();
	        				if (attributeTypeNodeValue.equals(ATTRIBUTE_TYPE_STRING)) {
	        					this.parseStringPropertyXmlNode(node);
	        				} else if (attributeTypeNodeValue.equals(ATTRIBUTE_TYPE_STRING_LIST)) {
	        					this.parseStringListPropertyXmlNode(node);
	        				} else if (attributeTypeNodeValue.equals(ATTRIBUTE_TYPE_BOOLEAN)){
	        					this.parseBooleanPropertyXmlNode(node);
	        				} else if (attributeTypeNodeValue.equals(ATTRIBUTE_TYPE_INTEGER)){
	        					this.parseIntegerPropertyXmlNode(node);
	        				} else {
	        					
	        				}
	        			} else {
	        				
	        			}
	        		} else {
	        			
	        		}
	        	} else {
	        		
	        	}
	        }
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}	
	}
	
	private void parseBooleanPropertyXmlNode(Node node) {
		String propertyName = "";
		boolean propertyDefaultValue = false;
		boolean propertyValue = false;
		boolean parsingError = false;
		
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
        	Node childNode = nodeList.item(i);
        	if (childNode.getNodeType() == Node.ELEMENT_NODE) {
        		String childNodeName = childNode.getNodeName();
        		if(childNodeName.equals(NAME_NODE_NAME)) {
        			propertyName = childNode.getFirstChild().getNodeValue();
        		} else if(childNodeName.equals(DEFAULT_NODE_NAME)) {
        			String stringDefaultValue = childNode.getFirstChild().getNodeValue();
        			if(stringDefaultValue.equals(VALUE_NODE_BOOLEAN_TRUE)) {
        				propertyDefaultValue = true;
        			} else if(stringDefaultValue.equals(VALUE_NODE_BOOLEAN_FALSE)){
        				propertyDefaultValue = false;
        			} else {
        				
        			}
        		}else if(childNodeName.equals(VALUE_NODE_NAME)) {
        			String stringValue = childNode.getFirstChild().getNodeValue();
        			if(stringValue.equals(VALUE_NODE_BOOLEAN_TRUE)) {
        				propertyValue = true;
        			} else if(stringValue.equals(VALUE_NODE_BOOLEAN_FALSE)){
        				propertyValue = false;
        			} else {
        				
        			}
        		} else {
        			
        		}	
        	} else {
        		
        	}
		}
		if(parsingError == false) {
			LOGGER.info("Adding boolean property with : name=" +propertyName + " - default Value=" +propertyDefaultValue + " - value=" + propertyValue);
			XmlProperty<Boolean> newProperty = new XmlProperty<Boolean>(propertyName, propertyDefaultValue, propertyValue);
			this.xmlProperties.add(newProperty);
		} else {
			
		}
	}
	
	private void parseStringPropertyXmlNode(Node node) {
		String propertyName = "";
		String propertyDefaultValue = "";
		String propertyValue = "";
		boolean parsingError = false;
		
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
        	Node childNode = nodeList.item(i);
        	if (childNode.getNodeType() == Node.ELEMENT_NODE) {
        		String childNodeName = childNode.getNodeName();
        		if(childNodeName.equals(NAME_NODE_NAME)) {
        			propertyName = childNode.getFirstChild().getNodeValue();
        		} else if(childNodeName.equals(DEFAULT_NODE_NAME)) {
        			propertyDefaultValue = childNode.getFirstChild().getNodeValue();
        		}else if(childNodeName.equals(VALUE_NODE_NAME)) {
        			propertyValue = childNode.getFirstChild().getNodeValue();
        		} else {
        			
        		}	
        	} else {
        		
        	}
		}
		if(parsingError == false) {
			LOGGER.info("Adding string property with : name=" +propertyName + " - default Value=" +propertyDefaultValue + " - value=" + propertyValue);
			XmlProperty<String> newProperty = new XmlProperty<String>(propertyName, propertyDefaultValue, propertyValue);
			this.xmlProperties.add(newProperty);
		} else {
			
		}
	}
	
	private void parseStringListPropertyXmlNode(Node node) {
		String propertyName = "";
		int propertyDefaultValueIndex = 0;
		int propertyValueIndex = 0;
		ArrayList<String> propertyPossibleValues = new ArrayList<>();
		boolean parsingError = false;
		
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
        	Node childNode = nodeList.item(i);
        	if (childNode.getNodeType() == Node.ELEMENT_NODE) {
        		String childNodeName = childNode.getNodeName();
        		if(childNodeName.equals(NAME_NODE_NAME)) {
        			propertyName = childNode.getFirstChild().getNodeValue();
        		} else if(childNodeName.equals(DEFAULT_NODE_NAME)) {
        			propertyDefaultValueIndex = Integer.parseInt(childNode.getFirstChild().getNodeValue());
        		}else if(childNodeName.equals(VALUE_NODE_NAME)) {
        			propertyValueIndex = propertyDefaultValueIndex = Integer.parseInt(childNode.getFirstChild().getNodeValue());
        		} else if (childNodeName.equals(POSSIBLE_VALUES_NODE_NAME)) {
        			NodeList possibleValuesNodeList = childNode.getChildNodes();
        			for (int y = 0; y < possibleValuesNodeList.getLength(); y++) {
        				Node possibleValueNode = possibleValuesNodeList.item(y);
        				if (possibleValueNode.getNodeType() == Node.ELEMENT_NODE) {
        					propertyPossibleValues.add((possibleValueNode.getFirstChild().getNodeValue()));
        				} else {
        					
        				}
        			}
        		} else {
        			
        		}	
        	} else {
        		
        	}
		}
		if(parsingError == false) {
			LOGGER.info("Adding string list property with : name=" +propertyName + " - default Value=" +propertyPossibleValues.get(propertyDefaultValueIndex) + " - value=" + propertyPossibleValues.get(propertyValueIndex));
			XmlListProperty<String> newProperty = new XmlListProperty<String>(propertyName, propertyDefaultValueIndex, propertyValueIndex, propertyPossibleValues);
			this.xmlProperties.add(newProperty);
		} else {
			
		}
	}
	
	private void parseIntegerPropertyXmlNode(Node node) {
		String propertyName = "";
		int propertyDefaultValue = 0;
		int propertyValue = 0;
		boolean parsingError = false;
		
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
        	Node childNode = nodeList.item(i);
        	if (childNode.getNodeType() == Node.ELEMENT_NODE) {
        		String childNodeName = childNode.getNodeName();
        		if(childNodeName.equals(NAME_NODE_NAME)) {
        			propertyName = childNode.getFirstChild().getNodeValue();
        		} else if(childNodeName.equals(DEFAULT_NODE_NAME)) {
        			propertyDefaultValue = Integer.parseInt(childNode.getFirstChild().getNodeValue());
        		}else if(childNodeName.equals(VALUE_NODE_NAME)) {
        			propertyValue = Integer.parseInt(childNode.getFirstChild().getNodeValue());
        		} else {
        			
        		}	
        	} else {
        		
        	}
		}
		if(parsingError == false) {
			LOGGER.info("Adding Integer property with : name=" +propertyName + " - default Value=" +propertyDefaultValue + " - value=" + propertyValue);
			XmlProperty<Integer> newProperty = new XmlProperty<Integer>(propertyName, propertyDefaultValue, propertyValue);
			this.xmlProperties.add(newProperty);
		} else {
			
		}
	}

	
	
	private void printNode(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			System.out.println("ELEMENT_NODE : Name : " +node.getNodeName());
			NamedNodeMap attributes = node.getAttributes();
			for(int i=0; i< attributes.getLength(); i++) {
				System.out.print(" - attribute" +attributes.item(i).getNodeName() );
			}
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
	        	Node childNode = nodeList.item(i);
	        	this.printNode(childNode);
	        }
		} else if (node.getNodeType() == Node.TEXT_NODE){
			String value = node.getNodeValue().trim();
			if (value.equals("") == false) {
				System.out.println("	-	TEXT_NODE :	text : " +value);
	        }
		}
	}
}
