import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MessengerServiceImpl extends UnicastRemoteObject implements MessengerService {

	protected MessengerServiceImpl() throws RemoteException {
		super();
	}

	public void sendMessage(String clientMessage) {

		System.out.println("Exmple RMI Program");
	}



}
