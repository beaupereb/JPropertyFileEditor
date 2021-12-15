package model;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;

import properties.XmlPropertyParsingException;
import properties.XmlPropertyUtils;

public class XmlStringListProperty extends XmlListProperty{
	
	private static final Logger LOGGER = LogManager.getLogger(XmlStringListProperty.class);

	private int value;
	private int defaultValue;
	private ArrayList<String> possibleValues = new ArrayList<>();
	
	public XmlStringListProperty(Node propertyNode) throws XmlPropertyParsingException {
		super(propertyNode);
		this.value = Integer.parseInt(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.VALUE_NODE_NAME));
		this.defaultValue = Integer.parseInt(XmlPropertyUtils.parseChildNode(propertyNode, XmlPropertyUtils.DEFAULT_VALUE_NODE_NAME));
		this.possibleValues = XmlPropertyUtils.parseListNode(propertyNode, XmlPropertyUtils.POSSIBLE_VALUES_NODE_NAME);
		
		LOGGER.info("Property parsed with : " +this.toString());
	}
	
	//////////OVERRIDE METHODS //////////
		
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{Type=" +XmlPropertyUtils.TYPE_STRING_NAME +"}");
		stringBuilder.append("{Name=" +this.getName() +"}");
		stringBuilder.append("{Value=" +this.possibleValues.get(this.getValue()) +"}");
		stringBuilder.append("{Default value=" +this.possibleValues.get(this.getDefaultValue()) +"}");
		stringBuilder.append("{Group=" +this.getGroup() +"}");
		stringBuilder.append("{Description=" +this.getDescription() +"}");
		stringBuilder.append("{Possible values=" +this.getDescription());
		possibleValues.forEach(e -> stringBuilder.append(e+" ;"));
		stringBuilder.append( "}");
		return stringBuilder.toString();
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
