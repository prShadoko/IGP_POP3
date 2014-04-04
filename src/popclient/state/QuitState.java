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
		System.out.println(" --- Quit State --- ");
		
		try {
			CommandQuit command = new CommandQuit();
			System.out.println("send: " +command);
			deliveryService.send(command);
			Command response = deliveryService.receiveCommand();
			System.out.println("Receive: "+response);
		} catch (IOException e) {
			setError(new StateException(e));
			getError().printStackTrace();
		}
	}

}
