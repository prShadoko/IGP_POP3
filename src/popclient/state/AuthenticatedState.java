package popclient.state;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import poplib.command.Command;
import poplib.command.CommandRetr;
import poplib.command.CommandStat;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;

public class AuthenticatedState extends AbstractState {

	private Scanner scanner;
	private Command commandToSend;
	private boolean quit;

	public AuthenticatedState(DeliveryService deliveryService) {
		super(deliveryService);
		quit = false;
		scanner = new Scanner(System.in);
	}

	@Override
	public void run() {

		commandToSend = null;
		quit = false;

		while (null == commandToSend && !quit) {
			try {
				System.out.println(" --- --- --- ");
				System.out.println("Choose a command");
				System.out.println("1 - STAT");
				System.out.println("2 - RETR");
				System.out.println("0 - QUIT");
				int response = scanner.nextInt();

				if (1 == response) {
					commandToSend = new CommandStat();
				} else if (2 == response) {
					System.out.println(" --- --- --- ");
					System.out.println("What is the message id to retrieve ?");
					if (scanner.hasNextInt()) {
						response = scanner.nextInt();
						commandToSend = new CommandRetr(response);
					}
				} else if (0 == response) {
					quit = true;
				}
			} catch (NoSuchElementException e) {
				System.out.println("err");
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int selection = 0;
		String CDid;
		int i = 0;
		int j = 0;

		while (i == 0) { // changed while(1) to a value that didn't complain in
							// netbeans
			System.out.println("Please choose an option from the following:");
			System.out.println("[1] Search by CD ID");
			System.out.println("[2] View all CD's in store");
			System.out.println("[3] Quit");
			System.out.println("Choice: ");
			selection = scanner.nextInt();

			switch (selection) {

			case 1:
				System.out.println("Please enter the CD ID:");

				break;

			case 2:
				System.out.println("List all CD's");
				i = 1;
				break;

			case 3:
				System.out.println("Quiting...");
				System.exit(5);

			default:
				System.out.println("Your choice was not valid!");

			}
			;

		}
		System.out.println(selection);
	}

	public Command getCommandToSend() {
		return commandToSend;
	}

	public boolean quit() {
		return quit;
	}
}
