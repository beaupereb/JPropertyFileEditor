package command;

import controller.RootApplication;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class OpenPropertiesFileCommand extends Command {

	public OpenPropertiesFileCommand() {
	}
	
	@Override
	public void execute() {
		RootApplication.getInstance().getPropertiesFileManager().openPropertiesFile();
		/*Runnable runnable = () -> {
			try {
				//Thread.sleep(3000);

				OpenPropertiesFileCommand.this.notifyCommandExecuteSuccessfull();
			} catch (InterruptedException e) {
				OpenPropertiesFileCommand.this.notifyCommandExecuteError();
				e.printStackTrace();
			}
			OpenPropertiesFileCommand.this.notifyCommandExecuteSuccessfull();
		};

		Thread thread = new Thread(runnable);
		thread.start();*/
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
