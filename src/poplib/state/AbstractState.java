package poplib.state;

import poplib.factory.CommandFactory;
import poplib.service.DeliveryService;

public abstract class AbstractState implements State {

	protected DeliveryService deliveryService;

	protected CommandFactory commandFactory = new CommandFactory();

	public AbstractState(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}
}
