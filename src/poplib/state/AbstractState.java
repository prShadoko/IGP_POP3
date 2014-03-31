package poplib.state;

import java.io.IOException;

import poplib.factory.CommandFactory;
import poplib.service.DeliveryService;

public abstract class AbstractState implements State {

	protected DeliveryService deliveryService;
	protected CommandFactory commandFactory = new CommandFactory();
	
	public AbstractState(DeliveryService deliveryService) throws IOException {
		this.deliveryService = deliveryService;
	}
	
	@Override
	public void run() {
	}
}
