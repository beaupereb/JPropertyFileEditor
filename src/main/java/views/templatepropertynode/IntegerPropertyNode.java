package views.templatepropertynode;

import javafx.scene.control.TextField;
import model.templateproperty.TemplateIntegerProperty;

public class IntegerPropertyNode extends PropertyNode {

    TextField newValueTextField = new TextField();

    public IntegerPropertyNode(TemplateIntegerProperty templateIntegerProperty) {
        super(templateIntegerProperty);
        this.add(newValueTextField, 1, 2, 1, 1);

        //Making all the columns the same width
        for (int i = 0; i < 4; i++) {
            this.getColumnConstraints().add(cc);
        }
    }
}
