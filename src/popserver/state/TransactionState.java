package popserver.state;

import poplib.service.DeliveryService;
import poplib.state.AbstractState;

public class TransactionState extends AbstractState {

    public TransactionState(DeliveryService deliveryService) {
        super(deliveryService);
    }

    @Override
    public void run() {

    }
}
