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
public class TemplateIntegerProperty extends TemplateProperty {
	
	private static final Logger LOGGER = LogManager.getLogger(TemplateIntegerProperty.class);

	private int defaultValue;

	public TemplateIntegerProperty(Node propertyNode) throws TemplatePropertyParsingException {
		super(propertyNode);
		this.type = TemplatePropertyUtils.TYPE_INTEGER_NAME;
		this.defaultValue = Integer.parseInt(TemplatePropertyUtils.parseChildNode(propertyNode, TemplatePropertyUtils.DEFAULT_VALUE_NODE_NAME));
		LOGGER.info("Property parsed with : " +this.toString());
	}
	
	////////// OVERRIDE METHODS //////////

	@Override
	public String getStringDefaultValue() {
		return "" +this.getDefaultValue();
	}
	
	//////////GETTERS AND SETTERS //////////
	
	public Integer getDefaultValue() {
		return defaultValue;
	}
	
	
	public void setDefaultValue(Integer defaultValue) {
		this.defaultValue = defaultValue;
	}

}
