package popserver.state;

import poplib.service.DeliveryService;
import poplib.state.AbstractState;

public class UpdateState extends AbstractState {

    public UpdateState(DeliveryService deliveryService) {
        super(deliveryService);
    }

    @Override
    public void run() {

    }
}
