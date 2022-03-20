package views;

import command.CommandInvoker;
import command.OpenFileChooserCommand;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.TemplateProperty;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public abstract class TemplatePropertyNode extends GridPane {

	Button buttonValidate = new Button("Validate");
	Button buttonCancel = new Button("Cancel");

	public TemplatePropertyNode(TemplateProperty templateProperty) {
		this.setPadding(new Insets(20));
		this.setHgap(25);
		this.setVgap(15);

		this.add(new Label("Property name : "), 0, 0, 1, 1);
		this.add(new Label(templateProperty.getName()), 1, 0, 1, 1);
		this.add(new Label("Type : "), 2, 0, 1, 1);
		this.add(new Label(templateProperty.getType()), 3, 0, 1, 1);
		this.add(new Label("Current value : "), 0, 1, 1, 1);
		this.add(new Label(templateProperty.getStringValue()), 1, 1, 1, 1);
		this.add(new Label("Change value to : "), 0, 2, 1, 1);
		this.add(buttonValidate, 2, 3, 1, 1);
		this.add(buttonCancel, 3, 3, 1, 1);


		buttonValidate.setOnAction(actionEvent -> {
			//CommandInvoker.getInstance().executeCommand();
		});

		buttonCancel.setOnAction(actionEvent -> {
			//CommandInvoker.getInstance().executeCommand();
		});
	}



}
