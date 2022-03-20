package views;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.TemplateBooleanProperty;
import model.TemplateProperty;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class TemplateBooleanPropertyNode extends TemplatePropertyNode {

	ComboBox<String> newValueComboBox = new ComboBox<String>();

	public TemplateBooleanPropertyNode(TemplateBooleanProperty templateBooleanProperty) {
		super(templateBooleanProperty);
		newValueComboBox.getItems().add("True");
		newValueComboBox.getItems().add("False");
		newValueComboBox.getSelectionModel().selectFirst();
		this.add(newValueComboBox, 1, 2, 1, 1);
	}

}
