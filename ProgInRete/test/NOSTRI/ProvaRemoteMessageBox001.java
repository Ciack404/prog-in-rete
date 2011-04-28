package NOSTRI;

import JAM.*;
import java.rmi.*;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class ProvaRemoteMessageBox001{
    public static void main(String[] args){
        ADSL adsl;
        PersonalAgentID myID;
        MessageBox box1;
        try{
            myID = new PersonalAgentID("Mario","Impiegato");
            adsl = (ADSL) Naming.lookup("rmi://127.0.0.1:2000/ADSL");
            box1 = new MessageBox(myID);
            adsl.insertRemoteMessageBox(box1);
        }catch(Exception e){
            System.out.println("Failed rmi"+e);
        }
    }
}