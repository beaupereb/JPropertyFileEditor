package model;

import controller.PropertiesFileManager;

public class Property {

    private String name;
    private String value;
    private String section;
    private String type;

    public Property(String name, String value, String section) {
        this.name = name;
        this.value = value;
        this.section = section;

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
