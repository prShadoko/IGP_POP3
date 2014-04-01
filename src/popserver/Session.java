package popserver;

import java.io.IOException;
import java.net.Socket;

import poplib.factory.StateFactory;

public class Session extends Thread {

    private Socket socket;
    private StateFactory stateFactory;
    private poplib.state.State state;

    public Session(Socket s) {
        System.out.println("Client connected");
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
