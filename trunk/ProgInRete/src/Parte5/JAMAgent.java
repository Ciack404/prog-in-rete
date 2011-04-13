package Parte5;

import Parte1.*;
import Parte3.*;
import Parte4.*;
import java.rmi.RemoteException;
import java.util.List;
import eccezioni.*;

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

    public JAMAgent(PersonalAgentID agentID, String ip, String name, int port) throws RemoteException{
        this.myMessageBox = new MessageBox(agentID);
        this.myID = agentID;
        this.adsl = new ADSLImpl(port, name);
        this.name = name;
        this.ip = ip;
        this.port = port;
    }

    public JAMAgent(PersonalAgentID agentID) throws RemoteException{
        this.myMessageBox = new MessageBox(agentID);
        this.myID = agentID;
        this.adsl = new ADSLImpl(1099, agentID.getName());
        this.name = agentID.getName();
        this.ip = "127.0.0.1";
        this.port = 1099;
    }

    public void init(){

    }

    public void destroy(){
        
    }

    public boolean isThereMessage(){
        return myMessageBox.isThereMessage();
    }

    public boolean isThereMessage(AgentID age){
        return myMessageBox.isThereMessage(age);
    }

    public boolean isThereMessage(String cat){
        return myMessageBox.isThereMessage(cat);
    }

    public boolean isThereMessage(Performative per){
        return myMessageBox.isThereMessage(per);
    }

    public boolean isThereMessage(AgentID age,Performative per){
        return myMessageBox.isThereMessage(age,per);
    }

    public boolean isThereMessage(AgentID age, String cat, Performative per){
        return myMessageBox.isThereMessage(age,cat,per);
    }

    public Message receive() throws JAMMessageBoxException{
        return this.myMessageBox.readMessage();
    }

    public Message receive(AgentID age) throws JAMMessageBoxException{
        return this.myMessageBox.readMessage(age);
    }

    public Message receive(String cat) throws JAMMessageBoxException{
        return this.myMessageBox.readMessage(cat);
    }

    public Message receive(Performative per) throws JAMMessageBoxException{
        return this.myMessageBox.readMessage(per);
    }

    public Message receive(AgentID age,Performative per) throws JAMMessageBoxException{
        return this.myMessageBox.readMessage(age, per);
    }

    public Message receive(AgentID age, String cat, Performative per) throws JAMMessageBoxException{
        return this.myMessageBox.readMessage(age, cat, per);
    }

    public void send(Message mex, ADSL adsl) throws JAMADSLException, RemoteException, JAMMessageBoxException{
        AgentID rec = mex.getReceiver();
        List<RemoteMessageBox> bo = adsl.getRemoteMessageBox(rec);
        for(RemoteMessageBox box:bo){
                box.writeMessage(mex);
        }
    }
}
