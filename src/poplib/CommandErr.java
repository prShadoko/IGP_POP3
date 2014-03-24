package poplib;

public class CommandErr extends Command {
	
	private String message;

	public CommandErr() {
		super("-Err");
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
