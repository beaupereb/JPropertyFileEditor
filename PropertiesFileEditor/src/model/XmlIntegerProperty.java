package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;

import properties.XmlPropertyParsingException;
import properties.XmlPropertyUtils;

public class XmlIntegerProperty extends XmlProperty{
	
	private static final Logger LOGGER = LogManager.getLogger(XmlIntegerProperty.class);
	
	private int value;
	private int defaultValue;

	public XmlIntegerProperty(Node propertyNode) throws XmlPropertyParsingException {
		super(propertyNode);
		this.value = Integer.parseInt(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.VALUE_NODE_NAME));
		this.defaultValue = Integer.parseInt(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.DEFAULT_VALUE_NODE_NAME));
	
		LOGGER.info("Property parsed with : " +this.toString());
	}
	
	////////// OVERRIDE METHODS //////////
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{Type=" +XmlPropertyUtils.TYPE_INTEGER_NAME +"}");
		stringBuilder.append("{Name=" +this.getName() +"}");
		stringBuilder.append("{Value=" +this.getValue() +"}");
		stringBuilder.append("{Default value=" +this.getDefaultValue() +"}");
		stringBuilder.append("{Group=" +this.getGroup() +"}");
		stringBuilder.append("{Description=" +this.getDescription() +"}");
		return stringBuilder.toString();
	}
	
	//////////GETTERS AND SETTERS //////////
		
	public Integer getValue() {
		return value;
	}
	
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
	public Integer getDefaultValue() {
		return defaultValue;
	}
	
	
	public void setDefaultValue(Integer defaultValue) {
		this.defaultValue = defaultValue;
	}

}
