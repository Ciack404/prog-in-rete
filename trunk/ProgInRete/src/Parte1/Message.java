package Parte1;

/**
* Class Message
* @author Francesco Alisetta, Mattia Camusso
* @version 1.0
*/
public class Message {
    private Performative performer;
    private String content;
    private Object extraArgument;
    private AgentID sender;
    private AgentID receiver;

    /**
    * Costruttore che crea un Message vuoto;
    */
    public Message(){
        this.performer = null;
	this.content = "";
	this.extraArgument = new Object();
	this.sender = new GenericAgentID();
	this.receiver = new GenericAgentID();
    }

    /**
     * Costruttore che crea un Message contenente i parametri passati dal metodo richiamante
     * @param per
     * @param con
     * @param ex Argomenti opzionali
     * @param sen oggetto Sender
     * @param rec oggetto Receiver
     */
    public Message(Performative per, String con, Object ex, AgentID sen, AgentID rec){
	this.performer = per;
	this.content = con;
	this.extraArgument = ex;
	this.sender = sen;
	this.receiver = rec;
    }

    /**
     * Setta il Sender del messaggio
     * @param s oggetto Sender del messaggio
     */
    public void setSender(AgentID s){
        this.sender = s;
    }

    /**
     * Restituisce il Sender del messaggio
     * @return AgentID L'oggetto restituito
     */
    public AgentID getSender(){
        return sender;
    }

    /**
     * Setta il Receiver del messaggio
     * @param r L'oggetto restituito
     */
    public void setReceiver(AgentID r){
        this.receiver = r;
    }

    /**
     * Restituisce il Receiver del messaggio
     * @return AgentID L'oggetto restituito
     */
    public AgentID getReceiver(){
        return receiver;
    }

    /**
     *
     */
    public Performative getPerformative(){
        return performer;
    }

    /**
     *
     */
    public void setPerformative(Performative p){
        this.performer = p;
    }

    /**
     *
     */
    public String getContent(){
        return content;
    }

    /**
     *
     */
    public void setContent(String c){
        this.content = c;
    }

    /**
     *
     */
    public Object getExtraArgument(){
	return extraArgument;
    }

    /**
     *
     */
    public void setExtraArgument(Object e){
	this.extraArgument = e;
    }

    /**
     *
     */
    @Override
    public String toString(){
	return "Performativa: " + performer + "\nSender: " + sender.toString() + "\nReceiver: " + receiver.toString() + "\nContent: " + content + "\nExtraArgument: " + extraArgument.toString();
    }
}
