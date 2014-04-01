package popserver.state;

import java.io.IOException;

import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;

public class AuthorizationState extends AbstractState {

    public AuthorizationState(DeliveryService deliveryService) {
        super(deliveryService);
    }

    @Override
    public void run() {
        try {
            CommandOk command = new CommandOk("server ready");
            System.out.println("Command: " + command.toString());
            deliveryService.send(command);
        } catch(IOException e) {
            //TODO
            e.printStackTrace();
        }
    }
}
