package popserver.service.impl;

import poplib.command.CommandApop;
import popserver.service.MailboxService;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MailboxServiceImpl implements MailboxService {

    public String getTimestamp() {
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

    @Override
    public boolean checkAuthentication(CommandApop command, String timestamp) {
        //TODO
        return true;
    }

    public void lock(String mailbox) {
        //TODO
    }

    @Override
    public int getMailCount(String mailbox) {
        return 0;
    }

    @Override
    public int getMailSize(String mailbox) {
        return 0;
    }
}
