package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;

import properties.XmlPropertyParsingException;

public class XmlStringListProperty extends XmlListProperty{
	
	private static final Logger LOGGER = LogManager.getLogger(XmlStringListProperty.class);

	public XmlStringListProperty(Node propertyNode) throws XmlPropertyParsingException {
		super(propertyNode);

	}

}
