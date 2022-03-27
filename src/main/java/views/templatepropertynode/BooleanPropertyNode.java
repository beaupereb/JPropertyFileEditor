package views.templatepropertynode;

import javafx.scene.control.ComboBox;
import model.templateproperty.TemplateBooleanProperty;
import views.utils.StringConstraints;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class BooleanPropertyNode extends PropertyNode {

	ComboBox<String> newValueComboBox = new ComboBox<String>();

	public BooleanPropertyNode(TemplateBooleanProperty templateBooleanProperty) {
		super(templateBooleanProperty);
		newValueComboBox.getItems().add(StringConstraints.BOOLEAN_TRUE);
		newValueComboBox.getItems().add(StringConstraints.BOOLEAN_FALSE);
		newValueComboBox.getSelectionModel().selectFirst();
		this.add(newValueComboBox, 1, 2, 1, 1);

		//Making all the columns the same width
		for (int i = 0; i < 4; i++) {
			this.getColumnConstraints().add(cc);
		}
	}

	public void setProperty() {

	}

}
