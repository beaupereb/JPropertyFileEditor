package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TemplatePropertyFile {

    protected PropertyChangeSupport propertyChangeSupport;

    private ArrayList<TemplateProperty> templateProperties = new ArrayList<>();

    public TemplatePropertyFile() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public ArrayList<TemplateProperty> getTemplateProperties() {
        return templateProperties;
    }

    public void setTemplateProperties(ArrayList<TemplateProperty> templateProperties) {
        ArrayList<TemplateProperty> oldValue = this.templateProperties;
        this.templateProperties = templateProperties;
        this.propertyChangeSupport.firePropertyChange("templateProperties", oldValue, templateProperties);
    }
}
