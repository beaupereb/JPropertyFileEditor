package command;

import java.util.ArrayList;
import java.util.Stack;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;






public class CommandInvoker {
	
	private static final Logger LOGGER = LogManager.getLogger(CommandInvoker.class);
	private static CommandInvoker instance;
	
	private CommandHistoryManager commandHistoryManager = CommandHistoryManager.getInstance();
	
	
	//////////SINGLETON PATTERN //////////
	
	private CommandInvoker() {

	}
	
	public static CommandInvoker getInstance() {
		if (instance == null) {
            instance = new CommandInvoker();
        }
        return instance;
	}
	
	////////// CLASS METHODS //////////
	
	public void executeCommand(Command command) {
		LOGGER.info("Execute :" +command.getClass().getName());
		command.execute();
		this.addToHistory(command);
	}
	
	public void undoLastCommand() {
		Command commandToUndo = this.commandHistoryManager.getLastCommand();
		if(commandToUndo != null) {
			System.out.println("Undo :" +commandToUndo.getClass().getName());
			commandToUndo.undo();
			this.commandHistoryManager.removeLastCommand();	
		} else {
			System.out.println("History is empty");
		}

	}
	
	private void addToHistory(Command command) {
		this.commandHistoryManager.addCommand(command);
	}
	

	
}
