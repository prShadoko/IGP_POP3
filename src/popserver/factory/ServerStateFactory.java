package popserver.factory;

import poplib.factory.StateFactory;
import poplib.service.DeliveryService;
import popserver.state.AuthorizationState;
import poplib.state.State;

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
