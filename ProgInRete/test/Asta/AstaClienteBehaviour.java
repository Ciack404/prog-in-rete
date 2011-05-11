package Asta;

/**
 * @(#)AstaClienteBehaviour.java
 *
 *
 * @author
 * @version 1.00 2009/2/27
 */
import java.lang.*;
import java.util.*;
import JAM.*;


public class AstaClienteBehaviour extends JAMWhileBehaviour {
	private int saldo, offerta, prezzo;
	private LogFile file;
	boolean vinto;

	public AstaClienteBehaviour (JAMAgent agent) {
		super(agent);
		file=new LogFile();
		vinto=false;
	}


	/**
	 * Inizializzo il mio portafoglio con una dotazione di euro
	 * a caso compresa in un certo range di valori
	 */
	public void setup() throws JAMBehaviourInterruptedException {
   	saldo=(int)(Math.random() * 20000);
		try {
			file.startLog(myAgent.getMyID().toString(),"Log file di "+myAgent.getMyID());
			file.log("1- Saldo iniziale: "+saldo);
		}
		catch (JAMIOException e) {
			System.out.println("Errore: "+e);
         done();
		}
   }

	/**
	 * Stampo il risultato dell'asta, se mi sono aggiudicato o no
	 * l'oggetto e, nel caso, quanto ho speso
	 */
	public void dispose() throws JAMBehaviourInterruptedException {
		try {
			file.log(myAgent.getMyID()+": vinto="+vinto);
			if (vinto) file.log(myAgent.getMyID()+": HO VINTO!!!"+
				" Ho speso "+offerta+" e mi sono rimasti "+saldo);
			file.log(myAgent.getMyID()+": FINITO\n");
			file.endLog();
		}
		catch (JAMIOException e) {
			System.out.println("Errore: "+e);
         done();
		}
	}

	/**
	 * Se ho soldi nel portafoglio chiedo il valore corrente
	 * dell'oggetto in asta. Se � ancora in asta e non sono io
	 * il migliore offerente effettuo un'offerta superiore a
	 * quella miglore (es. incrementando di un valore random la
	 * migliore offerta corrente), invio la mia offerta e attendo
	 * la risposta.
	 * Se la mia offerta � accettata, mi riposo qualche secondo
	 * e poi vado nuovamente a verificare se sono rimasto il miglior
	 * offerente. Se la mia offerta non � accettata riconmincio da
	 * capo; se non ho soldi sufficienti per rilanciare mi ritiro.
	 * Rispondo a seconda del messaggio letto
	 */
	public void action() throws JAMBehaviourInterruptedException {
		CategoryAgentID query= new CategoryAgentID("Query");
		CategoryAgentID request= new CategoryAgentID("Request");
		try{// Chiedo il prezzo
			Message queryIf= new Message(
				myAgent.getMyID(),
            query,
            Performative.QUERY_IF,
            "Valore?",
            null);
			myAgent.send(queryIf);
			file.log(myAgent.getMyID()+": inviata QUERY_IF\n");

			Message risp= myAgent.receive(query);
			if(risp.getPerformative()!=Performative.AGREE) {
				file.log(myAgent.getMyID()+": richiesta QUERY_IF respinta!");
				done();
			}
			file.log(myAgent.getMyID()+": ricevuta AGREE");
			
			prezzo=Integer.parseInt(risp.getContent());
			file.log(myAgent.getMyID()+": Prezzo ricevuto= "+prezzo);
			
			if (prezzo<=saldo) { //Se posso faccio un'offerta
				file.log(myAgent.getMyID()+": penso all'offerta da fare...");
				offerta=(int)(Math.random()*2000)+prezzo+1;
				while (offerta>saldo) 
					offerta=(int)(Math.random()*2000)+prezzo;
				file.log(myAgent.getMyID()+": offro "+offerta+"!\n");

				Message req= new Message(
					myAgent.getMyID(),
	            request,
   	         Performative.REQUEST,
   	         String.valueOf(offerta),
   	         null);
				myAgent.send(req);
				file.log(myAgent.getMyID()+": offerta inviata");

				risp= myAgent.receive(request);
				if (risp.getPerformative()==Performative.INFORM) {//La mia offerta e' stata accettata
					file.log(myAgent.getMyID()+": sono momentaneamente in testa con "+offerta);
					sleep(1300);
					if (myAgent.isThereMessage(Performative.INFORM)) {//Ho vinto l'asta
						saldo-=offerta;
						vinto=true;
						done();
					}
					else file.log(myAgent.getMyID()+": offerta superata.. rilancio!!");
				}
				else file.log(myAgent.getMyID()+": offerta respinta.. rilancio!!");
			}
			else { //Non ho abbastanza soldi
				file.log(myAgent.getMyID()+": troppo alto... mi ritiro!!");
				done();
			}
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
}
