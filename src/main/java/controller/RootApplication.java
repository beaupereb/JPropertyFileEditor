package controller;

import command.CommandInvoker;
import command.LoadPropertiesFileCommand;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import properties.PropertiesListener;
import views.PropertiesScene;
import views.RootMenuBar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Starting point of the application. It's the main controller in the MVC pattern.
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class RootApplication extends Application {

	private static final Logger LOGGER = LogManager.getLogger(RootMenuBar.class);
	private static RootApplication instance;

	protected List<PropertiesListener> propertiesListeners = new ArrayList<>();

	private Stage primaryStage;
	private BorderPane borderPane = new BorderPane();
	private RootMenuBar rootToolBar = RootMenuBar.getInstance();
	private PropertiesScene propertiesScene;

	
	private Properties properties = new Properties();;
	private CommandInvoker commandInvoker = CommandInvoker.getInstance();
	private XmlPropertyFileManager propertiesXmlFileManager = new XmlPropertyFileManager();

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
		this.primaryStage.setTitle("DesignPatterns");
		this.borderPane.setTop(this.rootToolBar);
		this.propertiesScene = new PropertiesScene(borderPane, 700, 400);
		this.primaryStage.setScene(propertiesScene);
		this.primaryStage.show();
	}

	////////// CLASS METHODS //////////

	public void openFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("src/main/resources"));
		fileChooser.setInitialFileName("test.properties");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Properties Files", "*.properties"));
		File selectedFile = fileChooser.showOpenDialog(this.primaryStage);
		if(selectedFile != null) {
			commandInvoker.executeCommand(new LoadPropertiesFileCommand(selectedFile));
		}
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


	
	////////// GETTERS AND SETTERS //////////
	
	public List<PropertiesListener> getPropertiesListeners() {
		return this.propertiesListeners;
	}

	public XmlPropertyFileManager getPropertiesXmlFileManager() {
		return propertiesXmlFileManager;
	}

	public void setPropertiesXmlFileManager(XmlPropertyFileManager propertiesXmlFileManager) {
		this.propertiesXmlFileManager = propertiesXmlFileManager;
	}
	
}

