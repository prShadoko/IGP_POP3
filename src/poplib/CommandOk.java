package poplib;

public class CommandOk extends Command {

    public static final String COMMAND_NAME = "+OK";

	private String message;

	public CommandOk(String message) {
		super(COMMAND_NAME);
		this.message = message;
	}

	@Override
	public String toString() {
		return super.toString() + " " + message;
	}

	public String getMessage() {
		return message;
	}

	public CommandOk setMessage(String message) {
		this.message = message;
        return this;
	}
}
