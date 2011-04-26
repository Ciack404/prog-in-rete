package JAM;

/**
 * Class PersonalAgentID
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class PersonalAgentID extends CategoryAgentID{

    /**
     * Costruttore che crea un PersonalAgentID con nome nam e categoria cat
     * @param cat Categoria dell`agente
     * @param nam Nome da assegnare all`agente
     */
    public PersonalAgentID(String na, String cat) {
        super(cat);
        this.name = na;
    }

    /**
     * Confronta due oggetti di tipo AgentID
     * @param agentID oggetto con cui effettuare il confronto
     * @return true se i due oggetti sono identici, false altrimenti
     */
    @Override
    public boolean equals(Object agentID) {
	if(this==agentID) return true;
	if(agentID==null) return false;
	try {
            PersonalAgentID age = (PersonalAgentID) agentID;
            if(this.name.equals(age.name) && this.category.equals(age.category))
                return true;
            else
                return false;
        } catch(ClassCastException err){
            return false;
	}
    }
}
