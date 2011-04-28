package JAM;

import java.util.LinkedList;
import java.rmi.RemoteException;
import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class MessageBoxNoSync extends UnicastRemoteObject {
    private PersonalAgentID owner;
    protected LinkedList<Message> box;
    protected final int maxMessaggi;
    protected int DEFAULT_MAX_MESSAGE = 10;

    /**
     *
     * @param ow
     * @throws RemoteException
     */
    public MessageBoxNoSync(PersonalAgentID ow) throws RemoteException {
        this.owner = ow;
        this.box = new LinkedList<Message>();
        this.maxMessaggi = DEFAULT_MAX_MESSAGE;
    }

    /**
     *
     * @param ow
     * @param max
     * @throws RemoteException
     */
    public MessageBoxNoSync(PersonalAgentID ow, int max) throws RemoteException {
        this.owner = ow;
        this.box = new LinkedList<Message>();
        this.maxMessaggi = max;
    }

    /**
     *
     * @return
     */
    public AgentID getOwner(){
        return this.owner;
    }

    /**
     *
     * @return
     */
    public boolean isBoxEmpty(){
        return this.box.size() == 0;
    }

    /**
     * lettura del primo messaggio in coda
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage() throws JAMMessageBoxException, InterruptedException {
        if(!this.isBoxEmpty())    return this.box.removeFirst();
        else throw new JAMMessageBoxException();
    }

    /**
     * lettura del primo messaggio in coda inviato da un certo agente
     * @param age
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage(AgentID age) throws JAMMessageBoxException, InterruptedException {
        if(!this.isBoxEmpty()){
            boolean notFound = true;
            int i = 0;
            int len = this.box.size();
            while(notFound && i<len){
                if(this.box.get(i).getSender().equals(age))    notFound=false;
                else    i++;
            }
            if(notFound)    throw new JAMMessageBoxException();
            else return this.box.remove(i);
        }
        else throw new JAMMessageBoxException();
    }

    /**
     * lettura del primo messaggio in coda corrispondente ad una certa performativa
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage(Performative per) throws JAMMessageBoxException, InterruptedException {
        if(!this.isBoxEmpty()){
            boolean notFound = true;
            int i = 0;
            int len = this.box.size();
            while(notFound && i<len){
                if(this.box.get(i).getPerformative().equals(per))    notFound=false;
                else    i++;
            }
            if(notFound)    throw new JAMMessageBoxException();
            else return this.box.remove(i);
        }
        else throw new JAMMessageBoxException();
    }

    /**
     * lettura del primo messaggio in coda inviato da un certo agente e corrispondente ad una certa performativa
     * @param age
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage(AgentID age,Performative per) throws JAMMessageBoxException, InterruptedException {
        if(!this.isBoxEmpty()){
            boolean notFound = true;
            int i = 0;
            int len = this.box.size();
            while(notFound && i<len){
                if(this.box.get(i).getSender().equals(age) && this.box.get(i).getPerformative().equals(per))    notFound=false;
                else    i++;
            }
            if(notFound)    throw new JAMMessageBoxException();
            else return this.box.remove(i);
        }
        else throw new JAMMessageBoxException();
    }

    /**
     * restituisce true se ` presente un messaggio in coda su box, false altrimenti
     * @return
     */
    public boolean isThereMessage(){
        if(!this.isBoxEmpty())    return true;
        else return false;
    }

    /**
     *
     * @param age
     * @return
     * @throws JAMMessageBoxException
     */
    public boolean isThereMessage(AgentID age){
        boolean found = false;
        if(!this.isBoxEmpty()){
            int i = 0;
            int len = this.box.size();
            while(!found && i<len){
                if(this.box.get(i).getSender().equals(age)){
                    found=true;
                }
                else    i++;
            }
        }
        return found;
    }

    /**
     *
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public boolean isThereMessage(Performative per){
        boolean found = false;
        if(!this.isBoxEmpty()){
            int i = 0;
            int len = this.box.size();
            while(!found && i<len){
                if(this.box.get(i).getPerformative().equals(per)){
                    found=true;
                }
                else    i++;
            }
        }
        return found;
    }

    /**
     *
     * @param age
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public boolean isThereMessage(AgentID age,Performative per){
        boolean found = false;
        if(!this.isBoxEmpty()){
            int i = 0;
            int len = this.box.size();
            while(!found && i<len){
                if(this.box.get(i).getSender().equals(age) && this.box.get(i).getPerformative().equals(per)){
                    found=true;
                }
                else    i++;
            }
        }
        return found;
    }

    /**
     * Metodo che inserisce in coda alla casella il messaggio passato come parametro
     * @param mex
     */
    public void writeMessage(Message mex) throws JAMMessageBoxException, InterruptedException{
        if(this.box.size() < this.maxMessaggi)    this.box.add(mex);
        else throw new JAMMessageBoxException();
    }
}
