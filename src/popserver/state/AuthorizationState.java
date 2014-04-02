package popserver.state;

import poplib.command.Command;
import poplib.command.CommandApop;
import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;
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
            Command command = new CommandOk("server ready" + timestamp);
            System.out.println("Command: " + command.toString());
            deliveryService.send(command);
            System.out.println("Sent");
            command = deliveryService.receive();
            if(command instanceof CommandApop) {
                if(mailboxService.checkAuthentication((CommandApop) command, timestamp)) {
                    command = new CommandOk("maildrop has " + mailboxService.getMailCount(((CommandApop) command).getMailbox()) + " message(s) (" + mailboxService.getMailSize(((CommandApop) command).getMailbox()) + ")");
                } else {
                    //TODO
                }
            }
        } catch(IOException e) {
            //TODO
            e.printStackTrace();
        }
    }
}
