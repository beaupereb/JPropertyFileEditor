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
import views.TemplatePropertyFileScene;
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
	private TemplatePropertyFileScene templatePropertyFileScene;

	
	private Properties properties = new Properties();;
	private CommandInvoker commandInvoker = CommandInvoker.getInstance();
	private TemplatePropertyFileManager templatePropertyFileManager;

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
		LOGGER.info("start");
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Property file editor");
		this.borderPane.setTop(this.rootToolBar);
		this.templatePropertyFileScene = new TemplatePropertyFileScene(borderPane, 700, 400);
		this.primaryStage.setScene(templatePropertyFileScene);
		this.primaryStage.show();
		this.templatePropertyFileManager = new TemplatePropertyFileManager();
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

	public TemplatePropertyFileManager getTemplatePropertyFileManager() {
		return templatePropertyFileManager;
	}

	public TemplatePropertyFileScene getTemplatePropertyFileScene() {
		return templatePropertyFileScene;
	}
}

