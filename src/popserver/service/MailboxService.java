package popserver.service;

import poplib.command.CommandApop;

public interface MailboxService {

    public String stat();

    public String retr(int id);

    public String update();

    public String getTimestamp();

    public boolean checkAuthentication(CommandApop command, String timestamp);

    public int getMailSize();

    public int getMailCount();
}
