package command;

import java.util.ArrayList;
import java.util.List;

/**
 * The command  interface implements the "Command" design pattern.
 * For more information about this pattern, see : https://refactoring.guru/design-patterns/command.
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public abstract class Command {

	/**
	 *
	 */
	protected List<CommandListener> listeners = new ArrayList<>();

	public abstract void execute();
	public abstract void undo();

	/**
	 *
	 */
	protected void notifyCommandExecuteSuccessfull() {
		for(CommandListener commandListener : this.listeners) {
			commandListener.onCommandExecuteSuccessfull();
		}	
	}

	protected void notifyCommandExecuteError() {
		for(CommandListener commandListener : this.listeners) {
			commandListener.onCommandExecuteError();
		}
	}

	protected void notifyCommandUndoSuccessfull() {
		for(CommandListener commandListener : this.listeners) {
			commandListener.onCommandUndoSuccessfull();
		}
	}

	protected void notifyCommandUndoError() {
		for(CommandListener commandListener : this.listeners) {
			commandListener.onCommandUndoError();
		}
	}
	
	public void addListener(CommandListener commandListener) {
		this.listeners.add(commandListener);
	}
	
	public void removeListener(CommandListener commandListener) {
		if (this.listeners.contains(commandListener)) {
			this.listeners.remove(commandListener);
		}
	}
}
