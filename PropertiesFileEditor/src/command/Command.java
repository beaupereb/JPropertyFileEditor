package command;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

	protected List<CommandListener> listeners = new ArrayList<>();

	public abstract void execute();
	public abstract void undo();
	
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
