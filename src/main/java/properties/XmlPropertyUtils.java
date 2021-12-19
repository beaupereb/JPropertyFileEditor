package properties;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public abstract class XmlPropertyUtils {

	public static final String PROPERTY_NODE_NAME = "property";
	public static final String NAME_NODE_NAME = "name";
	public static final String VALUE_NODE_NAME = "value";
	public static final String DEFAULT_VALUE_NODE_NAME = "default";
	public static final String DESCRIPTION_NODE_NAME = "description";
	
	public static final String POSSIBLE_VALUES_NODE_NAME = "possibleValues";
	public static final String ITEM_NODE_NAME = "item";
	
	public static final String SECTION_ATTRIBUTE_NAME = "section";
	public static final String TYPE_ATTRIBUTE_NAME = "type";
	
	public static final String TYPE_STRING_NAME = "String";
	public static final String TYPE_INTEGER_NAME = "Integer";
	public static final String TYPE_FLOAT_NAME = "Float";
	public static final String TYPE_BOOLEAN_NAME = "Boolean";
	public static final String TYPE_STRING_LIST_NAME = "StringList";
	
	
	
	////////// PARSING METHODS //////////
	
	public static String parseChildNode(Node propertyNode, String wantedNodeName) {
		String result = "";
		NodeList nodeList = propertyNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childNode = nodeList.item(i);
			String childNodeName = childNode.getNodeName();
    		if(childNodeName.equals(wantedNodeName)) {
    			result = childNode.getFirstChild().getNodeValue();
    		}
		}
		return result;
	}
	
	public static String parseAttribute(Node propertyNode, String wantedAttributeName){
		String result = "";
		NamedNodeMap attributes = propertyNode.getAttributes();
		result = attributes.getNamedItem(wantedAttributeName).getNodeValue();
		return result;
	}
	
	public static ArrayList<String> parseListNode(Node node, String wantedListNodeName) {
		ArrayList<String> result = new ArrayList<>();
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
        	Node childNode = nodeList.item(i);
        	if (childNode.getNodeType() == Node.ELEMENT_NODE) {
        		String childNodeName = childNode.getNodeName();
        		if (childNodeName.equals(wantedListNodeName)) {
        			NodeList possibleValuesNodeList = childNode.getChildNodes();
        			for (int y = 0; y < possibleValuesNodeList.getLength(); y++) {
        				Node possibleValueNode = possibleValuesNodeList.item(y);
        				if (possibleValueNode.getNodeType() == Node.ELEMENT_NODE && possibleValueNode.getNodeName().equals(ITEM_NODE_NAME)) {
        					result.add((possibleValueNode.getFirstChild().getNodeValue()));
        				}
        			}
        		}
        	}
		}
		return result;
	}
	
}
