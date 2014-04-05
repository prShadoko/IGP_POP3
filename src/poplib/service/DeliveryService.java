package poplib.service;

import poplib.command.Command;

import java.io.IOException;

public interface DeliveryService {

    //public Command receive() throws IOException;
    public void send(Command command) throws IOException;

    public String receive() throws IOException;

    public Command receiveCommand() throws IOException;
}
