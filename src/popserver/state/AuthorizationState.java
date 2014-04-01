package popserver.state;

import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AuthorizationState extends AbstractState {

    public AuthorizationState(DeliveryService deliveryService) {
        super(deliveryService);
    }

    @Override
    public void run() {
        try {
            CommandOk command = new CommandOk("server ready" + getTimestamp());
            System.out.println("Command: " + command.toString());
            deliveryService.send(command);
            System.out.println("Sent");
        } catch(IOException e) {
            //TODO
            e.printStackTrace();
        }
    }

    private String getTimestamp() {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch(UnknownHostException e) {
            hostname = "localhost";
        }
        return "<" + Thread.currentThread().getId() + "." +
                System.currentTimeMillis() + "@" +
                hostname + ">";
    }
}
