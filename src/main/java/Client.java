import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Locale;

import ui.Bundle;
import ui.Gui;
import ui.GuiControlInterfaceImpl;
import ui.GuiDataInterfaceImpl;

public class Client {
	private Client() {
	}

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Locale locale = new Locale("de_DE");
		Bundle bundle = new Bundle(locale);
		Registry reg = LocateRegistry.getRegistry("localhost", 5555);
		MessengerService messengerService = (MessengerService) reg.lookup("RMI_EXAMPLE");

		String str = "GK-Softare Rocks!";
		System.out.println("RMI returns:" + str);
		GuiDataInterfaceImpl guiDataInterfaceImpl = new GuiDataInterfaceImpl();
		GuiControlInterfaceImpl guiControlInterfaceImpl = new GuiControlInterfaceImpl();
		Gui gui = new Gui(guiDataInterfaceImpl, guiControlInterfaceImpl);
		gui.initializeGUI();
	}
}