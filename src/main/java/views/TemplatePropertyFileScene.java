package views;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import properties.TemplatePropertyUtils;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class TemplatePropertyFileScene extends Scene implements PropertyChangeListener {

	private static final Logger LOGGER = LogManager.getLogger(TemplatePropertyFileScene.class);

	private BorderPane borderPane;
	VBox vBox = new VBox();
	ScrollPane scrollPane = new ScrollPane(vBox);
	
	private TemplatePropertyFile templatePropertyFile;
	
	
	public TemplatePropertyFileScene(Parent root, double width, double height) {
		super(root, width, height);
		LOGGER.info("Object created");
		scrollPane.pannableProperty().set(true);
		scrollPane.fitToWidthProperty().set(true);
		scrollPane.fitToHeightProperty().set(true);
		scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);

		
		this.borderPane = (BorderPane) root;
		borderPane.setCenter(scrollPane);
		File cssFile = new File("src/main/resources/DarkTheme.css");
		this.getStylesheets().clear();
		this.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));

	}
	
	private void updateView() {
        Runnable runnable = () -> {

			templatePropertyFile.getTemplateProperties().forEach(
  		          (templateProperty) -> {
					  LOGGER.debug(templateProperty.getType());
					  if (templateProperty.getType() == TemplatePropertyUtils.TYPE_BOOLEAN_NAME) {
						  vBox.getChildren().add(new TemplateBooleanPropertyNode((TemplateBooleanProperty) templateProperty));
					  } else if (templateProperty.getType() == TemplatePropertyUtils.TYPE_INTEGER_NAME) {
						  vBox.getChildren().add(new TemplateIntegerPropertyNode((TemplateIntegerProperty) templateProperty));
					  } else if (templateProperty.getType() == TemplatePropertyUtils.TYPE_FLOAT_NAME) {
						  vBox.getChildren().add(new TemplateFloatPropertyNode((TemplateFloatProperty) templateProperty));
					  } else if (templateProperty.getType() == TemplatePropertyUtils.TYPE_STRING_NAME) {
						  vBox.getChildren().add(new TemplateStringPropertyNode((TemplateStringProperty) templateProperty));
					  } else if (templateProperty.getType() == TemplatePropertyUtils.TYPE_STRING_LIST_NAME) {
						  vBox.getChildren().add(new TemplateStringListPropertyNode((TemplateStringListProperty) templateProperty));
					  } else {
						  LOGGER.error("Invalid property type");
					  }
  		          }
  		      );
		};
        Platform.runLater(runnable);
	}


	public void setTemplatePropertyFile(TemplatePropertyFile templatePropertyFile) {
		LOGGER.info("setTemplatePropertyFile");
		this.templatePropertyFile = templatePropertyFile;
		this.templatePropertyFile.addPropertyChangeListener(this);
		this.updateView();
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		LOGGER.info("propertyChange");
		this.updateView();
	}
}
