package popserver.state;

import poplib.command.*;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;
import popserver.exception.AuthorizationException;
import popserver.service.MailboxService;
import popserver.service.impl.MailboxServiceImpl;

import java.io.IOException;

public class AuthorizationState extends AbstractState {

    MailboxService mailboxService = new MailboxServiceImpl();

    public AuthorizationState(DeliveryService deliveryService) {
        super(deliveryService);
    }

    @Override
    public void run() {
        try {
            String timestamp = mailboxService.getTimestamp();
            Command commandGreeting = new CommandOk("server ready" + timestamp);
            System.out.println("Command: " + commandGreeting.toString());
            deliveryService.send(commandGreeting);
            System.out.println("Sent");
            Command commandRequest = deliveryService.receive();
            if(commandRequest instanceof CommandApop) {
                CommandApop commandApop = (CommandApop) commandRequest;
                Command commandAnswer;
                if(mailboxService.checkAuthentication(commandApop, timestamp)) {
                    mailboxService.lock(commandApop.getMailbox());
                    commandAnswer = new CommandOk("maildrop for " + commandApop.getMailbox() + " ready.");
                } else {
                    commandAnswer = new CommandErr("permission denied for " + commandApop.getMailbox() + " maildrop.");
                    setError(new AuthorizationException(commandAnswer));
                }
                deliveryService.send(commandAnswer);
            } else if(commandRequest instanceof CommandQuit) {
                CommandQuit commandQuit = (CommandQuit) commandRequest;
                Command commandAnswer = new CommandOk("closing server connexion.");
                deliveryService.send(commandAnswer);
                setError(new AuthorizationException(commandQuit));
            } else {
                Command commandAnswer = new CommandErr("invalid request.");
                deliveryService.send(commandAnswer);
                setError(new AuthorizationException(commandAnswer));
            }
        } catch(IOException e) {
            //TODO
            e.printStackTrace();
        }
    }
}
