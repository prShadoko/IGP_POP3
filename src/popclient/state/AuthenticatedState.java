package popclient.state;

import java.io.IOException;
import java.util.Scanner;

import poplib.command.Command;
import poplib.command.CommandRetr;
import poplib.command.CommandStat;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;

public class AuthenticatedState extends AbstractState {

    private Command commandToSend;
    private boolean quit;

    public AuthenticatedState(DeliveryService deliveryService) {
        super(deliveryService);
        quit = false;
    }

    @Override
    public void run() {

        commandToSend = null;

        while(null == commandToSend && !quit) {
            System.out.println(" --- --- --- ");
            System.out.println("Choose a command");
            System.out.println("1 - STAT");
            System.out.println("2 - RETR");
            System.out.println("0 - QUIT");
            Scanner sc = new Scanner(System.in);
            if(sc.hasNextInt()) {
                int response = sc.nextInt();
                if(1 == response) {
                    commandToSend = new CommandStat();
                } else if(2 == response) {
                    System.out.println(" --- --- --- ");
                    System.out.println("What is the message id to retrieve ?");
                    if(sc.hasNextInt()) {
                        response = sc.nextInt();
                        commandToSend = new CommandRetr(response);
                    }
                } else if(0 == response) {
                    quit = true;
                }
            }
            sc.next();
            sc.close();
        }
    }

    public static void main(String[] args) {
        AuthenticatedState s = new AuthenticatedState(null);
        s.run();
    }

    public Command getCommandToSend() {
        return commandToSend;
    }

    public boolean quit() {
        return quit;
    }
}
