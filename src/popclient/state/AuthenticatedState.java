package popclient.state;

import java.util.Scanner;

import poplib.command.Command;
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
		
		while(null == commandToSend) {
			System.out.println("Que voulez vous faire ?");
			System.out.println("1 - STAT");
			System.out.println("2 - RETR");
			System.out.println("0 - QUIT");
			System.out.println(" --- --- --- ");
			Scanner sc = new Scanner(System.in);
			String response = sc.next();
			sc.close();
			if("1".equals(response)) {
				
			}
			else if("2".equals(response)) {
				
			}
			else if("0".equals(response)) {
				quit = true;
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
	
	public boolean quit() {
		return quit;
	}
}
