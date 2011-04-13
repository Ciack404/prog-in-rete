package Parte3;

import Parte1.*;
import Parte2.*;
import java.rmi.RemoteException;
import eccezioni.*;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class MessageBox extends MessageBoxNoSync implements RemoteMessageBox{

    /**
     *
     * @param ow
     * @throws RemoteException
     */
    public MessageBox(PersonalAgentID ow) throws RemoteException {
        super(ow);
    }


    /**
     *
     * @param ow
     * @param max
     * @throws RemoteException
     */
    public MessageBox(PersonalAgentID ow, int max) throws RemoteException {
        super(ow, max);
    }

    /**
     *
     * @return
     */
    public PersonalAgentID getOwner(){
        return super.getOwner();
    }

    /**
     *
     * @return
     */
    public boolean isBoxEmpty(){
        return super.isBoxEmpty();
    }

    /**
     *
     * @return
     * @throws JAMMessageBoxException
     */
    synchronized public Message readMessage() throws JAMMessageBoxException{
        return super.readMessage();
    }


    /**
     *
     * @param age
     * @return
     * @throws JAMMessageBoxException
     */
    synchronized public Message readMessage(AgentID age) throws JAMMessageBoxException {
        return super.readMessage(age);
    }


    /**
     *
     * @param cat
     * @return
     * @throws JAMMessageBoxException
     */
    synchronized public Message readMessage(String cat) throws JAMMessageBoxException {
        return super.readMessage(cat);
    }


    /**
     *
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    synchronized public Message readMessage(Performative per) throws JAMMessageBoxException {
        return super.readMessage(per);
    }


    /**
     *
     * @param age
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    synchronized public Message readMessage(AgentID age,Performative per) throws JAMMessageBoxException {
        return super.readMessage(age, per);
    }


    /**
     *
     * @param age
     * @param cat
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    synchronized public Message readMessage(AgentID age, String cat, Performative per) throws JAMMessageBoxException {
        return super.readMessage(age, cat, per);
    }


    /**
     *
     * @return
     */
    synchronized public boolean isThereMessage(){
        return super.isThereMessage();
    }


    /**
     *
     * @param age
     * @return
     */
    synchronized public boolean isThereMessage(AgentID age){
        return super.isThereMessage(age);
    }


    /**
     *
     * @param cat
     * @return
     */
    synchronized public boolean isThereMessage(String cat){
        return super.isThereMessage(cat);
    }


    /**
     *
     * @param per
     * @return
     */
    synchronized public boolean isThereMessage(Performative per){
        return super.isThereMessage(per);
    }


    /**
     *
     * @param age
     * @param per
     * @return
     */
    synchronized public boolean isThereMessage(AgentID age,Performative per){
        return super.isThereMessage(age, per);
    }


    /**
     * 
     * @param age
     * @param cat
     * @param per
     * @return
     */
    synchronized public boolean isThereMessage(AgentID age, String cat, Performative per){
        return super.isThereMessage(age, cat, per);
    }

    /**
     *
     * @param mex
     * @throws JAMMessageBoxException
     */
    synchronized public void writeMessage(Message mex) throws JAMMessageBoxException{
        super.writeMessage(mex);
    }
}
