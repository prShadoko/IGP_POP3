package popserver.factory;

import poplib.command.CommandErr;
import poplib.command.CommandQuit;
import poplib.factory.StateFactory;
import poplib.service.DeliveryService;
import poplib.state.State;
import poplib.state.StateException;
import popserver.state.AuthorizationState;
import popserver.state.TransactionState;
import popserver.state.UpdateState;

public class ServerStateFactory implements StateFactory {

    private DeliveryService deliveryService;

    public ServerStateFactory() {
        this.deliveryService = null;
    }

    public ServerStateFactory(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @Override
    public State nextState(State state) {
        if(state == null) {
            return new AuthorizationState(deliveryService);
        } else if(state instanceof AuthorizationState) {
            AuthorizationState authorizationState = (AuthorizationState) state;
            StateException error = authorizationState.getError();
            if(error != null) {
                if(error.getCommand() instanceof CommandErr) {
                    return null;
//                    return new AuthorizationState(); //also possible
                } else if(error.getCommand() instanceof CommandQuit) {
                    return null;
                }
            } else {
                return new TransactionState(deliveryService);
            }
        } else if(state instanceof TransactionState) {
            //TODO
        } else if(state instanceof UpdateState) {
            //TODO
        }

        return null;
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
}
