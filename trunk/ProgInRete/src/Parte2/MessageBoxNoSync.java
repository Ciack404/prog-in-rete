package Parte2;

import Parte1.*;
import java.util.LinkedList;
import java.rmi.RemoteException;
import java.rmi.server.*;
import eccezioni.*;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class MessageBoxNoSync extends UnicastRemoteObject {
    protected PersonalAgentID owner;
    protected LinkedList<Message> box;
    protected final int maxMessaggi;

    /**
     *
     * @throws RemoteException
     */
    public MessageBoxNoSync() throws RemoteException {
        owner = new PersonalAgentID();
        box = new LinkedList<Message>();
        maxMessaggi = 10;
    }

    /**
     *
     * @param max
     * @throws RemoteException
     */
    public MessageBoxNoSync(int max) throws RemoteException {
        owner = new PersonalAgentID();
        box = new LinkedList<Message>();
        maxMessaggi = max;
    }

    /**
     *
     * @param ow
     * @throws RemoteException
     */
    public MessageBoxNoSync(PersonalAgentID ow) throws RemoteException {
        owner = ow;
        box = new LinkedList<Message>();
        maxMessaggi = 10;
    }

    /**
     *
     * @param ow
     * @param max
     * @throws RemoteException
     */
    public MessageBoxNoSync(PersonalAgentID ow, int max) throws RemoteException {
        owner = ow;
        box = new LinkedList<Message>();
        maxMessaggi = max;
    }

    /**
     *
     * @param ow
     */
    public void setOwner(PersonalAgentID ow){
        this.owner = ow;
    }

    /**
     *
     * @return
     */
    public PersonalAgentID getOwner(){
        return this.owner;
    }

    /**
     *
     * @return
     */
    public LinkedList<Message> getBox(){
        return this.box;
    }

    /**
     *
     * @return
     */
    public int getMaxMessaggi(){
        return this.maxMessaggi;
    }

    /**
     *
     * @return
     */
    public boolean isBoxEmpty(){
        return this.getBox().isEmpty();
    }

    /**
     *
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage() throws JAMMessageBoxException {
        if(!this.isBoxEmpty())    return this.getBox().removeFirst();
        else throw new JAMMessageBoxException();
    }

    public Message readMessage(AgentID age) throws JAMMessageBoxException {
        if(!this.isBoxEmpty()){
            try{
                Message temp[] = this.getBox().toArray();
                while(){
                    if
                }
            }catch(){
                throw new JAMMessageBoxException();
            }
        }
        else throw new JAMMessageBoxException();
    }

    /**
     * Metodo che inserisce in coda alla casella il messaggio passato come parametro
     * @param mex
     */
    public void writeMessage(Message mex){
        if(this.getBox().size() < this.getMaxMessaggi())    this.getBox().add(mex);
    }
}
