package command;

public class CommandHistoryManager {
	
	private final static int COMMAND_HISTORY_SIZE = 10;
	
	private static CommandHistoryManager instance;
	

	private Command[] commandHistory = new Command[COMMAND_HISTORY_SIZE];
	private int index = 0;
	
	
	////////// SINGLETON PATTERN //////////
	
	private CommandHistoryManager() {
		
	}
	
	public static CommandHistoryManager getInstance() {
		if (instance == null) {
            instance = new CommandHistoryManager();
        }
        return instance;
	}
	
	////////// PUBLIC HISTORY MANAGEMENT METHODES //////////
	
	public void addCommand(Command command) {
		this.commandHistory[this.index] = command;
		this.incementeIndex();
	}
	
	public Command getLastCommand() {
		Command result = null;
		this.decrementIndex();
		result = this.commandHistory[this.index];
		this.incementeIndex();
		return result;
	}
	
	public void removeLastCommand() {
		this.decrementIndex();
		this.commandHistory[this.index] = null;
	}
	
	//////////PRIVATE HISTORY MANAGEMENT METHODES //////////
	
	private void incementeIndex() {
		if(this.index == COMMAND_HISTORY_SIZE -1) {
			this.index = 0;
		} else {
			this.index++;
		}
	}
	
	private void decrementIndex() {
		if(this.index == 0) {
			this.index = COMMAND_HISTORY_SIZE - 1;
		} else {
			this.index--;
		}
	}
}
