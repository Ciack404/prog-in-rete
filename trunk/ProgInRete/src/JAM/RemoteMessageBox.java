package JAM;

import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

/**
 * Class RemoteMessageBox
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public interface RemoteMessageBox extends Remote{

    /**
     * 
     * @return
     */
    public AgentID getOwner();

    /**
     * 
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage() throws JAMMessageBoxException, InterruptedException;

    /**
     *
     * @param age
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage(AgentID age) throws JAMMessageBoxException, InterruptedException;

    /**
     *
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage(Performative per) throws JAMMessageBoxException, InterruptedException;

    /**
     *
     * @param age
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    public Message readMessage(AgentID age,Performative per) throws JAMMessageBoxException, InterruptedException;

    /**
     *
     * @return
     */
    public boolean isThereMessage();

    /**
     *
     * @param age
     * @return
     */
    public boolean isThereMessage(AgentID age);

    /**
     *
     * @param per
     * @return
     */
    public boolean isThereMessage(Performative per);

    /**
     *
     * @param age
     * @param per
     * @return
     */
    public boolean isThereMessage(AgentID age,Performative per);

    /**
     * 
     * @param mex
     * @throws JAMMessageBoxException
     */
    public void writeMessage(Message mex) throws JAMMessageBoxException, InterruptedException;
}
