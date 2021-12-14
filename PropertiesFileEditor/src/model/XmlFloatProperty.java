package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;

import properties.XmlPropertyParsingException;
import properties.XmlPropertyUtils;

public class XmlFloatProperty extends XmlProperty{
	
	private static final Logger LOGGER = LogManager.getLogger(XmlFloatProperty.class);
	
	private float value;
	private float defaultValue;

	public XmlFloatProperty(Node propertyNode) throws XmlPropertyParsingException {
		super(propertyNode);
		this.value = Float.parseFloat(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.VALUE_NODE_NAME));
		this.defaultValue = Float.parseFloat(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.DEFAULT_VALUE_NODE_NAME));
	
		LOGGER.info("Property parsed with : " +this.toString());
	}

	
	////////// OVERRIDE METHODS //////////
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{Type=" +XmlPropertyUtils.TYPE_FLOAT_NAME +"}");
		stringBuilder.append("{Name=" +this.getName() +"}");
		stringBuilder.append("{Value=" +this.getValue() +"}");
		stringBuilder.append("{Default value=" +this.getDefaultValue() +"}");
		stringBuilder.append("{Group=" +this.getGroup() +"}");
		stringBuilder.append("{Description=" +this.getDescription() +"}");
		return stringBuilder.toString();
	}
	
	//////////GETTERS AND SETTERS //////////
		
	public Float getValue() {
		return value;
	}
	
	
	public void setValue(Float value) {
		this.value = value;
	}
	
	
	public Float getDefaultValue() {
		return defaultValue;
	}
	
	
	public void setDefaultValue(Float defaultValue) {
		this.defaultValue = defaultValue;
	}
}
