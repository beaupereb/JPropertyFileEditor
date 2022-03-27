package controller;

import javafx.stage.FileChooser;
import model.Property;
import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class PropertiesFileManager {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesFileManager.class);

    LinkedList<Property> properties = new LinkedList<>();

    ////////// CLASS METHODS //////////

    public void openPropertiesFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src/main/resources"));
        fileChooser.setInitialFileName("test.properties");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Properties Files", "*.properties"));
        File selectedFile = fileChooser.showOpenDialog(RootApplication.getInstance().getPrimaryStage());
        if(selectedFile != null) {
            LOGGER.info("Template property file open : selectedFile = " +selectedFile);
            this.parsePropertiesFile(selectedFile);
            //RootApplication.getInstance().getTemplatePropertyFileScene().setTemplatePropertyFile(this.templatePropertyFile);
        }
    }

    private void parsePropertiesFile(File file) {
        LOGGER.info("Parsing " +file);
        this.properties = new LinkedList<>();
        INIConfiguration iniConfiguration = new INIConfiguration();
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            BufferedReader reader=new BufferedReader(fileReader);
            iniConfiguration.read(reader);
            Set<String> sectionNames = iniConfiguration.getSections();
            for (String sectionName : sectionNames) {
                SubnodeConfiguration section = iniConfiguration.getSection(sectionName);
                if (section != null) {
                    LOGGER.debug("Parsing section : " +sectionName);
                    Iterator<String> keys = section.getKeys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        String value = section.getString(key);
                        if (value != null) {
                            LOGGER.debug("New property : " +key +"=" +value);
                           this.properties.add(new Property(key, value, sectionName));
                        }

                    }
                }
            }
        } catch (ConfigurationException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////////// GETTERS AND SETTERS //////////

    public LinkedList<Property> getProperties() {
        return properties;
    }

    public void setProperties(LinkedList<Property> properties) {
        this.properties = properties;
    }

    public Property getPropertyByName(String name) {
        Property result = null;
        for(Property property : this.properties) {
            if (property.getName().equals(name)) {
                result = property;
            }
        }
        return result;
    }
}
