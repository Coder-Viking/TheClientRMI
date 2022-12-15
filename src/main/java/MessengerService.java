

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessengerService extends Remote {
	public void sendMessage(String clientMessage) throws RemoteException;

}
