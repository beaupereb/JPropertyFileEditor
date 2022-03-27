package model.templateproperty;

import org.w3c.dom.Node;
import properties.TemplatePropertyParsingException;
import properties.TemplatePropertyUtils;



/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public abstract class TemplateProperty implements Comparable {


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
		stringBuilder.append("{Default value=" +this.getStringDefaultValue() +"}");
		stringBuilder.append("{Section=" +this.getSection() +"}");
		stringBuilder.append("{Description=" +this.getDescription() +"}");
		return stringBuilder.toString();
	}


	
	////////// GETTERS AND SETTERS //////////

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
