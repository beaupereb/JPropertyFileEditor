package views.templatepropertynode;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import model.templateproperty.TemplateProperty;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public abstract class PropertyNode extends GridPane {

	RowConstraints rc = new RowConstraints();
	ColumnConstraints cc = new ColumnConstraints();

	Button buttonValidate = new Button("Validate");
	Button buttonCancel = new Button("Cancel");

	public PropertyNode(TemplateProperty templateProperty) {
		//this.setGridLinesVisible(true);
		cc.setPercentWidth(100d / 4); //because we have 4 columns
		this.setPadding(new Insets(20));
		this.setHgap(25);
		this.setVgap(15);

		this.add(new Label("Property name : "), 0, 0, 1, 1);
		this.add(new Label(templateProperty.getName()), 1, 0, 1, 1);
		this.add(new Label("Type : " + templateProperty.getType()), 2, 0, 1, 1);
		this.add(new Label("Section : " + templateProperty.getSection()), 3, 0, 1, 1);
		this.add(new Label("Current value : "), 0, 1, 1, 1);
		this.add(new Label("Put the value here"), 1, 1, 1, 1);
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
