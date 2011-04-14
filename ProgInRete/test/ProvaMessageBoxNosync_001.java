import java.rmi.RemoteException;
import Parte1.*;
import Parte2.*;
import eccezioni.*;

public class ProvaMessageBoxNosync_001 {

	public static void main(String[] args) throws JAMMessageBoxException, RemoteException{

		Message stampa;
		boolean isThere;

		PersonalAgentID proprietario = new PersonalAgentID("mattia", "francesco");


		AgentID agente1 = new CategoryAgentID("Impiegato");
		AgentID agente2 = new PersonalAgentID("Marco", "Rossi");
		AgentID agente3 = new PersonalAgentID("Alberto", "Gosso");
		AgentID agente4 = new CategoryAgentID("Manager");
		AgentID agente5 = new PersonalAgentID("Beppe", "Siragusa");
		AgentID agente6 = new PersonalAgentID("Marco", "Passet");
		AgentID agente7 = new PersonalAgentID("Mattia", "Camusso");
		AgentID agente8 = new PersonalAgentID("Francesco", "Alisetta");


        //create caselle di posta
		MessageBoxNoSync boxMessaggi1 = new MessageBoxNoSync(proprietario);
		//default capacity costruttore vuoto

		//creati mex
		Message mex1 = new Message(Performative.UNKNOWN, "agente1", "agente2", agente1, agente2);
		Message mex2 = new Message(Performative.REQUEST, "agente2", "agente3", agente2, agente3);
		Message mex3 = new Message(Performative.INFORM, "agente3", "agente4", agente3, agente4);
		Message mex4 = new Message(Performative.INFORM, "agente4", "agente5", agente4, agente5);
		Message mex5 = new Message(Performative.UNKNOWN, "agente5", "agente6", agente5, agente6);
		Message mex6 = new Message(Performative.REQUEST, "agente6", "agente7", agente6, agente7);
		Message mex7 = new Message(Performative.INFORM, "agente7", "agente8", agente7, agente8);
		Message mex8 = new Message(Performative.REQUEST, "agente8", "agente1", agente8, agente1);

		boxMessaggi1.writeMessage(mex1);
		boxMessaggi1.writeMessage(mex2);
		boxMessaggi1.writeMessage(mex3);
		boxMessaggi1.writeMessage(mex4);

		//legge mex1
		System.out.println("stampa il primo messaggio in coda nella boxMessaggi1\n");
                try{
		stampa = boxMessaggi1.readMessage();
		System.out.println(stampa + "\n");
                }catch(JAMMessageBoxException jmbe){
                    System.out.println("error in mex1 read test");
                }

		//legge mex2
		System.out.println("stampa il primo messaggio corrispondende all'agente nella boxMessaggi1\n");
		try{
                stampa = boxMessaggi1.readMessage(agente2);
		System.out.println(stampa + "\n");
                }catch(JAMMessageBoxException jmbe){
                    System.out.println("error in mex2 read test");
                }

		//legge mex3
		/*modifica inform INFORM*/
		System.out.println("stampa il primo messaggio con performativa REQUEST in boxMessaggi1\n");
		try{
                stampa = boxMessaggi1.readMessage(Performative.REQUEST);
		System.out.println(stampa + "\n");
                }catch(JAMMessageBoxException jmbe){
                    System.out.println("error in mex3 read test");
                }

		//legge mex4
		System.out.println("stampa il primo messaggio con agente4 e Perf.INFORM boxMessaggi1\n");
                try{
		stampa = boxMessaggi1.readMessage(agente4, Performative.INFORM);
		System.out.println(stampa + "\n");
                }catch(JAMMessageBoxException jmbe){
                    System.out.println("error in mex4 read test");
                }

		boxMessaggi1.writeMessage(mex5);
		boxMessaggi1.writeMessage(mex6);
		boxMessaggi1.writeMessage(mex7);
		boxMessaggi1.writeMessage(mex8);

		//legge mex5
		System.out.print("1) primo messaggio in coda trovato ?  ");
		isThere = boxMessaggi1.isThereMessage();
		System.out.print(isThere+ "\n");
		System.out.print("\n");

		//legge mex6
		System.out.print("2) primo messaggio in coda di un certo agente trovato ?  ");
		isThere = boxMessaggi1.isThereMessage(agente6);
		System.out.print(isThere + "\n");
		System.out.print("\n");


		//legge mex7
		System.out.print("3) primo messaggio in coda di una certa performativa trovato ?  ");
		isThere = boxMessaggi1.isThereMessage(Performative.INFORM);
		System.out.print(isThere + "\n");
		System.out.print("\n");

		//legge mex8
		System.out.print("4) primo messaggio in coda di un certo agente + performativa trovato ?  ");
		isThere = boxMessaggi1.isThereMessage(agente8, Performative.REQUEST);
		System.out.print(isThere + "\n");
		System.out.print("\n");



	}//end main


}//end class