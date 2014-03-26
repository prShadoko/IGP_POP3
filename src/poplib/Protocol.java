package poplib;

public class Protocol {

    public static int LISTENING_PORT = 110;

    public enum State {
        AUTHORIZATION,
        TRANSACTION,
        UPDATE
    }
}
