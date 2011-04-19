package Parte3;


import Parte1.*;
import eccezioni.*;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public interface RemoteMessageBox extends java.rmi.Remote{

    /**
     * 
     * @return
     */
    PersonalAgentID getOwner();

    /**
     * 
     * @return
     * @throws JAMMessageBoxException
     */
    Message readMessage() throws JAMMessageBoxException, InterruptedException;

    /**
     *
     * @param age
     * @return
     * @throws JAMMessageBoxException
     */
    Message readMessage(AgentID age) throws JAMMessageBoxException, InterruptedException;

    /**
     *
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    Message readMessage(Performative per) throws JAMMessageBoxException, InterruptedException;

    /**
     *
     * @param age
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    Message readMessage(AgentID age,Performative per) throws JAMMessageBoxException, InterruptedException;

    /**
     *
     * @return
     */
    boolean isThereMessage();

    /**
     *
     * @param age
     * @return
     */
    boolean isThereMessage(AgentID age);

    /**
     *
     * @param per
     * @return
     */
    boolean isThereMessage(Performative per);

    /**
     *
     * @param age
     * @param per
     * @return
     */
    boolean isThereMessage(AgentID age,Performative per);

    /**
     * 
     * @param mex
     * @throws JAMMessageBoxException
     */
    public void writeMessage(Message mex) throws JAMMessageBoxException, InterruptedException;
}
