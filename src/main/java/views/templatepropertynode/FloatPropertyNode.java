package views.templatepropertynode;

import javafx.scene.control.TextField;
import model.templateproperty.TemplateFloatProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FloatPropertyNode extends PropertyNode implements PropertyChangeListener {

    TextField newValueTextField = new TextField();

    public FloatPropertyNode(TemplateFloatProperty templateFloatProperty) {
        super(templateFloatProperty);
        this.add(newValueTextField, 1, 2, 1, 1);

        //Making all the columns the same width
        for (int i = 0; i < 4; i++) {
            this.getColumnConstraints().add(cc);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
