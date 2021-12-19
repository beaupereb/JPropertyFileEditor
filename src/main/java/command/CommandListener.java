package command;

import java.util.EventListener;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public interface CommandListener extends EventListener{

	public void onCommandExecuteSuccessfull();
	public void onCommandExecuteError();
	
	public void onCommandUndoSuccessfull();
	public void onCommandUndoError();
}
