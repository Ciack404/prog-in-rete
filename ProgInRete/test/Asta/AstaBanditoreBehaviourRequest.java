package Asta;

/**
 * @(#)AstaBanditoreBehaviourRequest.java
 *
 *
 * @author Capello Matteo, Bolognesi Massimo
 * @version 1.00 2009/2/27
 */

import JAM.*;


public class AstaBanditoreBehaviourRequest extends JAMWhileBehaviour {
	private Oggetto oggetto;
	private Message mex, bestOffer;
	private LogFile file;

	public AstaBanditoreBehaviourRequest (JAMAgent agent, Oggetto obj) {
		super(agent);
		oggetto=obj;
		file=new LogFile();
	}

	/**
	 *
	 */
 	public void setup() throws JAMBehaviourInterruptedException {
		try {
			file.startLog(myAgent.getMyID().toString(),
				"Log file di "+myAgent.getMyID());
			file.log(myAgent.getMyID()+": oggetto all'asta: "+oggetto.getNome()+
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
			mex=myAgent.receive();
			file.log(myAgent.getMyID()+": ricevuta offerta da "+mex.getSender());
			if(mex.getPerformative()!=Performative.REQUEST) {//Ricevo un'offerta
				file.log(myAgent.getMyID()+": performativa offerta errata");
         	Message request=new Message(
            	myAgent.getMyID(),
               mex.getSender(),
               Performative.REFUSE,
               "Performativa non accettata",
               mex);
				myAgent.send(request);
			}
			else {
				int offerta=-1; //controlla il possibile fallimento del parseInt
				try {
					offerta=Integer.parseInt(mex.getContent());
				}
				catch (NumberFormatException err2) { //Offerta non valida
					file.log(myAgent.getMyID()+": parametro offerta errato");
					Message non_capito=new Message(
           			myAgent.getMyID(),
            		mex.getSender(),
            		Performative.NOT_UNDERSTOOD,
            		"Offerta non valida",
             		mex);
					myAgent.send(non_capito);
				}
				if (offerta!=-1) {
					file.log(myAgent.getMyID()+": l'offerta di "+mex.getSender()+"e' corretta");
					if (offerta>oggetto.getPrezzoCorrente()) { //Se l'offerta e' la migliore
						oggetto.setPrezzoCorrente(offerta); //Salvo il nuovo prezzo...
						bestOffer=mex; //...e chi ha fatto l'offerta
						file.log(myAgent.getMyID()+": l'offerta di "+offerta+" da parte di "+
							bestOffer.getSender()+"e' la migliore");
						Message inform=new Message(
	            		myAgent.getMyID(),
	            	   mex.getSender(),
	            	   Performative.INFORM,
	            	   oggetto.toString(),
	            	   mex);
						myAgent.send(inform);
					}
					else { //Offerta valida ma non sufficientemente alta
						Message refuse=new Message(
		            	myAgent.getMyID(),
		               mex.getSender(),
		               Performative.REFUSE,
		               oggetto.toString(),
		               mex);
						myAgent.send(refuse);
						file.log(myAgent.getMyID()+": l'offerta di "+offerta+" da parte di "+
							mex.getSender()+" non e' abbastanza alta-> REFUSE");
					}
					sleep(1000);
					if (!myAgent.isThereMessage(Performative.REQUEST)) {//Se nessuno ha rilanciato...
						file.log(myAgent.getMyID()+": nessuno ha rilanciato l'offerta di "+
							bestOffer.getSender());
						Message victory= new Message( //..informo il vincitore della vittoria...
		            	myAgent.getMyID(),
		               bestOffer.getSender(),
		               Performative.INFORM,
		               oggetto.toString(),
		               mex);
		            file.log(myAgent.getMyID()+": informo "+mex.getSender()+
		            	" della vittoria");
						myAgent.send(victory);
						done(); //...e chiudo l'asta
					}
				}
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

 	/**
 	 * Stampo chi si e'�aggiudicato l'oggetto e a quanto.
 	 */
 	public void dispose() throws JAMBehaviourInterruptedException {
 		try{
 			file.log(myAgent.getMyID()+": l'oggetto "+oggetto.getNome()+ " e` stato "+
	 			"aggiudicato da "+ bestOffer.getSender()+" per "+oggetto.getPrezzoCorrente());
			file.endLog();
			done();
 		}
 		catch(JAMIOException e) {
      	System.out.println("Errore: "+e);
         done();
      }
		System.exit(1);
 	}
}
