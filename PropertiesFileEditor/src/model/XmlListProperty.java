package model;

import java.util.List;

import org.w3c.dom.Node;

import properties.XmlPropertyParsingException;

public class XmlListProperty extends XmlProperty{

	
	private List<String> possibleValues;
	

	public XmlListProperty(Node propertyNode) throws XmlPropertyParsingException {
		super(propertyNode);
	}

	public List<String> getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(List<String> possibleValues) {
		this.possibleValues = possibleValues;
	}
	
	
}
