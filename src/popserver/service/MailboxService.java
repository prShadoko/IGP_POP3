package popserver.service;

import poplib.command.CommandApop;

public interface MailboxService {

    public String getTimestamp();
    public boolean checkAuthentication(CommandApop command, String timestamp);
    public int getMailCount(String mailbox);
    public int getMailSize(String mailbox);
}
