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
		return "from: pierre.binauld@gmail.com<CR><LF>to: thomas.richard@gmail.com<CR><LF>Object: IGP POP3<CR><LF>Message id: "+id+"<CR><LF>\n.<CR><LF>";
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
