package Parte4;

import Parte1.*;
import Parte3.*;
import eccezioni.*;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.*;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class ADSLImpl extends UnicastRemoteObject implements ADSL{
    private List<RemoteMessageBox> messageBoxes;
    private int port;
    private String name;

    /**
     * 
     * @throws RemoteException
     */
    public ADSLImpl() throws RemoteException{
        this.messageBoxes = new LinkedList<RemoteMessageBox>();
        this.port = 1099;
        this.name = "ADSL";
    }

    /**
     * 
     * @param p
     * @param n
     * @throws RemoteException
     */
    public ADSLImpl(int p, String n) throws RemoteException{
        this.messageBoxes = new LinkedList<RemoteMessageBox>();
        this.name = n;
        this.port = p;
    }

    /**
     * 
     * @param agentID
     * @return
     * @throws JAMADSLException
     * @throws RemoteException
     */
    synchronized public List<RemoteMessageBox> getRemoteMessageBox(AgentID agentID) throws JAMADSLException, RemoteException{
        if(this.messageBoxes.isEmpty())   throw new JAMADSLException();
        else{
            List<RemoteMessageBox> elenco = new LinkedList<RemoteMessageBox>();
                for(RemoteMessageBox box:this.messageBoxes){
                    if(box.getOwner().equals(agentID))  elenco.add(box);
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
    synchronized public void insertRemoteMessageBox(RemoteMessageBox messageBox) throws JAMADSLException, RemoteException{
        if(this.messageBoxes.contains(messageBox))    throw new JAMADSLException();
        else{
            this.messageBoxes.add(messageBox);
        }
    }

    /**
     * 
     * @param agentID
     * @throws JAMADSLException
     * @throws RemoteException
     */
    synchronized public void removeRemoteMessageBox(AgentID agentID) throws JAMADSLException, RemoteException{
        if(this.messageBoxes.isEmpty())    throw new JAMADSLException();
        else{
            for(RemoteMessageBox box:this.messageBoxes){
                if(box.getOwner().equals(agentID)){
                    this.messageBoxes.remove(box);
                }
            }
            /*int i=0;
            int len=this.messageBoxes.size();
            while(i<len){
                if(messageBoxes.get(i).getOwner().equals(agentID)){
                    this.messageBoxes.remove(i);
                    i=len;
                }
                else i++;
            }*/
        }
    }
}
