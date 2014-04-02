package popserver.state;

import java.io.IOException;

import poplib.command.Command;
import poplib.command.CommandApop;
import poplib.command.CommandErr;
import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;
import popserver.exception.AuthorizationException;
import popserver.service.MailboxService;
import popserver.service.impl.MailboxServiceImpl;

public class AuthorizationState extends AbstractState {

    MailboxService mailboxService = new MailboxServiceImpl();

    public AuthorizationState(DeliveryService deliveryService) {
        super(deliveryService);
    }

    @Override
    public void run() {
    	System.out.println(" --- Authorization State --- ");
        try {
            String timestamp = mailboxService.getTimestamp();
            Command command = new CommandOk("server ready" + timestamp);
            System.out.println("Send: " + command.toString());
            deliveryService.send(command);

            command = deliveryService.receive();
            System.out.println("Receive: " + command);
            if(command instanceof CommandApop) {
            	CommandApop commandApop = (CommandApop) command;
                if(mailboxService.checkAuthentication((CommandApop) command, timestamp)) {
                	System.out.println("User authenticated.");
                    command = new CommandOk("maildrop has " + mailboxService.getMailCount() + " message(s) (" + mailboxService.getMailSize() + ")");
                } else {
                    command = new CommandErr("permission denied for " + commandApop.getMailbox() + " maildrop.");
                    setError(new AuthorizationException(command));
                }
            }
            System.out.println("Send: "+command);
            deliveryService.send(command);
        } catch(IOException e) {
        	setError(new AuthorizationException(e));
            getError().printStackTrace();

        }
    }
}
