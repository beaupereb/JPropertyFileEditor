package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import controller.XmlPropertyFileManager;
import properties.XmlPropertyParsingException;
import properties.XmlPropertyUtils;

import javax.xml.parsers.*;
import java.io.*;

public abstract class XmlProperty implements Comparable {
	
	protected String name;
	private String description;
	protected String group;
	
	public XmlProperty(Node propertyNode) throws XmlPropertyParsingException {
		
		this.name = XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.NAME_NODE_NAME);
		if (this.name.isBlank()) {
			throw new XmlPropertyParsingException();
		}
		this.description = XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.DESCRIPTION_NODE_NAME);
		this.group = XmlPropertyUtils.parseAttribute(propertyNode, XmlPropertyUtils.GROUP_ATTRIBUTE_NAME);
	}
	

	////////// OVERRIDE METHODS //////////
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	////////// GETTERS AND SETTERS //////////
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
