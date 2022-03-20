package model;

import org.w3c.dom.Node;
import properties.TemplatePropertyParsingException;
import properties.TemplatePropertyUtils;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public abstract class TemplateProperty implements Comparable {

	protected PropertyChangeSupport propertyChangeSupport;

	protected String type;
	protected String name;
	protected String description;
	protected String section;
	
	public TemplateProperty(Node propertyNode) throws TemplatePropertyParsingException {
		
		this.name = TemplatePropertyUtils.parseChildNode(propertyNode, TemplatePropertyUtils.NAME_NODE_NAME);
		if (this.name.isBlank()) {
			throw new TemplatePropertyParsingException();
		}
		this.description = TemplatePropertyUtils.parseChildNode(propertyNode, TemplatePropertyUtils.DESCRIPTION_NODE_NAME);
		this.section = TemplatePropertyUtils.parseAttribute(propertyNode, TemplatePropertyUtils.SECTION_ATTRIBUTE_NAME);

		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	

	////////// OVERRIDE METHODS //////////
	
	@Override
	public int compareTo(Object o) {
		return 0;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{Type=" +this.getType()+"}");
		stringBuilder.append("{Name=" +this.getName() +"}");
		stringBuilder.append("{Value=" +this.getStringValue() +"}");
		stringBuilder.append("{Default value=" +this.getStringDefaultValue() +"}");
		stringBuilder.append("{Section=" +this.getSection() +"}");
		stringBuilder.append("{Description=" +this.getDescription() +"}");
		return stringBuilder.toString();
	}

	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	////////// GETTERS AND SETTERS //////////

	public abstract String getStringValue();
	public abstract String getStringDefaultValue();
	
	public String getName() {
		return name;
	}

	public String getSection() {
		return section;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}
}
