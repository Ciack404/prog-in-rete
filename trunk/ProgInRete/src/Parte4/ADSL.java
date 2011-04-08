package Parte4;

import Parte1.*;
import Parte3.*;
import eccezioni.*;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public interface ADSL {
    List<RemoteMessageBox> getRemoteMessageBox(AgentID agentID) throws JAMADSLException, RemoteException;
}
