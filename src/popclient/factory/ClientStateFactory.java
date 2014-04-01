package popclient.factory;

import popclient.state.AuthenticationState;
import popclient.state.ConnectionState;
import poplib.factory.StateFactory;
import poplib.service.DeliveryService;
import poplib.state.State;
import poplib.state.StateException;

public class ClientStateFactory implements StateFactory {

	private DeliveryService deliveryService;

	public ClientStateFactory() {
		this.deliveryService = null;
	}

	public ClientStateFactory(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	@Override
	public State nextState(State state) {
		State next = null;
		if(null == state) {
			next = new ConnectionState();
		} else if(state instanceof ConnectionState) {
			ConnectionState connectionState = (ConnectionState) state;
			deliveryService = connectionState.getDeliveryService();
			StateException error = connectionState.getError();
			if(null == error) {
				next = new AuthenticationState(deliveryService);
			}
			else {
				error.printStackTrace();
				next = connectionState.reset();
			}
		}
		
		return next;
	}

	public DeliveryService getDeliveryService() {
		return deliveryService;
	}

	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}
}
