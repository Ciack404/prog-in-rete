package JAM;

import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

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
     * @throws JAMMessageBoxException
     */
    @Override
    public synchronized Message readMessage() throws InterruptedException{
        boolean found = false;
        Message m = new Message();
        while(!found){
            try{
                m = super.readMessage();
                found = true;
                notifyAll();
            }catch(JAMMessageBoxException e){
                wait();
            }
        }
        return m;
    }

    /**
     *
     * @param age
     * @return
     * @throws JAMMessageBoxException
     */
    @Override
    public synchronized Message readMessage(AgentID age) throws InterruptedException {
        boolean found = false;
        Message m = new Message();
        while(!found){
            try{
                m = super.readMessage(age);
                found = true;
                notifyAll();
            }catch(JAMMessageBoxException e){
                wait();
            }
        }
        return m;
    }

    /**
     *
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    @Override
    public synchronized Message readMessage(Performative per) throws InterruptedException {
        boolean found = false;
        Message m = new Message();
        while(!found){
            try{
                m = super.readMessage(per);
                found = true;
                notifyAll();
            }catch(JAMMessageBoxException e){
                wait();
            }
        }
        return m;
    }

    /**
     *
     * @param age
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    @Override
    public synchronized Message readMessage(AgentID age,Performative per) throws InterruptedException {
        boolean found = false;
        Message m = new Message();
        while(!found){
            try{
                m = super.readMessage(age, per);
                found = true;
                notifyAll();
            }catch(JAMMessageBoxException e){
                wait();
            }
        }
        return m;
    }

    /**
     *
     * @return
     */
    @Override
    public synchronized boolean isThereMessage(){
        return super.isThereMessage();
    }

    /**
     *
     * @param age
     * @return
     */
    @Override
    public synchronized boolean isThereMessage(AgentID age){
        return super.isThereMessage(age);
    }

    /**
     *
     * @param per
     * @return
     */
    @Override
    public synchronized boolean isThereMessage(Performative per){
        return super.isThereMessage(per);
    }

    /**
     *
     * @param age
     * @param per
     * @return
     */
    @Override
    public synchronized boolean isThereMessage(AgentID age,Performative per){
        return super.isThereMessage(age, per);
    }

    /**
     *
     * @param mex
     * @throws JAMMessageBoxException
     */
    @Override
    public synchronized void writeMessage(Message mex) throws InterruptedException, RemoteException{
        boolean undone = true;
        while(undone){
            try{
                super.writeMessage(mex);
                undone = false;
                notifyAll();
            }catch(JAMMessageBoxException e){
                wait();
            }
        }
    }
}
