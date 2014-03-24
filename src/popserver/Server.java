package popserver;

import java.io.IOException;
import java.net.ServerSocket;


public class Server {

    public static int LISTENING_PORT = 110;
    public static int SESSION_COUNT = 5;

    public Server() {
        try {
            ServerSocket s = new ServerSocket(LISTENING_PORT, SESSION_COUNT);
            new Session(s.accept());
        } catch(IOException e) {
            // TODO
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Server();
    }
}
