package popclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import poplib.Command;
import poplib.CommandOk;
import poplib.factory.CommandFactory;

public class Client {
	
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	
	CommandFactory commandFactory = new CommandFactory();
	
	public Client() {
		try {

			Socket socket = new Socket(InetAddress.getLocalHost(), 110);
			
			System.out.println("Demande de connexion");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String response = in.readLine();
			Command command = commandFactory.parse(response);
			if(command instanceof CommandOk) {
				
			}
			System.out.println(response);

			socket.close();

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			OutputStream oStream = socket.getOutputStream();
			oStream.write(command.toString().getBytes());
			oStream.flush();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}

		try {
			InputStream iStream = socket.getInputStream();
			// TODO read command (APOP expected)
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}
}
