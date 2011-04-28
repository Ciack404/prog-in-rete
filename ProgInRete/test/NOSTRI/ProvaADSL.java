package NOSTRI;

import JAM.*;
import java.rmi.*;
import java.rmi.RemoteException;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class ProvaADSL{
    public static void main(String[] args){
        try{
            ADSLImpl adsl = new ADSLImpl();
            try{
                java.rmi.registry.LocateRegistry.createRegistry(2000);
                Naming.rebind("rmi://127.0.0.1:2000/ADSL", adsl);
            } catch(Exception e){
                System.err.println("Failed to bind to RMIRegistry");
                System.exit(1);
            }
        }catch(RemoteException e){
            System.out.println("Failed to create ADSL");
        }
    }
}