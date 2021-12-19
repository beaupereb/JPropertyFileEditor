package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;
import properties.XmlPropertyParsingException;
import properties.XmlPropertyUtils;


/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class XmlFloatProperty extends XmlProperty{
	
	private static final Logger LOGGER = LogManager.getLogger(XmlFloatProperty.class);
	
	private float value;
	private float defaultValue;

	public XmlFloatProperty(Node propertyNode) throws XmlPropertyParsingException {
		super(propertyNode);
		this.type = XmlPropertyUtils.TYPE_FLOAT_NAME;
		this.value = Float.parseFloat(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.VALUE_NODE_NAME));
		this.defaultValue = Float.parseFloat(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.DEFAULT_VALUE_NODE_NAME));
		LOGGER.info("Property parsed with : " +this.toString());
	}

	
	////////// OVERRIDE METHODS //////////

	@Override
	public String getStringValue() {
		return "" +this.getValue();
	}

	@Override
	public String getStringDefaultValue() {
		return "" +this.getDefaultValue();
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
