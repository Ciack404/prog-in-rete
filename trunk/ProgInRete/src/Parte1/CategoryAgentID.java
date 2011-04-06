package Parte1;

/**
 * Class CategoryAgentID
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class CategoryAgentID extends GenericAgentID{
    protected String category;

    /**
     * Costruttore che crea un CategoryAgentID vuoto;
     */
    public CategoryAgentID() {
        this.category = "";
    }

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
    public boolean equals(Object agentID) {
	if(this==agentID) return true;
	if(agentID==null) return false;
	try {
            CategoryAgentID age = (CategoryAgentID) agentID;
            if(this.category.equals(age.category))
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
        return "NONAME";
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
