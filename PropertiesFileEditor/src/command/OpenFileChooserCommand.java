package command;

import controller.RootApplication;
import javafx.stage.FileChooser;

public class OpenFileChooserCommand extends Command {

	@Override
	public void execute() {
		RootApplication.getInstance().openFileChooser();
	}

	@Override
	public void undo() {
	}

}
