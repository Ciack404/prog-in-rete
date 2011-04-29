package JAM;

import java.io.*;
import java.util.*;

/**
 * Class GenericAgentID
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class GenericAgentID implements AgentID{
    protected String category;
    protected String name;

    /**
     *
     */
    public GenericAgentID(){
        this.category="NOCATEGORY";
        this.name="NONAME";
    }

    /**
     * Confronta due oggetti di tipo AgentID
     * @param agentID oggetto con cui effettuare il confronto
     * @return true se i due oggetti sono identici, false altrimenti
     */
    public boolean equals(Object agentID){
        if(this==agentID)   return true;
        if(agentID==null)   return false;
        try{
            GenericAgentID age = (GenericAgentID) agentID;
            return true;
        }catch(Exception err){
            return false;
        }
    }

    /**
     * Restituisce una stringa contenente il parametro name
     * @return La stringa richiesta
     */
    public String getName(){
        return this.name;
    }

    /**
     * Restituisce una stringa contenente il parametro category
     * @return La stringa richiesta
     */
    public String getCategory(){
        return this.category;
    }

    /**
     * Restituisce una versione stampabile dell`oggetto
     * @return La stringa richiesta
     */
    public String toString(){
        return "("+ this.getName() +","+ this.getCategory() +")";
    }
}
