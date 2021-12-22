package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class XmlPropertyFile {

    protected PropertyChangeSupport propertyChangeSupport;

    private ArrayList<XmlProperty> xmlProperties = new ArrayList<>();

    public XmlPropertyFile () {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public ArrayList<XmlProperty> getXmlProperties() {
        return xmlProperties;
    }

    public void setXmlProperties(ArrayList<XmlProperty> xmlProperties) {
        ArrayList<XmlProperty> oldValue = this.xmlProperties;
        this.xmlProperties = xmlProperties;
        this.propertyChangeSupport.firePropertyChange("xmlProperties", oldValue, xmlProperties);
    }
}
