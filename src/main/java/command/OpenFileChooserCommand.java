package command;

import controller.RootApplication;


/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class OpenFileChooserCommand extends Command {

	@Override
	public void execute() {
		RootApplication.getInstance().openFileChooser();
	}

	@Override
	public void undo() {
	}

}
