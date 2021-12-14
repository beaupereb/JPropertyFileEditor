package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import command.CommandInvoker;
import command.CommandListener;
import command.LoadPropertiesFileCommand;
import command.OpenFileChooserCommand;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import properties.PropertiesListener;
import views.RootMenuBar;
import views.PropertiesScene;

public class RootApplication extends Application {

	private static final Logger LOGGER = LogManager.getLogger(RootMenuBar.class);
	private static RootApplication instance;

	protected List<PropertiesListener> propertiesListeners = new ArrayList<>();

	private Stage primaryStage;
	private BorderPane borderPane = new BorderPane();
	private RootMenuBar rootToolBar = RootMenuBar.getInstance();
	private PropertiesScene propertiesScene;

	
	Properties properties = new Properties();;
	CommandInvoker commandInvoker = CommandInvoker.getInstance();
	XmlPropertyFileManager propertiesXmlFileManager = new XmlPropertyFileManager();

	//////////SINGLETON PATTERN //////////

	public RootApplication() {
		instance = this;
	}

	public static RootApplication getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("DesignPatherns");
		this.borderPane.setTop(this.rootToolBar);
		this.propertiesScene = new PropertiesScene(borderPane, 700, 400);
		this.primaryStage.setScene(propertiesScene);
		this.primaryStage.show();
	}

	////////// CLASS METHODS //////////

	public void openFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("D:\\Documents\\eclipse workspace\\PropertiesFileEditor\\src\\resources"));
		fileChooser.setInitialFileName("test.properties");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Properties Files", "*.properties"));
		File selectedFile = fileChooser.showOpenDialog(this.primaryStage);
		commandInvoker.executeCommand(new LoadPropertiesFileCommand(selectedFile));
	}

	public void loadPropertiesFile(File file) {
		try (InputStream inputStream = new FileInputStream(file)) {
            this.properties.load(inputStream);
            this.notifyPropertiesChanged();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	
	private void notifyPropertiesChanged() {
		for (PropertiesListener propertiesListener : this.propertiesListeners) {
			propertiesListener.onPropertiesChange(this.properties);
		}
	}

	public List<PropertiesListener> getPropertiesListeners() {
		return this.propertiesListeners;
	}
}

