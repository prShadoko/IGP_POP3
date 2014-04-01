package poplib.service.impl;

import poplib.command.Command;
import poplib.factory.CommandFactory;
import poplib.service.DeliveryService;

import java.io.*;
import java.net.Socket;

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
        try {
            OutputStream oStream = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(oStream);
            writer.write(command.toString());
            writer.flush();
        } catch(IOException e) {
            //TODO
            e.printStackTrace();
        }
	}
	
	@Override
	public Command receive() {
        try {
            InputStream iStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
            return commandFactory.parse(reader.readLine());

        } catch(IOException e) {
            //TODO
            e.printStackTrace();
            return null;
        }
	}

}
