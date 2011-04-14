package Parte5;

import Parte1.*;
import Parte3.*;
import Parte4.*;
import java.rmi.RemoteException;
import java.util.List;
import eccezioni.*;
import java.rmi.*;


/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class JAMAgent {
    private MessageBox myMessageBox;
    private PersonalAgentID myID;
    private ADSL adsl;
    private String name;
    private String ip;
    private int port;

    /**
     *
     * @param agentID
     * @param ip
     * @param name
     * @param port
     * @throws RemoteException
     */
    public JAMAgent(PersonalAgentID agentID, String ip, String name, int port) throws RemoteException{
        this.myMessageBox = new MessageBox(agentID);
        this.myID = agentID;
        this.adsl = new ADSLImpl();
        this.name = name;
        this.ip = ip;
        this.port = port;
    }

    /**
     *
     * @param agentID
     * @throws RemoteException
     */
    public JAMAgent(PersonalAgentID agentID) throws RemoteException{
        this.myMessageBox = new MessageBox(agentID);
        this.myID = agentID;
        this.adsl = new ADSLImpl();
        this.name = agentID.getName();
        this.ip = "127.0.0.1";
        this.port = 1099;
    }

    public void init(){
        try{
            adsl = (ADSL)Naming.lookup("rmi://"+this.ip+":"+this.port+"/"+this.name);
        }catch(Exception e){
            System.out.println("Impossibile effettuare la lookup su ADSL");
        }
    }

    public void destroy() throws JAMADSLException, RemoteException {
        adsl.removeRemoteMessageBox(myID);
    }

    /**
     *
     * @return
     */
    public boolean isThereMessage(){
        return myMessageBox.isThereMessage();
    }

    /**
     *
     * @param age
     * @return
     */
    public boolean isThereMessage(AgentID age){
        return myMessageBox.isThereMessage(age);
    }

    /**
     *
     * @param cat
     * @return
     */
    public boolean isThereMessage(String cat){
        return myMessageBox.isThereMessage(cat);
    }

    /**
     *
     * @param per
     * @return
     */
    public boolean isThereMessage(Performative per){
        return myMessageBox.isThereMessage(per);
    }

    /**
     *
     * @param age
     * @param per
     * @return
     */
    public boolean isThereMessage(AgentID age,Performative per){
        return myMessageBox.isThereMessage(age,per);
    }

    /**
     *
     * @param age
     * @param cat
     * @param per
     * @return
     */
    public boolean isThereMessage(AgentID age, String cat, Performative per){
        return myMessageBox.isThereMessage(age,cat,per);
    }

    /**
     *
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive() throws JAMMessageBoxException{
        return this.myMessageBox.readMessage();
    }

    /**
     *
     * @param age
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive(AgentID age) throws JAMMessageBoxException{
        return this.myMessageBox.readMessage(age);
    }

    /**
     *
     * @param cat
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive(String cat) throws JAMMessageBoxException{
        return this.myMessageBox.readMessage(cat);
    }

    /**
     *
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive(Performative per) throws JAMMessageBoxException{
        return this.myMessageBox.readMessage(per);
    }

    /**
     *
     * @param age
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive(AgentID age,Performative per) throws JAMMessageBoxException{
        return this.myMessageBox.readMessage(age, per);
    }

    /**
     *
     * @param age
     * @param cat
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive(AgentID age, String cat, Performative per) throws JAMMessageBoxException{
        return this.myMessageBox.readMessage(age, cat, per);
    }

    /**
     *
     * @param mex
     * @param adsl
     * @throws JAMADSLException
     * @throws RemoteException
     * @throws JAMMessageBoxException
     */
    public void send(Message mex, ADSL adsl) throws JAMADSLException, RemoteException, JAMMessageBoxException{
        AgentID rec = mex.getReceiver();
        List<RemoteMessageBox> bo = adsl.getRemoteMessageBox(rec);
        for(RemoteMessageBox box:bo){
                box.writeMessage(mex);
        }
    }
}