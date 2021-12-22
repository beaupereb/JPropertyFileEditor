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
public class XmlIntegerProperty extends XmlProperty{
	
	private static final Logger LOGGER = LogManager.getLogger(XmlIntegerProperty.class);
	
	private int value;
	private int defaultValue;

	public XmlIntegerProperty(Node propertyNode) throws XmlPropertyParsingException {
		super(propertyNode);
		this.type = XmlPropertyUtils.TYPE_INTEGER_NAME;
		this.value = Integer.parseInt(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.VALUE_NODE_NAME));
		this.defaultValue = Integer.parseInt(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.DEFAULT_VALUE_NODE_NAME));
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
		
	public Integer getValue() {
		return value;
	}
	
	
	public void setValue(Integer value) {
		Integer oldValue = this.value;
		this.value = value;
		this.propertyChangeSupport.firePropertyChange("value",oldValue,value);
	}
	
	
	public Integer getDefaultValue() {
		return defaultValue;
	}
	
	
	public void setDefaultValue(Integer defaultValue) {
		this.defaultValue = defaultValue;
	}

}
