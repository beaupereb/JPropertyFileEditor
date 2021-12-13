package model;

import java.util.List;

public class XmlListProperty<T> extends XmlProperty{

	
	private List<T> possibleValues;
	
	public XmlListProperty(String name, Object defaultValue, Object value, List<T> possibleValues) {
		super(name, defaultValue, value);
		this.possibleValues = possibleValues;
	}

	

	public List<T> getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(List<T> possibleValues) {
		this.possibleValues = possibleValues;
	}
	
	
}
