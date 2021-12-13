package command;

import java.io.File;

import controller.RootApplication;

public class LoadPropertiesFileCommand extends Command {

	private File file;
	
	public LoadPropertiesFileCommand(File file) {
		this.file = file;
	}
	
	@Override
	public void execute() {
		Runnable runnable = () -> {
			//try {
				//Thread.sleep(3000);
				RootApplication.getInstance().loadPropertiesFile(file);
				LoadPropertiesFileCommand.this.notifyCommandExecuteSuccessfull();
			/*} catch (InterruptedException e) {
				LoadPropertiesFileCommand.this.notifyCommandExecuteError();
				e.printStackTrace();
			}*/
			LoadPropertiesFileCommand.this.notifyCommandExecuteSuccessfull();
		};

		Thread thread = new Thread(runnable);
		thread.start();
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
