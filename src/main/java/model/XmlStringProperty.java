package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import properties.XmlPropertyParsingException;
import properties.XmlPropertyUtils;

public class XmlStringProperty extends XmlProperty{

	private static final Logger LOGGER = LogManager.getLogger(XmlStringProperty.class);
	
	
	private String value;
	private String defaultValue;
	
	
	public XmlStringProperty(Node propertyNode) throws XmlPropertyParsingException {
		super(propertyNode);
		this.type = XmlPropertyUtils.TYPE_STRING_NAME;
		this.value = XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.VALUE_NODE_NAME);
		this.defaultValue = XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.DEFAULT_VALUE_NODE_NAME);
		LOGGER.info("Property parsed with : " +this.toString());
	}
	
	
	
	
	////////// OVERRIDE METHODS //////////
	
	@Override
	public String getStringValue() {
		return this.getValue();
	}

	@Override
	public String getStringDefaultValue() {
		return this.getDefaultValue();
	}

	//////////GETTERS AND SETTERS //////////
	
	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getDefaultValue() {
		return defaultValue;
	}


	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
