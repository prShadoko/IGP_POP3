package popserver.state;

import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.service.impl.DeliveryServiceImpl;
import poplib.state.AbstractState;

import java.io.IOException;
import java.net.Socket;

public class AuthorizationState extends AbstractState {

    public AuthorizationState(DeliveryService deliveryService) {
        super(null);
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
