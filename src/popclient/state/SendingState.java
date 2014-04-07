package popclient.state;

import java.io.IOException;

import poplib.command.Command;
import poplib.command.CommandErr;
import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;
import poplib.state.StateException;

public class SendingState extends AbstractState {

	private Command command;
	private Command response;

	public SendingState(DeliveryService deliveryService) {
		super(deliveryService);
	}

	@Override
	public void run() {
		System.out.println(" --- Sending --- ");
		try {
			deliveryService.send(command);
			response = deliveryService.receiveCommand();
			
			if(response instanceof CommandErr) {
				setError(new StateException(response));
				getError().printStackTrace();
			} else if(response instanceof CommandOk) {
				CommandOk ok = (CommandOk) response;
			}
		} catch (IOException e) {
			setError(new StateException(e));
			getError().printStackTrace();
		}
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public Command getResponse() {
		return response;
	}

}
