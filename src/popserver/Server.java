package popserver;

import poplib.Protocol;

import java.io.IOException;
import java.net.ServerSocket;


public class Server {

    public Server(int sessionCount) {
        try {
            ServerSocket s = new ServerSocket(Protocol.LISTENING_PORT, sessionCount);
            new Session(s.accept());
        } catch(IOException e) {
            // TODO
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Server(5);
    }
}
