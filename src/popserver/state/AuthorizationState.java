package popserver.state;

import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.service.impl.DeliveryServiceImpl;
import poplib.state.AbstractState;

import java.io.IOException;
import java.net.Socket;

public class AuthorizationState extends AbstractState {

    public AuthorizationState(DeliveryService deliveryService) {
        super(deliveryService);
    }

    @Override
    public void run() {
        try {
            CommandOk command = new CommandOk("server ready");
            deliveryService.send(command);
            System.out.println("Sent: " + command.toString());
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
