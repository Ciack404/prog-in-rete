package JAM;

import java.io.*;
import java.util.*;

/**
 * Class CategoryAgentID
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class CategoryAgentID extends GenericAgentID{

    /**
     * Costruttore che crea un CategoryAgentID con categoria cat
     * @param cat Categoria dell`agente
     */
    public CategoryAgentID(String cat) {
        this.category = cat;
    }

    /**
     * Confronta due oggetti di tipo AgentID
     * @param agentID Oggetto con cui effettuare il confronto
     * @return true se i due oggetti sono identici, false altrimenti
     */
    @Override
    public boolean equals(Object agentID) {
	if(this==agentID) return true;
	if(agentID==null) return false;
	try {
            CategoryAgentID age = (CategoryAgentID) agentID;
            if(this.category.equals(age.category))
                return true;
            else
                return false;
        } catch(Exception err){
            return false;
	}
    }
}
