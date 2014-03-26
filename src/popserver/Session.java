package popserver;

import poplib.Command;
import poplib.CommandOk;
import poplib.Protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Session extends Thread {

    private static int BUFFER_LENGTH = 200;
    private Socket socket;
    private Protocol.State state;

    public Session(Socket s) {
        this.socket = s;
        this.state = Protocol.State.AUTHORIZATION;
    }

    public void send(Command command) {
        try {
            OutputStream oStream = socket.getOutputStream();
            oStream.write(command.toString().getBytes());
            oStream.flush();
        } catch(IOException e) {
            //TODO
            e.printStackTrace();
        }
    }

    public void receive() {
        try {
            InputStream iStream = socket.getInputStream();
            //TODO read command
        } catch(IOException e) {
            //TODO
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        send(new CommandOk("server ready"));
    }
}
