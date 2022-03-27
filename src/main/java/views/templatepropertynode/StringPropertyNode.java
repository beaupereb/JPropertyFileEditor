package views.templatepropertynode;

import javafx.scene.control.TextField;
import model.templateproperty.TemplateStringProperty;

public class StringPropertyNode extends PropertyNode {

    TextField newValueTextField = new TextField();

    public StringPropertyNode(TemplateStringProperty templateStringProperty) {
        super(templateStringProperty);
        this.add(newValueTextField, 1, 2, 1, 1);

        //Making all the columns the same width
        for (int i = 0; i < 4; i++) {
            this.getColumnConstraints().add(cc);
        }
    }
}
