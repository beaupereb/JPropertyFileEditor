package properties;

import controller.PropertiesFileManager;
import model.Property;
import model.templateproperty.TemplateProperty;
import model.templateproperty.TemplatePropertyFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * //The purpose of this class is to check if the .properties file match with the template file.
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public abstract class PropertyTemplateMatchingChecker {

    private static final Logger LOGGER = LogManager.getLogger(PropertyTemplateMatchingChecker.class);

    ////////// CHECKING TEMPLATE METHODS //////////

    public static PropertyTemplateMatchingResult checkTemplateMatchPropertiesFile(ArrayList<Property> properties, TemplatePropertyFile templatePropertyFile) {
        PropertyTemplateMatchingResult result = new PropertyTemplateMatchingResult();
        for(Property property: properties) {
            String propertyName = property.getName();
            TemplateProperty templateProperty = templatePropertyFile.getTemplatePropertyByName(propertyName);
            if(templateProperty != null) {
                if(templateProperty.getSection().equals(property.getSection()) == false){
                    LOGGER.warn("Property in wrong section : " +propertyName);
                    result.getPropertiesInWrongSection().add(property);
                }
                if(templateProperty.getType().equals(property.getType()) == false) {
                    LOGGER.warn("Property with wrong type : " +propertyName);
                    result.getPropertiesWithWrongType().add(property);
                }
            } else {
                LOGGER.warn("Missing template property in template file");
                result.getPropertiesMissingInTemplateFile().add(property);
            }
        }

        for(TemplateProperty templateProperty : templatePropertyFile.getTemplateProperties()) {
            String templatePropertyName = templateProperty.getName();
            boolean found = false;
            for(Property property : properties) {
                if (property.getName().equals(templatePropertyName)) {
                    found = true;
                }
            }
            if (found == false) {
                LOGGER.warn("Missing property in properties file");
                result.getTemplatePropertiesMissingInPropertiesFile().add(templateProperty);
            }
        }
        return result;
    }
}
