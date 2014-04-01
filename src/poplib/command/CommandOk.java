package poplib.command;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class CommandOk extends Command {

    public static final String COMMAND_NAME = "+OK";

	private String message;
	private String timestamp;

	public CommandOk(String message) {
		super(COMMAND_NAME);
		this.message = message;
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch(UnknownHostException e) {
            hostname = "localhost";
        }
        this.timestamp = "<" +
                Thread.currentThread().getId() + "." +
                System.currentTimeMillis() + "@" +
                hostname + ">";
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

    public String getTimestamp() {
        return timestamp;
    }
}
