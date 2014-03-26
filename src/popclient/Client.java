package popclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import poplib.Command;
import poplib.CommandApop;
import poplib.CommandOk;
import poplib.factory.CommandFactory;
import exception.AuthenticationException;
import exception.ConnectionException;

public class Client {

	Socket socket;
	BufferedReader in;
	OutputStream out;

	CommandFactory commandFactory = new CommandFactory();

	public Client() {

		

		// try {
		// OutputStream oStream = socket.getOutputStream();
		// oStream.write(command.toString().getBytes());
		// oStream.flush();
		// } catch (IOException e) {
		// // TODO
		// e.printStackTrace();
		// }
		//
		// try {
		// InputStream iStream = socket.getInputStream();
		// // TODO read command (APOP expected)
		// } catch (IOException e) {
		// // TODO
		// e.printStackTrace();
		// }
	}

	public void connection() throws ConnectionException {
		try {

			System.out.println("Demande de connexion");
			Socket socket = new Socket(InetAddress.getByName("accesbv.univ-lyon1.fr"), 995);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = socket.getOutputStream();
			
			String response = in.readLine();
			Command command = commandFactory.parse(response);

			if (command instanceof CommandOk) {
				CommandOk commandOk = (CommandOk) command;
				System.out.println(commandOk.getMessage());
			} else {
				throw new ConnectionException("Bad server response : " + command);
			}

		} catch (UnknownHostException e) {
			throw new ConnectionException(e);
		} catch (IOException e) {
			throw new ConnectionException(e);
		}
	}
	
	public void authentication() throws AuthenticationException {
		try {
			CommandApop apop = new CommandApop("p1207814", "");
			
			out.write(apop.toString().getBytes());
			out.flush();
		} catch (IOException e) {
			throw new AuthenticationException(e);
		}
	}
	
	public void close() {try {
		socket.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		
		try {
			client.connection();
			client.close();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
