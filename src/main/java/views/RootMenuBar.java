package views;

import command.CommandInvoker;
import command.GeneratePropertyFileCommand;
import command.OpenPropertiesFileCommand;
import command.OpenTemplateFileCommand;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class RootMenuBar extends MenuBar{
	
	private static final Logger LOGGER = LogManager.getLogger(RootMenuBar.class);
	private static RootMenuBar instance;
	
	CommandInvoker commandInvoker = CommandInvoker.getInstance();
	
    // Create menus
    Menu fileMenu = new Menu("File");
    Menu editMenu = new Menu("Edit");
    Menu toolsMenu = new Menu("Tools");
    Menu helpMenu = new Menu("Help");
    
    // Create MenuItems
    MenuItem newItem = new MenuItem("New");
    MenuItem openPropertiesFileItem = new MenuItem("Open properties file");
	MenuItem openTemplateFileItem = new MenuItem("Open template file");
    MenuItem exitItem = new MenuItem("Exit");
   
    MenuItem copyItem = new MenuItem("Copy");
    MenuItem pasteItem = new MenuItem("Paste");
    
    MenuItem generatePropertyFileItem = new MenuItem("Generate property file");

	
	//////////SINGLETON PATTERN //////////
		
	private RootMenuBar() {
		LOGGER.info("Create singleton");
		
		 this.fileMenu.getItems().addAll(this.newItem, this.openPropertiesFileItem, this.openTemplateFileItem ,this.exitItem);
		 this.editMenu.getItems().addAll(this.copyItem, this.pasteItem);
		 this.toolsMenu.getItems().addAll(this.generatePropertyFileItem);
		 this.getMenus().addAll(this.fileMenu, this.editMenu, this.toolsMenu, this.helpMenu);
	       
		

		 openPropertiesFileItem.setOnAction(actionEvent -> {
			commandInvoker.executeCommand(new OpenPropertiesFileCommand());
		});

		openTemplateFileItem.setOnAction(actionEvent -> {
			commandInvoker.executeCommand(new OpenTemplateFileCommand());
		});
		 
		 generatePropertyFileItem.setOnAction(actionEvent -> {
				commandInvoker.executeCommand(new GeneratePropertyFileCommand());
			});
	}
	
	public static RootMenuBar getInstance() {
		if (instance == null) {
	      instance = new RootMenuBar();
	  }
	  return instance;
	}

}
