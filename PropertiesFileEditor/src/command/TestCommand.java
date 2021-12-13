package command;

public class TestCommand extends Command {

	@Override
	public void execute() {
		System.out.println("Execute test");
		this.notifyCommandExecuteSuccessfull();;
	}

	@Override
	public void undo() {
		System.out.println("Undo test");
		this.notifyCommandUndoSuccessfull();
	}

}
