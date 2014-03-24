package poplib;

public class CommandOk extends Command {
	
	private String message;

	public CommandOk(String message) {
		super("+OK");
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
