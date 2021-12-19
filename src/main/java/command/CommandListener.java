package command;

import java.util.EventListener;

public interface CommandListener extends EventListener{

	public void onCommandExecuteSuccessfull();
	public void onCommandExecuteError();
	
	public void onCommandUndoSuccessfull();
	public void onCommandUndoError();
}
