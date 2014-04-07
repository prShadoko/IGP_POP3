package poplib.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import poplib.command.Command;
import poplib.factory.CommandFactory;
import poplib.service.DeliveryService;

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
		System.out.println("    Send: " + command);
		writer.write(command + "\n");
		writer.write("\n");
		writer.flush();
	}

	@Override
	public String receive() throws IOException {
		String line = "";
		String response = "";
		
		line = reader.readLine();
		
		while(!"".equals(line)) {
			response += line + "\n";
			line = reader.readLine();
		}

		return response;
	}
	
	@Override
	public Command receiveCommand() throws IOException {
		Command command = commandFactory.parse(receive());
		System.out.println("Received: " + command);
		return command;
	}
}
