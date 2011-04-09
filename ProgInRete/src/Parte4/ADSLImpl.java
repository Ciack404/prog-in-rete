package Parte4;

import Parte1.*;
import Parte2.*;
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
    protected List<RemoteMessageBox> messageBoxes;

    public ADSLImpl() throws RemoteException{
        messageBoxes = new LinkedList<RemoteMessageBox>();
    }

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

    synchronized void insertRemoteMessageBox(RemoteMessageBox messageBox) throws JAMADSLException, RemoteException{
        if(this.messageBoxes.contains(messageBox))    throw new JAMADSLException();
        else{
            this.messageBoxes.add(messageBox);
        }
    }

    synchronized void removeRemoteMessageBox(AgentID agentID) throws JAMADSLException, RemoteException{
        if(this.messageBoxes.isEmpty())    throw new JAMADSLException();
        else{
            int i=0;
            int len=this.messageBoxes.size();
            while(i<len){
                if(messageBoxes.get(i).getOwner().equals(agentID)){
                    this.messageBoxes.remove(i);
                    i=len;
                }
                else i++;
            }
        }
    }
}
