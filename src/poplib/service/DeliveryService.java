package poplib.service;

import poplib.command.Command;

public interface DeliveryService {

	public void send(Command command);
	
	public Command receive();
}
