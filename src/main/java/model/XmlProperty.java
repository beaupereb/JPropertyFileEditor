package model;

import org.w3c.dom.*;

import properties.XmlPropertyParsingException;
import properties.XmlPropertyUtils;

public abstract class XmlProperty implements Comparable {

	protected String type;
	protected String name;
	protected String description;
	protected String section;
	
	public XmlProperty(Node propertyNode) throws XmlPropertyParsingException {
		
		this.name = XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.NAME_NODE_NAME);
		if (this.name.isBlank()) {
			throw new XmlPropertyParsingException();
		}
		this.description = XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.DESCRIPTION_NODE_NAME);
		this.section = XmlPropertyUtils.parseAttribute(propertyNode, XmlPropertyUtils.SECTION_ATTRIBUTE_NAME);
	}
	

	////////// OVERRIDE METHODS //////////
	
	@Override
	public int compareTo(Object o) {
		return 0;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{Type=" +this.getType()+"}");
		stringBuilder.append("{Name=" +this.getName() +"}");
		stringBuilder.append("{Value=" +this.getStringValue() +"}");
		stringBuilder.append("{Default value=" +this.getStringDefaultValue() +"}");
		stringBuilder.append("{Section=" +this.getSection() +"}");
		stringBuilder.append("{Description=" +this.getDescription() +"}");
		return stringBuilder.toString();
	}
	
	public abstract String getStringValue();
	public abstract String getStringDefaultValue();
	
	////////// GETTERS AND SETTERS //////////
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
