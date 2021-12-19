package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;
import properties.XmlPropertyParsingException;
import properties.XmlPropertyUtils;

import java.util.ArrayList;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class XmlStringListProperty extends XmlListProperty{
	
	private static final Logger LOGGER = LogManager.getLogger(XmlStringListProperty.class);

	private int value;
	private int defaultValue;
	private ArrayList<String> possibleValues = new ArrayList<>();
	
	public XmlStringListProperty(Node propertyNode) throws XmlPropertyParsingException {
		super(propertyNode);
		this.type = XmlPropertyUtils.TYPE_STRING_LIST_NAME;
		this.value = Integer.parseInt(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.VALUE_NODE_NAME));
		this.defaultValue = Integer.parseInt(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.DEFAULT_VALUE_NODE_NAME));
		this.possibleValues = XmlPropertyUtils.parseListNode(propertyNode, XmlPropertyUtils.POSSIBLE_VALUES_NODE_NAME);
		LOGGER.info("Property parsed with : " +this.toString());
	}
	
	//////////OVERRIDE METHODS //////////

	@Override
	public String getStringValue() {
		return this.possibleValues.get(this.getValue());
	}

	@Override
	public String getStringDefaultValue() {
		return this.possibleValues.get(this.getDefaultValue());
	}
	
	//////////GETTERS AND SETTERS //////////

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(int defaultValue) {
		this.defaultValue = defaultValue;
	}

	public ArrayList<String> getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(ArrayList<String> possibleValues) {
		this.possibleValues = possibleValues;
	}
	
}
