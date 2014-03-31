package poplib.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import poplib.command.Command;
import poplib.factory.CommandFactory;
import poplib.service.DeliveryService;

public class DeliveryServiceImpl implements DeliveryService {

	private Socket socket;
	private BufferedReader in;
	private OutputStream out;

	protected CommandFactory commandFactory = new CommandFactory();
	
	public DeliveryServiceImpl(Socket socket) throws IOException {
		this.socket = socket;
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = socket.getOutputStream();
	}

	@Override
	public void send(Command command) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Command receive() {
		// TODO Auto-generated method stub
		return null;
	}

}
