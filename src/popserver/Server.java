package popserver;

import poplib.Protocol;

import java.io.IOException;
import java.net.ServerSocket;


public class Server {

    private int sessionCount;

    public Server(int sessionCount) {
       this.sessionCount = sessionCount;
    }

    public void run() {
        try {
            ServerSocket s = new ServerSocket(Protocol.LISTENING_PORT, sessionCount);
            new Session(s.accept()).start();
        } catch(IOException e) {
            // TODO
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Server(5).run();
    }
}
