package popserver;

import poplib.factory.StateFactory;
import poplib.state.AuthorizationState;

import java.io.IOException;
import java.net.Socket;

public class Session extends Thread {

    private Socket socket;
    private StateFactory stateFactory;
    private poplib.state.State state;

    public Session(Socket s) {
        this.socket = s;
//        this.stateFactory =
        try {
            this.state = new AuthorizationState(socket);
        } catch(IOException e) {
            //TODO
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        this.state.run();
//        send(new CommandOk("server ready"));
    }
}
