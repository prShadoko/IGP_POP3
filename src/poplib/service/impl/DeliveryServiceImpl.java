package poplib.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import poplib.command.Command;
import poplib.factory.CommandFactory;
import poplib.service.DeliveryService;

public class DeliveryServiceImpl implements DeliveryService {

	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;

	protected CommandFactory commandFactory = new CommandFactory();

	public DeliveryServiceImpl(Socket socket) throws IOException {
		this.socket = socket;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		
	}

	@Override
	public void send(Command command) throws IOException {
		writer.write(command.toString());
		writer.flush();
	}

	@Override
	public Command receive() throws IOException {
		String line = reader.readLine();
		Command command = commandFactory.parse(line);
		return command;
	}
}
