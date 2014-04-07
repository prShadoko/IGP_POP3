package poplib.service.impl;

import poplib.command.Command;
import poplib.factory.CommandFactory;
import poplib.service.DeliveryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class DeliveryServiceImpl implements DeliveryService {

    protected CommandFactory commandFactory = new CommandFactory();
    private BufferedReader reader;
    private OutputStreamWriter writer;

    public DeliveryServiceImpl(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new OutputStreamWriter(socket.getOutputStream());
    }

    @Override
    public void send(Command command) throws IOException {
        writer.write(command.toString());
        writer.flush();
    }

    @Override
    public Command receive() throws IOException {
        Command command = commandFactory.parse(reader);
        return command;
    }
}
