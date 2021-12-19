package command;


/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
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
