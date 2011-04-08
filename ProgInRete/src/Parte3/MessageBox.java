package Parte3;

import Parte1.*;
import Parte2.*;
import java.rmi.RemoteException;
import java.rmi.server.*;
import eccezioni.*;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class MessageBox extends MessageBoxNoSync implements RemoteMessageBox{

    public MessageBox() throws RemoteException{
        super();
    }

    public MessageBox(int max) throws RemoteException {
        super();
    }

    public MessageBox(PersonalAgentID ow) throws RemoteException {
        super();
    }

    public MessageBox(PersonalAgentID ow, int max) throws RemoteException {
        super();
    }

    public Message readMessage() throws JAMMessageBoxException{
        return super.readMessage();
    }

    
}
