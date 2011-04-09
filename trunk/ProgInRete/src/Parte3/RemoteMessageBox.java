package Parte3;


import Parte1.*;
import Parte2.*;
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
    Message readMessage() throws JAMMessageBoxException;



    /**
     *
     * @param age
     * @return
     * @throws JAMMessageBoxException
     */
    Message readMessage(AgentID age) throws JAMMessageBoxException;


    /**
     *
     * @param cat
     * @return
     * @throws JAMMessageBoxException
     */
    Message readMessage(String cat) throws JAMMessageBoxException;


    /**
     *
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    Message readMessage(Performative per) throws JAMMessageBoxException;


    /**
     *
     * @param age
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    Message readMessage(AgentID age,Performative per) throws JAMMessageBoxException;



    /**
     *
     * @param age
     * @param cat
     * @param per
     * @return
     * @throws JAMMessageBoxException
     */
    Message readMessage(AgentID age,String cat,Performative per) throws JAMMessageBoxException;



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
     * @param cat
     * @return
     */
    boolean isThereMessage(String cat);



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
     * @param age
     * @param cat
     * @param per
     * @return
     */
    boolean isThereMessage(AgentID age,String cat,Performative per);

}
