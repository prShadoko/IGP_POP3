package poplib;

public class CommandApop extends Command {

    private String mailbox;
    private String md5;

    public CommandApop(String mailbox, String md5) {
        super("APOP");
        this.mailbox = mailbox;
        this.md5 = md5;
    }

    public String getMailbox() {
        return mailbox;
    }

    public CommandApop setMailbox(String mailbox) {
        this.mailbox = mailbox;
        return this;
    }

    public String getMd5() {
        return md5;
    }

    public CommandApop setMd5(String md5) {
        this.md5 = md5;
        return this;
    }
}
