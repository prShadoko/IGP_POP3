package poplib.state;

public class StateException extends Exception {

	public StateException() {
	}

	public StateException(String message) {
		super(message);
	}

	public StateException(Throwable cause) {
		super(cause);
	}

	public StateException(String message, Throwable cause) {
		super(message, cause);
	}
}
