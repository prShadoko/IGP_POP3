package popserver;


import poplib.factory.StateFactory;
import popserver.factory.ServerStateFactory;

import java.net.Socket;

public class Session extends Thread {

    private Socket socket;
    private StateFactory stateFactory;
    private poplib.state.State state;

    public Session(Socket s) {
        System.out.println("Client connected");
        this.socket = s;
        this.stateFactory = new ServerStateFactory();
        this.state = stateFactory.nextState(null);
    }

    @Override
    public void run() {
        super.run();
        this.state.run();
//        send(new CommandOk("server ready"));
    }
}
