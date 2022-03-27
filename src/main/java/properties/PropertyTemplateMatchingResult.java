package properties;


import model.Property;
import model.templateproperty.TemplateProperty;

import java.util.ArrayList;

/**
 * //The result contains  3 lists.
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class PropertyTemplateMatchingResult {


    private ArrayList<TemplateProperty> templatePropertiesMissingInPropertiesFile = new ArrayList<>();
    private ArrayList<Property> propertiesMissingInTemplateFile = new ArrayList<>();
    private ArrayList<Property> propertiesInWrongSection = new ArrayList<>();
    private ArrayList<Property> propertiesWithWrongType = new ArrayList<>();

    ////////// GETTERS AND SETTERS //////////


    public ArrayList<TemplateProperty> getTemplatePropertiesMissingInPropertiesFile() {
        return templatePropertiesMissingInPropertiesFile;
    }

    public void setTemplatePropertiesMissingInPropertiesFile(ArrayList<TemplateProperty> templatePropertiesMissingInPropertiesFile) {
        this.templatePropertiesMissingInPropertiesFile = templatePropertiesMissingInPropertiesFile;
    }

    public ArrayList<Property> getPropertiesMissingInTemplateFile() {
        return propertiesMissingInTemplateFile;
    }

    public void setPropertiesMissingInTemplateFile(ArrayList<Property> propertiesMissingInTemplateFile) {
        this.propertiesMissingInTemplateFile = propertiesMissingInTemplateFile;
    }

    public ArrayList<Property> getPropertiesInWrongSection() {
        return propertiesInWrongSection;
    }

    public void setPropertiesInWrongSection(ArrayList<Property> propertiesInWrongSection) {
        this.propertiesInWrongSection = propertiesInWrongSection;
    }

    public ArrayList<Property> getPropertiesWithWrongType() {
        return propertiesWithWrongType;
    }

    public void setPropertiesWithWrongType(ArrayList<Property> propertiesWithWrongType) {
        this.propertiesWithWrongType = propertiesWithWrongType;
    }
}
