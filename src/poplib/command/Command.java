package poplib.command;

public class Command {

    private String command;

    public Command(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }
}
