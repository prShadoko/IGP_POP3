package popserver.state;

import poplib.command.*;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;
import poplib.state.StateException;
import popserver.service.MailboxService;

import java.io.IOException;

public class TransactionState extends AbstractState {

    private MailboxService mailboxService;

    private boolean quit;

    public TransactionState(DeliveryService deliveryService, MailboxService mailboxService) {
        super(deliveryService);

        this.mailboxService = mailboxService;
        quit = false;
    }

    @Override
    public void run() {
        System.out.println(" --- Transaction State --- ");
        try {
            Command command = deliveryService.receiveCommand();
            String response = null;

            System.out.println("Receive: " + command);
            if(command instanceof CommandQuit) {
                quit = true;
            }

            System.out.println("Receive: " + command);
            if(command instanceof CommandQuit) {
                quit = true;
            } else if(command instanceof CommandStat) {
                response = mailboxService.statistics();
            } else if(command instanceof CommandRetr) {
                response = mailboxService.retrieve(((CommandRetr) command).getIdMessage());
            }

            if(!quit) {
                Command commandToSent = null;
                if(null == response) {
                    commandToSent = new CommandErr();
                } else {
                    commandToSent = new CommandOk(response);
                }
                System.out.println("Send: " + commandToSent);
                deliveryService.send(commandToSent);
            }
        } catch(IOException e) {
            setError(new StateException(e));
            getError().printStackTrace();
        }
    }

    public boolean quit() {
        return quit;
    }
}
