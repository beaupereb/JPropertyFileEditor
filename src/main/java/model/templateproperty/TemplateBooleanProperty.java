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
public class TemplateBooleanProperty extends TemplateProperty {
	
	private static final Logger LOGGER = LogManager.getLogger(TemplateBooleanProperty.class);

	private boolean defaultValue;

	public TemplateBooleanProperty(Node propertyNode) throws TemplatePropertyParsingException {
		super(propertyNode);
		this.type = TemplatePropertyUtils.TYPE_BOOLEAN_NAME;
		this.defaultValue = Boolean.parseBoolean(TemplatePropertyUtils.parseChildNode(propertyNode, TemplatePropertyUtils.DEFAULT_VALUE_NODE_NAME));
		LOGGER.info("Property parsed with : " +this.toString());
	}

	////////// OVERRIDE METHODS //////////

	@Override
	public String getStringDefaultValue() {
		return "" +this.getDefaultValue();
	}
	
	//////////GETTERS AND SETTERS //////////
	
	public Boolean getDefaultValue() {
		return defaultValue;
	}
	
	
	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

}
