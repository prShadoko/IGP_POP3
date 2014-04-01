package popclient.state;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import popclient.exception.AuthenticationException;
import poplib.command.Command;
import poplib.command.CommandApop;
import poplib.command.CommandErr;
import poplib.command.CommandOk;
import poplib.factory.CommandFactory;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;

public class AuthenticationState extends AbstractState {

	public AuthenticationState(DeliveryService deliveryService) {
		super(deliveryService);
	}

	Socket socket;
	BufferedReader in;
	OutputStream out;

	CommandFactory commandFactory = new CommandFactory();
	
	@Override
	public void run() {
//		try {
//			CommandApop apop = new CommandApop("p1207814", "");
//
//			out.write(apop.toString().getBytes());
//			out.flush();
//			
//			Command command = commandFactory.parse(in.readLine());
//			
//			if(command instanceof CommandErr) {
//				throw new AuthenticationException(((CommandErr) command).getMessage());
//			}
//			else if(command instanceof CommandOk) {
//				System.out.println(((CommandOk) command).getMessage());
//			}
//			
//		} catch (IOException e) {
//			throw new AuthenticationException(e);
//		}
	}

}
