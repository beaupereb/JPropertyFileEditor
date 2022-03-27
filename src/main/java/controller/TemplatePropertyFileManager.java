package controller;

import javafx.stage.FileChooser;
import model.templateproperty.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import properties.TemplatePropertyParsingException;
import properties.TemplatePropertyUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class TemplatePropertyFileManager {
	
	private static final Logger LOGGER = LogManager.getLogger(TemplatePropertyFileManager.class);
	
	private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder documentBuilder;
	private Document document;
	
	private TemplatePropertyFile templatePropertyFile;

	
	public TemplatePropertyFileManager() {
		LOGGER.info("Object created");
	}
	
	private void parseTemplatePropertiesFile(File file) {
		LOGGER.info("Parsing " +file);
		try {
			this.templatePropertyFile = new TemplatePropertyFile();
			ArrayList<TemplateProperty> newTemplateProperties = new ArrayList<>();
			documentBuilder = factory.newDocumentBuilder();
			this.document =  documentBuilder.parse(file);
			Node properties = document.getFirstChild();
			// loop the supercar child node
	        NodeList nodeList = properties.getChildNodes();
	        for (int i = 0; i < nodeList.getLength(); i++) {
	        	Node propertyNode = nodeList.item(i);
				//TODO gerer le cas ou on essais d'ajouter deux properties avec le meme nom
	        	if (propertyNode.getNodeType() == Node.ELEMENT_NODE) {
	        		String nodeName = propertyNode.getNodeName();
	        		if(nodeName.equals(TemplatePropertyUtils.PROPERTY_NODE_NAME)) {
	        			String propertyType = TemplatePropertyUtils.parseAttribute(propertyNode, TemplatePropertyUtils.TYPE_ATTRIBUTE_NAME);
	        			if(propertyType.equals(TemplatePropertyUtils.TYPE_STRING_NAME)) {
							newTemplateProperties.add(new TemplateStringProperty(propertyNode));
	        			} else if (propertyType.equals(TemplatePropertyUtils.TYPE_INTEGER_NAME)) {
							newTemplateProperties.add(new TemplateIntegerProperty(propertyNode));
	        			} else if (propertyType.equals(TemplatePropertyUtils.TYPE_FLOAT_NAME)) {
							newTemplateProperties.add(new TemplateFloatProperty(propertyNode));
	        			} else if (propertyType.equals(TemplatePropertyUtils.TYPE_BOOLEAN_NAME)) {
							newTemplateProperties.add(new TemplateBooleanProperty(propertyNode));
	        			} else if (propertyType.equals(TemplatePropertyUtils.TYPE_STRING_LIST_NAME)) {
	        				TemplateProperty newProp = new TemplateStringListProperty(propertyNode);
							newTemplateProperties.add(newProp);
	        			}
	        		} 
	        	}
	        }
			this.templatePropertyFile.setTemplateProperties(newTemplateProperties);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TemplatePropertyParsingException e) {
			e.printStackTrace();
		}	
	}

	public void openTemplatePropertyFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("src/main/resources"));
		fileChooser.setInitialFileName("templateProperties.xml");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
		File selectedFile = fileChooser.showOpenDialog(RootApplication.getInstance().getPrimaryStage());
		if(selectedFile != null) {
			LOGGER.info("Template property file open : selectedFile = " +selectedFile);
			this.parseTemplatePropertiesFile(selectedFile);
			RootApplication.getInstance().getTemplatePropertyFileScene().setTemplatePropertyFile(this.templatePropertyFile);
		}
	}
	
	////////// GETTERS AND SETTERS //////////
	
	public TemplatePropertyFile getTemplatePropertyFile() {
		return this.templatePropertyFile;
	}


}
