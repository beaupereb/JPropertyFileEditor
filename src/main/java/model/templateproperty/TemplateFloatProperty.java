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
public class TemplateFloatProperty extends TemplateProperty {
	
	private static final Logger LOGGER = LogManager.getLogger(TemplateFloatProperty.class);

	private float defaultValue;

	public TemplateFloatProperty(Node propertyNode) throws TemplatePropertyParsingException {
		super(propertyNode);
		this.type = TemplatePropertyUtils.TYPE_FLOAT_NAME;
		this.defaultValue = Float.parseFloat(TemplatePropertyUtils.parseChildNode(propertyNode, TemplatePropertyUtils.DEFAULT_VALUE_NODE_NAME));
		LOGGER.info("Property parsed with : " +this.toString());
	}

	
	////////// OVERRIDE METHODS //////////

	@Override
	public String getStringDefaultValue() {
		return "" +this.getDefaultValue();
	}

	//////////GETTERS AND SETTERS //////////


	public Float getDefaultValue() {
		return defaultValue;
	}
	
	
	public void setDefaultValue(Float defaultValue) {
		this.defaultValue = defaultValue;
	}
}
