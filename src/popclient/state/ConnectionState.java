package popclient.state;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import popclient.exception.ConnectionException;
import poplib.Protocol;
import poplib.command.Command;
import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.service.impl.DeliveryServiceImpl;
import poplib.state.AbstractState;
import poplib.state.StateException;

public class ConnectionState extends AbstractState {

	public int tryCount;
	
	public ConnectionState() {
		super(null);
		tryCount = 0;
	}

	public void run() {
		System.out.println("Demande de connexion n°" + (tryCount+1));
		Socket socket;
		try {
			socket = new Socket(InetAddress.getByName("192.168.43.130"), Protocol.LISTENING_PORT);
//			socket = new Socket(InetAddress.getByName("accesbv.univ-lyon1.fr"), 995);
			deliveryService = new DeliveryServiceImpl(socket);

			Command command = deliveryService.receive();

			if (command instanceof CommandOk) {
				CommandOk commandOk = (CommandOk) command;
				System.out.println(commandOk.getMessage());
			} else {
				setError(new ConnectionException("Bad server response", command));
			}
			
		} catch (IOException e) {
			setError(new ConnectionException(e));
		}
	}
	
	public ConnectionState reset() {
		tryCount++;
		if(tryCount > 2) {
			return null;
		} else {
			return this;
		}
	}

	public DeliveryService getDeliveryService() {
		return deliveryService;
	}
}