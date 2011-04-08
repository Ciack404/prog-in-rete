package Parte3;

import Parte1.*;
import Parte2.*;
import java.util.LinkedList;
import java.rmi.RemoteException;
import java.rmi.server.*;
import eccezioni.*;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class MessageBox extends MessageBoxNoSync{
    public MessageBox() throws RemoteException{
        super();
    }


}
