package popclient.state;

import poplib.command.Command;
import poplib.command.CommandErr;
import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;
import poplib.state.StateException;

import java.io.IOException;

public class SendingState extends AbstractState {

    private Command command;
    private Command response;

    public SendingState(DeliveryService deliveryService) {
        super(deliveryService);
    }

    @Override
    public void run() {
        try {
            deliveryService.send(command);
            response = deliveryService.receive();

            if(response instanceof CommandErr) {
                setError(new StateException(response));
            } else if(response instanceof CommandOk) {
                CommandOk ok = (CommandOk) response;
                System.out.println(ok);
            }
        } catch(IOException e) {
            setError(new StateException(e));
            getError().printStackTrace();
        }
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Command getResponse() {
        return response;
    }
}
