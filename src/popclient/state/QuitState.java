package popclient.state;

import java.io.IOException;

import poplib.command.Command;
import poplib.command.CommandQuit;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;
import poplib.state.StateException;

public class QuitState extends AbstractState {

	public QuitState(DeliveryService deliveryService) {
		super(deliveryService);
	}

	@Override
	public void run() {
		System.out.println("Quiting");
		
		try {
			deliveryService.send(new CommandQuit());
			Command response = deliveryService.receive();
			System.out.println(response);
		} catch (IOException e) {
			setError(new StateException(e));
			getError().printStackTrace();
		}
	}

}
