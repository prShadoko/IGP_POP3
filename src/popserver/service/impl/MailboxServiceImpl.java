package popserver.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import poplib.command.CommandApop;
import popserver.service.MailboxService;

public class MailboxServiceImpl implements MailboxService{

	@Override
	public String stat() {
		return "3 480";
	}

	@Override
	public String retr(int id) {
		return id + " 120\nfrom: pierre.binauld@gmail.com<CR><LF>\nto: thomas.richard@gmail.com<CR><LF>\nObject: IGP POP3<CR><LF>\nContenu du message<CR><LF>\n.<CR><LF>";
	}

	@Override
	public String update() {
		return null;
	}
	
	@Override
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
        return true;
    }
    
    @Override
    public int getMailCount() {
        return 3;
    }

    @Override
    public int getMailSize() {
        return 480;
    }
}
