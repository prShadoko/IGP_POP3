package popserver;

import poplib.Command;
import poplib.CommandOk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.Socket;

public class Session extends Thread {

    private static int BUFFER_LENGTH = 200;
    private Socket socket;

    public Session(Socket s) {
        this.socket = s;
        send(new CommandOk("server ready"));
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

        try {
            InputStream iStream = socket.getInputStream();
            //TODO read command (APOP expected)
        } catch(IOException e) {
            //TODO
            e.printStackTrace();
        }
    }
}
