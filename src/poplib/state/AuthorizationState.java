package poplib.state;

import poplib.command.CommandOk;
import poplib.service.impl.DeliveryServiceImpl;

import java.io.IOException;
import java.net.Socket;

public class AuthorizationState extends AbstractState {

    public AuthorizationState(Socket socket) throws IOException {
        super(new DeliveryServiceImpl(socket));
    }

    @Override
    public void run() {
        CommandOk command = new CommandOk("server ready");
        deliveryService.send(command);
        System.out.println("Sent: " + command.toString());
    }
}
