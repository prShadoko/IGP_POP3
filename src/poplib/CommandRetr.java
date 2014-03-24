package poplib;

public class CommandRetr extends Command {
	
	private int idMessage;

	public CommandRetr(int idMessage) {
		super("RETR");
		this.idMessage = idMessage; 
	}

	@Override
	public String toString() {
		return super.toString() + " " + Integer.toString(idMessage);
	}

	public int getIdMessage() {
		return idMessage;
	}

	public CommandRetr setIdMessage(int idMessage) {
		this.idMessage = idMessage;
        return this;
	}
}
