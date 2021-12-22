package views;

import controller.RootApplication;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.XmlPropertyFile;
import properties.PropertiesListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Properties;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class XmlPropertyFileScene extends Scene implements PropertyChangeListener {

	private BorderPane borderPane;
	VBox vBox = new VBox();
	ScrollPane scrollPane = new ScrollPane(vBox);
	
	private XmlPropertyFile xmlPropertyFile;
	
	
	public XmlPropertyFileScene(Parent root, double width, double height) {
		super(root, width, height);
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

			xmlPropertyFile.getXmlProperties().forEach(
  		          (xmlProperty) -> {
  		        	  vBox.getChildren().add(new PropertyNode(xmlProperty));
  		          }
  		      );
		};
        Platform.runLater(runnable);
	}


	private void setXmlPropertyFile(XmlPropertyFile xmlPropertyFile) {
		this.xmlPropertyFile = xmlPropertyFile;
		this.xmlPropertyFile.addPropertyChangeListener(this);
		this.updateView();
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.updateView();
	}
}
