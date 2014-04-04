package poplib.service;

import java.io.IOException;

import poplib.command.Command;

public interface DeliveryService {

	public void send(Command command) throws IOException;

	public String receive() throws IOException;
	
	public Command receiveCommand() throws IOException;
}
