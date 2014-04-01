package popclient.factory;

import popclient.state.AuthenticatedState;
import popclient.state.AuthenticationState;
import popclient.state.ConnectionState;
import poplib.command.Command;
import poplib.command.CommandErr;
import poplib.command.CommandQuit;
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
			
		} else if(state instanceof AuthenticationState) {
			AuthenticationState authenticationState = (AuthenticationState) state;
			StateException error = authenticationState.getError();
			if(error != null) {
				if(error.getCommand() instanceof CommandErr) {
					next = state;
				}
				else {
					next = null;
				}
			} else {
				next = new AuthenticatedState(deliveryService);
			}
			
		} else if(state instanceof AuthenticatedState) {
			AuthenticatedState authenticatedState = (AuthenticatedState) state;
			StateException error = authenticatedState.getError();
			if(error != null) {
				//TODO
			} else {
				Command cmd = authenticatedState.getCommandToSend();
				if(cmd instanceof CommandQuit) {
					
				}
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
