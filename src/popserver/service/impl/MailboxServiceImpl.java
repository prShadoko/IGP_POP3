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
		return id + " 120\n" +
				"from: pierre.binauld@gmail.com\r\n" +
				"to: thomas.richard@gmail.com\r\n" +
				"Object: IGP POP3\r\n" +
				"\r\n" +
				"Contenu du message\r\n" +
				".\r\n";
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
