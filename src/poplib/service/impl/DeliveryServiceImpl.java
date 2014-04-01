package poplib.service.impl;

import poplib.command.Command;
import poplib.factory.CommandFactory;
import poplib.service.DeliveryService;

import java.io.*;
import java.net.Socket;

public class DeliveryServiceImpl implements DeliveryService {

	private BufferedReader reader;
	private OutputStreamWriter writer;

	protected CommandFactory commandFactory = new CommandFactory();

	public DeliveryServiceImpl(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new OutputStreamWriter(socket.getOutputStream());
	}

	@Override
	public void send(Command command) throws IOException {
		writer.write(command.toString() + "\n");
		writer.flush();
	}

	@Override
	public Command receive() throws IOException {
		return commandFactory.parse(reader.readLine());
	}
}
