package JAM;

/**
 * Interface AgentID
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public interface AgentID extends java.io.Serializable{

    /**
     * Confronta due oggetti di tipo AgentID
     * @param agentID Oggetto con cui confrontare il this <<-----SCHIFO SCRIVERE MEGLIO<<---
     * @return true se i due oggetti ricevuti sono identici, false altrimenti
     */
    public boolean equals(Object agentID);

    /**
     * Restituisce una stringa contenente il parametro name
     * @return La stringa richiesta
     */
    public String getName();

    /**
     * Restituisce una stringa contenente il parametro category
     * @return La stringa richiesta
     */
    public String getCategory();

    /**
     * Restituisce una versione stampabile dell`oggetto
     * @return La stringa richiesta
     */
    @Override
    public String toString();
}
