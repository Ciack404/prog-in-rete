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
     * lettura del primo messaggio in coda
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage() throws JAMMessageBoxException {
        if(!this.isBoxEmpty())    return this.getBox().removeFirst();
        else throw new JAMMessageBoxException();
    }

    /**
     * lettura del primo messaggio in coda inviato da un certo agente
     * @param age
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage(AgentID age) throws JAMMessageBoxException {
        if(!this.isBoxEmpty()){
            boolean notFound = true;
            int i = 0;
            int len = this.getBox().size();
            while(notFound && i<len){
                if(this.getBox().get(i).getSender().equals(age))    notFound=false;
                else    i++;
            }
            if(notFound)    throw new JAMMessageBoxException();
            else return this.getBox().remove(i);
        }
        else throw new JAMMessageBoxException();
    }

    /**
     * lettura del primo messaggio in coda inviato da un agente appartenente ad una certa categoria di agenti
     * @param cat
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage(String cat) throws JAMMessageBoxException {
        if(!this.isBoxEmpty()){
            boolean notFound = true;
            int i = 0;
            int len = this.getBox().size();
            while(notFound && i<len){
                if(this.getBox().get(i).getSender().getCategory().equals(cat))    notFound=false;
                else    i++;
            }
            if(notFound)    throw new JAMMessageBoxException();
            else return this.getBox().remove(i);
        }
        else throw new JAMMessageBoxException();
    }

    /**
     * lettura del primo messaggio in coda corrispondente ad una certa performativa
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage(Performative per) throws JAMMessageBoxException {
        if(!this.isBoxEmpty()){
            boolean notFound = true;
            int i = 0;
            int len = this.getBox().size();
            while(notFound && i<len){
                if(this.getBox().get(i).getPerformative().equals(per))    notFound=false;
                else    i++;
            }
            if(notFound)    throw new JAMMessageBoxException();
            else return this.getBox().remove(i);
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
    public Message readMessage(AgentID age,Performative per) throws JAMMessageBoxException {
        if(!this.isBoxEmpty()){
            boolean notFound = true;
            int i = 0;
            int len = this.getBox().size();
            while(notFound && i<len){
                if(this.getBox().get(i).getSender().equals(age) && this.getBox().get(i).getPerformative().equals(per))    notFound=false;
                else    i++;
            }
            if(notFound)    throw new JAMMessageBoxException();
            else return this.getBox().remove(i);
        }
        else throw new JAMMessageBoxException();
    }

    /**
     * lettura del primo messaggio in coda inviato da un agente appartenente ad una certa categoria di agenti e corrispondente ad una certa performativa
     * @param age
     * @param cat
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage(AgentID age, String cat, Performative per) throws JAMMessageBoxException {
        if(!this.isBoxEmpty()){
            boolean notFound = true;
            int i = 0;
            int len = this.getBox().size();
            while(notFound && i<len){
                if(this.getBox().get(i).getSender().equals(age) && this.getBox().get(i).getPerformative().equals(per) && this.getBox().get(i).getSender().getCategory().equals(cat))    notFound=false;
                else    i++;
            }
            if(notFound)    throw new JAMMessageBoxException();
            else return this.getBox().remove(i);
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

    public boolean isThereMessage(AgentID age) throws JAMMessageBoxException {
        boolean found = false;
        if(!this.isBoxEmpty()){
            int i = 0;
            int len = this.getBox().size();
            while(found && i<len){
                if(this.getBox().get(i).getSender().equals(age)){
                    found=true;
                    i=len;
                }
                else    i++;
            }
        }
        return found;
    }


    public boolean isThereMessage(String cat) throws JAMMessageBoxException {
        boolean found = false;
        if(!this.isBoxEmpty()){
            int i = 0;
            int len = this.getBox().size();
            while(found && i<len){
                if(this.getBox().get(i).getSender().getCategory().equals(cat)){
                    found=true;
                    i=len;
                }
                else    i++;
            }
        }
        return found;
    }


    public boolean isThereMessage(Performative per) throws JAMMessageBoxException {
        boolean found = false;
        if(!this.isBoxEmpty()){
            int i = 0;
            int len = this.getBox().size();
            while(found && i<len){
                if(this.getBox().get(i).getPerformative().equals(per)){
                    found=true;
                    i=len;
                }
                else    i++;
            }
        }
        return found;
    }


    public boolean isThereMessage(AgentID age,Performative per) throws JAMMessageBoxException {
        boolean found = false;
        if(!this.isBoxEmpty()){
            int i = 0;
            int len = this.getBox().size();
            while(found && i<len){
                if(this.getBox().get(i).getSender().equals(age) && this.getBox().get(i).getPerformative().equals(per)){
                    found=true;
                    i=len;
                }
                else    i++;
            }
        }
        return found;
    }


    public boolean isThereMessage(AgentID age, String cat, Performative per) throws JAMMessageBoxException {
        boolean found = false;
        if(!this.isBoxEmpty()){
            int i = 0;
            int len = this.getBox().size();
            while(found && i<len){
                if(this.getBox().get(i).getSender().equals(age) && this.getBox().get(i).getPerformative().equals(per) && this.getBox().get(i).getSender().getCategory().equals(cat)){
                    found=true;
                    i=len;
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
    public void writeMessage(Message mex){
        if(this.getBox().size() < this.getMaxMessaggi())    this.getBox().add(mex);
    }
}
