package views;

import java.io.File;
import java.util.Properties;

import controller.RootApplication;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties.PropertiesListener;

public class PropertiesScene extends Scene implements PropertiesListener {

	private BorderPane borderPane;
	VBox vBox = new VBox();
	ScrollPane scrollPane = new ScrollPane(vBox);
	
	private Properties properties;
	
	
	public PropertiesScene(Parent root, double width, double height) {
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
		RootApplication.getInstance().getPropertiesListeners().add(this);
	}
	
	private void updateVIew() {
        Runnable runnable = () -> {
        	
        	properties.forEach(
  		          (key, value) -> {
  		        	  vBox.getChildren().add(new PropertyNode((String) key, (String) value));
  		          }
  		      );
		};
        Platform.runLater(runnable);
	}


	private void setProperties(Properties properties) {
		this.properties = properties;
		this.updateVIew();
	}

	@Override
	public void onPropertiesChange(Properties properties) {
		this.setProperties(properties);
	}
	
	

}
