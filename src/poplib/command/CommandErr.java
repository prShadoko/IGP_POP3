package poplib.command;

public class CommandErr extends Command {

	public static final String COMMAND_NAME = "ERR";
	
	private String message;

	public CommandErr() {
		super(COMMAND_NAME);
	}

	@Override
	public String toString() {
		return super.toString() + " " + message;
	}

	public String getMessage() {
		return message;
	}

	public CommandErr setMessage(String message) {
		this.message = message;
        return this;
	}
}
