package Asta;

/**
 * @(#)AstaBanditoreBehaviourQuery.java
 *
 *
 * @author Capello Matteo, Bolognesi Massimo
 * @version 1.00 2009/2/27
 */

import JAM.*;


public class AstaBanditoreBehaviourQuery extends JAMWhileBehaviour {
	private Oggetto oggetto;
	private Message bestOffer;
	private LogFile file;

	public AstaBanditoreBehaviourQuery (JAMAgent agent, Oggetto obj) {
		super(agent);
		oggetto=obj;
		file=new LogFile();
	}

	/**
	 * Inizializzo il valore di base dell'oggetto all'asta
	 */
 	public void setup() throws JAMBehaviourInterruptedException {
		oggetto.setNome("KORG Oasys");
		oggetto.setPrezzoBase(8000);
		oggetto.setPrezzoCorrente(8000);
		try {
			file.startLog(myAgent.getMyID().toString(),
				"Log file di "+myAgent.getMyID());
			file.log(myAgent.getMyID()+ "Oggetto all'asta: "+oggetto.getNome()+
			"\nPrezzo di partenza: "+oggetto.getPrezzoBase());
		}
		catch(JAMIOException e) {
      	System.out.println("Errore: "+e);
         done();
      }
 	}

 	/**
 	 * Leggo un messaggio, lo analizzo e rispondo di conseguenza
 	 */
 	public void action() throws JAMBehaviourInterruptedException {
 		try {
			Message mex=myAgent.receive();
			file.log(myAgent.getMyID()+": ricevuta richiesta da "+mex.getSender());
			if(mex.getPerformative()!=Performative.QUERY_IF) {
         	Message request=new Message(myAgent.getMyID(), mex.getSender(),
         	Performative.REFUSE, "Performativa non accettata", mex);
				myAgent.send(request);
			}
			else if(!mex.getContent().equals("Valore?")) {
		   	Message non_capito=new Message(myAgent.getMyID(), mex.getSender(),
               Performative.NOT_UNDERSTOOD,"Domanda non valida", mex);
				myAgent.send(non_capito);
			}
			else {
				file.log(myAgent.getMyID()+": richiesta accettata... preparo risposta");
            Message agree=new Message(myAgent.getMyID(), mex.getSender(),
               Performative.AGREE, oggetto.toString(), mex);
				myAgent.send(agree);
			}
			file.log(myAgent.getMyID()+": inviato prezzo a "+mex.getSender());;
			sleep(100);
		}
		catch(JAMADSLException err1) {
      	System.out.println("Errore: "+err1);
         done();
		}
      catch(JAMIOException err2) {
      	System.out.println("Errore: "+err2);
         done();
      }
 	}

 	/**
 	 *
 	 */
 	public void dispose() throws JAMBehaviourInterruptedException {
 		file.endLog();
		System.exit(1);
 	}
}
