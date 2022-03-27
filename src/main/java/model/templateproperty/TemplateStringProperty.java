package model.templateproperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class TemplateStringProperty extends TemplateProperty {

	private static final Logger LOGGER = LogManager.getLogger(TemplateStringProperty.class);

	private String defaultValue;
	
	
	public TemplateStringProperty(Node propertyNode) throws TemplatePropertyParsingException {
		super(propertyNode);
		this.type = TemplatePropertyUtils.TYPE_STRING_NAME;
		this.defaultValue = TemplatePropertyUtils.parseChildNode(propertyNode, TemplatePropertyUtils.DEFAULT_VALUE_NODE_NAME);
		LOGGER.info("Property parsed with : " +this.toString());
	}
	
	
	
	
	////////// OVERRIDE METHODS //////////

	@Override
	public String getStringDefaultValue() {
		return this.getDefaultValue();
	}

	//////////GETTERS AND SETTERS //////////


	public String getDefaultValue() {
		return defaultValue;
	}


	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
