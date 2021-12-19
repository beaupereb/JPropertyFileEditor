package model;

import org.w3c.dom.Node;
import properties.XmlPropertyParsingException;

import java.util.List;


/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public abstract class XmlListProperty extends XmlProperty{

	
	private List<String> possibleValues;
	

	public XmlListProperty(Node propertyNode) throws XmlPropertyParsingException {
		super(propertyNode);
	}



	//////////OVERRIDE METHODS //////////

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(super.toString());
		stringBuilder.append( "{");
		this.getPossibleValues().forEach(e -> stringBuilder.append(e+" ;"));
		stringBuilder.append( "}");
		return stringBuilder.toString();
	}

	////////// GETTERS AND SETTERS //////////

	public List<String> getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(List<String> possibleValues) {
		this.possibleValues = possibleValues;
	}
	
}
