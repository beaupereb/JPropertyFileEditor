package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;
import properties.TemplatePropertyParsingException;
import properties.TemplatePropertyUtils;

import java.util.ArrayList;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class TemplateStringListProperty extends TemplateListProperty {
	
	private static final Logger LOGGER = LogManager.getLogger(TemplateStringListProperty.class);

	private int value;
	private int defaultValue;
	private ArrayList<String> possibleValues;
	
	public TemplateStringListProperty(Node propertyNode) throws TemplatePropertyParsingException {
		super(propertyNode);
		this.type = TemplatePropertyUtils.TYPE_STRING_LIST_NAME;
		this.value = Integer.parseInt(TemplatePropertyUtils.parseChildNode(propertyNode, TemplatePropertyUtils.VALUE_NODE_NAME));
		this.defaultValue = Integer.parseInt(TemplatePropertyUtils.parseChildNode(propertyNode, TemplatePropertyUtils.DEFAULT_VALUE_NODE_NAME));
		this.possibleValues = TemplatePropertyUtils.parseListNode(propertyNode, TemplatePropertyUtils.POSSIBLE_VALUES_NODE_NAME);
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
		int oldValue = this.value;
		this.value = value;
		this.propertyChangeSupport.firePropertyChange("value",oldValue,value);
	}

	public int getDefaultValue() {
		return defaultValue;
	}

	public ArrayList<String> getPossibleValues() {
		return possibleValues;
	}

	
}
