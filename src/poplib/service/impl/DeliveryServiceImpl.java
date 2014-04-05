package poplib.service.impl;

import poplib.command.Command;
import poplib.factory.CommandFactory;
import poplib.service.DeliveryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class DeliveryServiceImpl implements DeliveryService {

    protected CommandFactory commandFactory = new CommandFactory();
    private BufferedReader reader;
    private OutputStreamWriter writer;

    public DeliveryServiceImpl(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new OutputStreamWriter(socket.getOutputStream());
    }

    @Override
    public void send(Command command) throws IOException {
        writer.write(command + "\n");
        writer.write("-STOP" + "\n");
        writer.flush();
    }

    /*
    @Override
    public Command receive() throws IOException {
        String line = reader.readLine();
        Command command = commandFactory.parse(line);
        return command;
    }
    //*/
    @Override
    public String receive() throws IOException {
        String line = "";
        String response = "";

        while(!"-STOP\n".equals(line)) {
            response += line;
            line = reader.readLine() + "\n";
        }

        return response;
    }

    @Override
    public Command receiveCommand() throws IOException {
        Command command = commandFactory.parse(receive());
        return command;
    }
}
