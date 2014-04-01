package popclient.state;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import popclient.exception.AuthenticationException;
import popclient.exception.ConnectionException;
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
		CommandApop apop = new CommandApop("p1207814", "");

		try {
			deliveryService.send(apop);
			Command response = deliveryService.receive();

			if (response instanceof CommandOk) {
				System.out.println(((CommandOk) response).getMessage());
			} else if (response instanceof CommandErr) {
				setError(new ConnectionException(new AuthenticationException(response)));
			}
		} catch (IOException e) {
			setError(new ConnectionException(new AuthenticationException(e)));
		}
	}

}
