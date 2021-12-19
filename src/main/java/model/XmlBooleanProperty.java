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
public class XmlBooleanProperty extends XmlProperty{
	
	private static final Logger LOGGER = LogManager.getLogger(XmlBooleanProperty.class);
	
	private boolean value;
	private boolean defaultValue;

	public XmlBooleanProperty(Node propertyNode) throws XmlPropertyParsingException {
		super(propertyNode);
		this.type = XmlPropertyUtils.TYPE_BOOLEAN_NAME;
		this.value = Boolean.parseBoolean(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.VALUE_NODE_NAME));
		this.defaultValue = Boolean.parseBoolean(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.DEFAULT_VALUE_NODE_NAME));
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
		
	public Boolean getValue() {
		return value;
	}
	
	
	public void setValue(Boolean value) {
		this.value = value;
	}
	
	
	public Boolean getDefaultValue() {
		return defaultValue;
	}
	
	
	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

}
