package controller;

import command.CommandInvoker;
import command.CommandListener;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import evolvedproperties.EvolvedProperties;
import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.TemplatePropertyFileScene;
import views.RootMenuBar;

import java.io.*;
import java.util.*;


/**
 * Starting point of the application. It's the main controller in the MVC pattern.
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class RootApplication extends Application implements CommandListener {

	EvolvedProperties propTest = new EvolvedProperties();

	private static final Logger LOGGER = LogManager.getLogger(RootApplication.class);
	private static RootApplication instance;

	private Stage primaryStage;
	private BorderPane borderPane = new BorderPane();
	private RootMenuBar rootMenuBar;
	private TemplatePropertyFileScene templatePropertyFileScene;


	private CommandInvoker commandInvoker = CommandInvoker.getInstance();
	private TemplatePropertyFileManager templatePropertyFileManager;
	private PropertiesFileManager propertiesFileManager;

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
		LOGGER.info("START");
		this.rootMenuBar = RootMenuBar.getInstance();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Property file editor");
		this.borderPane.setTop(this.rootMenuBar);
		this.templatePropertyFileScene = new TemplatePropertyFileScene(borderPane, 700, 400);
		this.primaryStage.setScene(templatePropertyFileScene);
		this.primaryStage.show();
		this.templatePropertyFileManager = new TemplatePropertyFileManager();
		this.propertiesFileManager = new PropertiesFileManager();
		LOGGER.info("INIT OK");
	}

	////////// CLASS METHODS //////////


	
	private void notifyPropertiesChanged() {

	}

	////////// INTERFACES OVERRIDE //////////

	@Override
	public void onCommandExecuteSuccessfull() {

	}

	@Override
	public void onCommandExecuteError() {

	}

	@Override
	public void onCommandUndoSuccessfull() {

	}

	@Override
	public void onCommandUndoError() {

	}


	
	////////// GETTERS AND SETTERS //////////

	public TemplatePropertyFileManager getTemplatePropertyFileManager() {
		return templatePropertyFileManager;
	}

	public TemplatePropertyFileScene getTemplatePropertyFileScene() {
		return templatePropertyFileScene;
	}

	public PropertiesFileManager getPropertiesFileManager() {
		return propertiesFileManager;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
}

