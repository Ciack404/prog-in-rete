package JAM;

import java.util.List;
import java.rmi.*;


/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public abstract class JAMAgent {
    private MessageBox myMessageBox;
    private PersonalAgentID myID;
    private ADSL adsl;
    private String name;
    private String ip;
    private int port;
    private java.util.LinkedList<JAMBehaviour> behaviours;

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

    /**
     * 
     */
    public void init(){
        try{
            this.adsl = (ADSL)Naming.lookup("rmi://"+this.ip+":"+this.port+"/"+this.name);
            adsl.insertRemoteMessageBox(myMessageBox);
        }catch(Exception e){
            System.out.println("Impossibile effettuare la lookup su ADSL");
        }
    }

    /**
     * 
     * @throws JAMADSLException
     * @throws RemoteException
     */
    public void destroy() throws JAMADSLException, RemoteException {
        this.adsl.removeRemoteMessageBox(myID);
        for(JAMBehaviour be:behaviours){
            be.done();
        }
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
    public Message receive() throws JAMMessageBoxException, InterruptedException{
        return this.myMessageBox.readMessage();
    }

    /**
     *
     * @param age
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive(AgentID age) throws InterruptedException{
        return this.myMessageBox.readMessage(age);
    }

    /**
     *
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive(Performative per) throws InterruptedException{
        return this.myMessageBox.readMessage(per);
    }

    /**
     *
     * @param age
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message receive(AgentID age,Performative per) throws InterruptedException{
        return this.myMessageBox.readMessage(age, per);
    }

    /**
     *
     * @param mex
     * @param adsl
     * @throws JAMADSLException
     * @throws RemoteException
     * @throws JAMMessageBoxException
     */
    public void send(Message mex, ADSL adsl) throws JAMADSLException, RemoteException, JAMMessageBoxException, InterruptedException{
        AgentID rec = mex.getReceiver();
        List<RemoteMessageBox> bo = adsl.getRemoteMessageBox(rec);
        for(RemoteMessageBox box:bo){
                box.writeMessage(mex);
        }
    }

    /**
     * 
     * @param behaviour
     */
    void addBehaviour(JAMBehaviour behaviour) {
        this.behaviours.add(behaviour);
    }

    void start(){
        Thread t;
        for(JAMBehaviour be : this.behaviours){
            t = new Thread(be);
	    be.setDone(false);
            be.setMyThread(t);
            t.start();
	    be.setRunning(true);
        }
    }
}
