package JAM;

import java.io.*;
import java.rmi.*;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.*;

/**
 * Class ADSLImpl
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class ADSLImpl extends UnicastRemoteObject implements ADSL{
    public static List<RemoteMessageBox> messageBoxes;
    String ip;
    int port;
    String name;
    private static List<ADSLMonitor> observers;

    /**
     * 
     * @throws RemoteException
     */
    public ADSLImpl() throws RemoteException{
        this.messageBoxes = new LinkedList<RemoteMessageBox>();
        this.ip = "127.0.0.1";
        this.port = 1099;
        this.name = "ADSL";
        this.observers = new LinkedList<ADSLMonitor>();
    }

    /**
     * 
     * @param p
     * @param n
     * @throws RemoteException
     */
    public ADSLImpl(String ip, int p, String n) throws RemoteException{
        this.messageBoxes = new LinkedList<RemoteMessageBox>();
        this.ip = ip;
        this.name = n;
        this.port = p;
        this.observers = new LinkedList<ADSLMonitor>();
    }

    /**
     * 
     * @param agentID
     * @return
     * @throws JAMADSLException
     * @throws RemoteException
     */
    public synchronized List<RemoteMessageBox> getRemoteMessageBox(AgentID agentID) throws JAMADSLException, RemoteException{
        if(this.messageBoxes.isEmpty())   throw new JAMADSLException();
        else{
            List<RemoteMessageBox> elenco = new LinkedList<RemoteMessageBox>();
                for(RemoteMessageBox box:this.messageBoxes){
                    if(box.getOwner().equals(agentID)){
                        elenco.add(box);
                        notifyObservers("Richiesto box "+box.getOwner().toString());
                    }
                }
            return elenco;
        }
    }

    /**
     * 
     * @param messageBox
     * @throws JAMADSLException
     * @throws RemoteException
     */
    public synchronized void insertRemoteMessageBox(RemoteMessageBox messageBox) throws JAMADSLException, RemoteException{
        if(this.messageBoxes.contains(messageBox))    throw new JAMADSLException();
        else{
            this.messageBoxes.add(messageBox);
            notifyObservers("Iscrizione nuovo box per "+messageBox.getOwner().toString());
        }
    }

    /**
     * MODIFICARE CICLO: CONTINUA A CICLARE ANCHE DOPO AVER TROVATO L'ELEMENTO
     * @param agentID
     * @throws JAMADSLException
     * @throws RemoteException
     */
    public synchronized void removeRemoteMessageBox(AgentID agentID) throws JAMADSLException, RemoteException{
        if(this.messageBoxes.isEmpty())    throw new JAMADSLException();
        else{
            for(RemoteMessageBox box:this.messageBoxes){
                if(box.getOwner().equals(agentID)){
                    this.messageBoxes.remove(box);
                    notifyObservers("Cancellato box "+box.getOwner().toString());
                }
            }
        }
    }

    /**
     * 
     */
    void startRMIRegistry(){
        try {
            java.rmi.registry.LocateRegistry.createRegistry(port);
            notifyObservers("Creata ADSL: "+"  rmi://"+ip+":"+port+"/"+ name);
        }catch(Exception e){
            notifyObservers("adsl non creata");
        }
    }

    /**
     *
     */
    void startADSL(){
        try {
            Naming.rebind("rmi://"+ip+":"+port+"/"+name,this);
            notifyObservers("\n agganciata la ADSL");
        }catch(Exception e){
            notifyObservers("fallito in bind");
        }
    }

    /**
     * 
     */
    void stopADSL(){
        try {
            Naming.unbind("rmi://"+ip+":"+port+"/"+name);
        }catch(Exception e){
            notifyObservers("fallito l' unbind");
        }
    }

    /**
     * 
     * @param m
     */
    public void addObserver(ADSLMonitor m){
       observers.add(m);
    }

    /**
     *
     * @param m
     */
    public void removeObserver(ADSLMonitor m){
       observers.remove(m);
    }

    /**
     *
     * @param s
     */
    public void notifyObservers(String s){
        for(ADSLMonitor m: observers){
            m.updateViewMonitor(s);
        }
    }
}
