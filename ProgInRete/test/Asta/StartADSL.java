package Asta;

/**
 * @(#)StartADSL.java
 *
 *
 * @author Capello Matteo, Bolognesi Massimo
 * @version 1.00 2009/3/4
 */

import JAM.*;

import java.util.*;
import java.rmi.*;
import java.net.*;

public class StartADSL {
	public static void main(String[] args) {
		try{
			ADSLImpl adsl=new ADSLImpl();
			java.rmi.registry.LocateRegistry.createRegistry(2000);
			Naming.bind ("rmi://127.0.0.1:2000/ADSL", adsl);
			System.out.println("Bind RIUSCITO!");
		}
		catch (AlreadyBoundException e) {
			System.err.println("Bind all'RMI-Registry gia effettuato in precedenza!");
		}
		catch (MalformedURLException e) {
			System.err.println("URL non corretto!");
		}
		catch (RemoteException e) {
			System.err.println("\tCLASSE START_ADSL: Bind all'RMI-Registry FALLITO!");
		}
		catch (Exception e) {
			System.err.println("Bind fallito!");
		}
	}
}
