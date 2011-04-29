package JAM;

import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.lang.*;

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
    public AgentID getOwner() throws RemoteException;

    /**
     * 
     * @param mex
     * @throws JAMMessageBoxException
     */
    public void writeMessage(Message mex) throws RemoteException, InterruptedException;
}
