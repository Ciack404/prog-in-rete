package JAM;

import java.rmi.*;
import java.util.List;

/**
 * Class ADSL
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public interface ADSL extends Remote{

    /**
     * 
     * @param agentID
     * @return
     * @throws JAMADSLException
     * @throws RemoteException
     */
    List<RemoteMessageBox> getRemoteMessageBox(AgentID agentID) throws JAMADSLException, RemoteException;

    /**
     *
     * @param messageBox
     * @throws JAMADSLException
     * @throws RemoteException
     */
    void insertRemoteMessageBox(RemoteMessageBox messageBox) throws JAMADSLException, RemoteException;

    /**
     * 
     * @param agentID
     * @throws JAMADSLException
     * @throws RemoteException
     */
    void removeRemoteMessageBox(AgentID agentID) throws JAMADSLException, RemoteException;
}
