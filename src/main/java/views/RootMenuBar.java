package views;

import command.CommandInvoker;
import command.GeneratePropertyFileCommand;
import command.OpenFileChooserCommand;
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
    MenuItem openFileItem = new MenuItem("Open File");
    MenuItem exitItem = new MenuItem("Exit");
   
    MenuItem copyItem = new MenuItem("Copy");
    MenuItem pasteItem = new MenuItem("Paste");
    
    MenuItem generatePropertyFileItem = new MenuItem("Generate property file");

	
	//////////SINGLETON PATTERN //////////
		
	private RootMenuBar() {
		LOGGER.info("Create object");
		
		 this.fileMenu.getItems().addAll(this.newItem, this.openFileItem, this.exitItem);
		 this.editMenu.getItems().addAll(this.copyItem, this.pasteItem);
		 this.toolsMenu.getItems().addAll(this.generatePropertyFileItem);
		 this.getMenus().addAll(this.fileMenu, this.editMenu, this.toolsMenu, this.helpMenu);
	       
		

		 openFileItem.setOnAction(actionEvent -> {
			commandInvoker.executeCommand(new OpenFileChooserCommand());
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
