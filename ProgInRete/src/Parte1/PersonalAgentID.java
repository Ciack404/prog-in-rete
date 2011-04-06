package Parte1;

/**
 * Class PersonalAgentID
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class PersonalAgentID extends CategoryAgentID{
    protected String name;

    /**
     * Costruttore che crea un PersonalAgentID vuoto;
     */
    public PersonalAgentID() {
        this.category = "";
        this.name = "";
    }

    /**
     * Costruttore che crea un PersonalAgentID con nome nam e categoria cat
     * @param cat Categoria dell`agente
     * @param nam Nome da assegnare all`agente
     */
    public PersonalAgentID(String na, String cat) {
        this.category = cat;
        this.name = na;
    }

    /**
     * Confronta due oggetti di tipo AgentID
     * @param agentID oggetto con cui effettuare il confronto
     * @return true se i due oggetti sono identici, false altrimenti
     */
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
