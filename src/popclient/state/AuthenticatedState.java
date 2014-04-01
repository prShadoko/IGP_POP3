package popclient.state;

import java.util.Scanner;

import poplib.command.Command;
import poplib.command.CommandQuit;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;

public class AuthenticatedState extends AbstractState {

	private Command commandToSend;
	
	public AuthenticatedState(DeliveryService deliveryService) {
		super(deliveryService);
	}

	@Override
	public void run() {

		commandToSend = null;
		
		while(null == commandToSend) {
			System.out.println("Que voulez vous faire ?");
			System.out.println("1 - STAT");
			System.out.println("2 - RETR");
			System.out.println("0 - QUIT");
			System.out.println(" --- --- --- ");
			Scanner sc = new Scanner(System.in);
			String response = sc.next();
			
			if("1".equals(response)) {
				
			}
			else if("2".equals(response)) {
				
			}
			else if("0".equals(response)) {
				commandToSend = new CommandQuit();
			}
		}
	}
	
	public static void main(String[] args) {
		AuthenticatedState s = new AuthenticatedState(null);
		s.run();
	}
	
	public Command getCommandToSend() {
		return commandToSend;
	}
}
