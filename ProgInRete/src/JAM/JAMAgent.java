package JAM;

import java.util.*;
import java.rmi.*;
import java.io.*;
import java.rmi.server.*;
import java.net.MalformedURLException;


/**
 * Class JAMAgent
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public abstract class JAMAgent extends Observable{
    public MessageBox myMessageBox;
    private PersonalAgentID myID;
    public ADSL adsl;
    private String name;
    private String ip;
    private int port;
    private List<JAMBehaviour> myBehaviours;

    /**
     *
     * @param agentID
     * @param ip
     * @param name
     * @param port
     * @throws RemoteException
     */
    public JAMAgent(PersonalAgentID agentID, String ip, String name, int port) throws JAMADSLException{
        try{
            this.myMessageBox = new MessageBox(agentID);
            this.myID = agentID;
            this.name = name;
            this.ip = ip;
            this.port = port;
            this.myBehaviours = new LinkedList<JAMBehaviour>();
        }catch(RemoteException e){
            throw new JAMADSLException(e);
        }
    }

    /**
     *
     * @param agentID
     * @throws RemoteException
     */
    public JAMAgent(PersonalAgentID agentID) throws JAMADSLException{
        try{
            this.myMessageBox = new MessageBox(agentID);
            this.myID = agentID;
            this.name = "ADSL";
            this.ip = "127.0.0.1";
            this.port = 1099;
            this.myBehaviours = new LinkedList<JAMBehaviour>();
        }catch(RemoteException e){
            throw new JAMADSLException(e);
        }
    }

     /**
     *
     * @return
     */
    public PersonalAgentID getMyID(){
        return this.myID;
    }

    /**
     * 
     */
    public void init() throws JAMADSLException{
        try{
            this.adsl = (ADSL)Naming.lookup("rmi://"+this.ip+":"+this.port+"/"+this.name);
            this.adsl.insertRemoteMessageBox(myMessageBox);
        }catch(Exception e){
             throw new JAMADSLException(e);
	}
    }

    /**
     * 
     * @throws JAMADSLException
     * @throws RemoteException
     */
    public void destroy() throws JAMADSLException, RemoteException {
        this.adsl.removeRemoteMessageBox(myID);
    }

    /**
     *
     * @return
     */
    public boolean isThereMessage(){
        return this.myMessageBox.isThereMessage();
    }

    /**
     *
     * @param age
     * @return
     */
    public boolean isThereMessage(AgentID age){
        return this.myMessageBox.isThereMessage(age);
    }

    /**
     *
     * @param per
     * @return
     */
    public boolean isThereMessage(Performative per){
        return this.myMessageBox.isThereMessage(per);
    }

    /**
     *
     * @param age
     * @param per
     * @return
     */
    public boolean isThereMessage(AgentID age,Performative per){
        return this.myMessageBox.isThereMessage(age,per);
    }

    /**
     *
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive() throws JAMIOException{
        try{
            return this.myMessageBox.readMessage();
        }catch(InterruptedException e){
            throw new JAMIOException();
        }
    }

    /**
     *
     * @param age
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive(AgentID age) throws JAMIOException{
        try{
            return this.myMessageBox.readMessage(age);
        }catch(InterruptedException e){
            throw new JAMIOException();
        }
    }

    /**
     *
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive(Performative per) throws JAMIOException{
        try{
            return this.myMessageBox.readMessage(per);
        }catch(InterruptedException e){
            throw new JAMIOException();
        }
    }

    /**
     *
     * @param age
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive(AgentID age,Performative per) throws JAMIOException{
        try{
            return this.myMessageBox.readMessage(age, per);
        }catch(InterruptedException e){
            throw new JAMIOException();
        }
    }

    /**
     *
     * @param mex
     * @param adsl
     * @throws JAMADSLException
     * @throws RemoteException
     * @throws JAMMessageBoxException
     */
    public void send(Message mex) throws JAMADSLException, JAMIOException{
        try{
            AgentID rec = mex.getReceiver();
            List<RemoteMessageBox> bo = adsl.getRemoteMessageBox(rec);
            for(RemoteMessageBox box:bo){
                    box.writeMessage(mex);
            }
        }catch(RemoteException e){
            throw new JAMADSLException(e);
        }catch(InterruptedException ex){
            throw new JAMIOException(ex);
        }
    }

    /**
     * 
     * @param behaviour
     */
    public void addBehaviour(JAMBehaviour behaviour) {
        this.myBehaviours.add(behaviour);
    }

    public void start(){
        Thread t;
        for(JAMBehaviour be : this.myBehaviours){
            t = new Thread(be);
	    be.setDone(false);
            be.setMyThread(t);
            t.start();
	    be.setRunning(true);
        }
    }
}
