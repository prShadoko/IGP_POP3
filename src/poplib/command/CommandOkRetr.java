package poplib.command;

public class CommandOkRetr extends CommandOk {

    private String mail;

    public CommandOkRetr(String mail) {
        this(mail, "");
    }

    public CommandOkRetr(String mail, String comment) {
        super(comment);
        this.mail = mail;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + comment;
    }

    public String getMail() {
        return mail;
    }
}

