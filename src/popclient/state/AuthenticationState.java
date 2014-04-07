package popclient.state;

import java.io.IOException;

import popclient.exception.AuthenticationException;
import popclient.exception.ConnectionException;
import poplib.command.Command;
import poplib.command.CommandApop;
import poplib.command.CommandErr;
import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;

public class AuthenticationState extends AbstractState {

	public AuthenticationState(DeliveryService deliveryService) {
		super(deliveryService);
	}
	
	@Override
	public void run() {
		System.out.println(" --- Authentication --- ");
		CommandApop apop = new CommandApop("mailbox", "md5");

		try {
			deliveryService.send(apop);
			Command response = deliveryService.receiveCommand();

			if (response instanceof CommandOk) {
				System.out.println("Message:" + ((CommandOk) response).getMessage());
			} else if (response instanceof CommandErr) {
				setError(new ConnectionException(new AuthenticationException(response)));
			}
		} catch (IOException e) {
			setError(new AuthenticationException(e));
		}
	}

}
