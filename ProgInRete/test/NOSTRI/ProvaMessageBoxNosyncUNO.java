package NOSTRI;

import java.rmi.RemoteException;
import JAM.*;
import java.rmi.*;
import java.rmi.server.*;

public class ProvaMessageBoxNosyncUNO{
	public static void main(String[] args) {
            
		Message stampa;
		boolean trovato;

		PersonalAgentID proprietario = new PersonalAgentID("mattia", "francesco");


		AgentID agente1 = new CategoryAgentID("Impiegato");
		AgentID agente2 = new PersonalAgentID("Marco", "Rossi");
		AgentID agente3 = new PersonalAgentID("Alberto", "Gosso");
		AgentID agente4 = new CategoryAgentID("Manager");
		AgentID agente5 = new PersonalAgentID("Beppe", "Siragusa");
		AgentID agente6 = new PersonalAgentID("Marco", "Passet");
		AgentID agente7 = new PersonalAgentID("Mattia", "Camusso");
		AgentID agente8 = new PersonalAgentID("Francesco", "Alisetta");

             try{
        //create caselle di posta
		MessageBoxNoSync boxMessaggi1 = new MessageBoxNoSync(proprietario);
		//default capacity costruttore vuoto

		//creati mex
		Message mex1 = new Message(agente1, agente2, Performative.UNKNOWN, "agente1", "agente2");
		Message mex2 = new Message(agente2, agente3, Performative.REQUEST, "agente2", "agente3");
		Message mex3 = new Message(agente3, agente4, Performative.INFORM, "agente3", "agente4");
		Message mex4 = new Message(agente4, agente5, Performative.INFORM, "agente4", "agente5");
		Message mex5 = new Message(agente5, agente6, Performative.UNKNOWN, "agente5", "agente6");
		Message mex6 = new Message(agente6, agente7, Performative.REQUEST, "agente6", "agente7");
		Message mex7 = new Message(agente7, agente8, Performative.INFORM, "agente7", "agente8");
		Message mex8 = new Message(agente8, agente1, Performative.REQUEST, "agente8", "agente1");

                    boxMessaggi1.writeMessage(mex1);
                    boxMessaggi1.writeMessage(mex2);
                    boxMessaggi1.writeMessage(mex3);
                    boxMessaggi1.writeMessage(mex4);

		//legge mex1
		System.out.println("stampa il primo messaggio in coda nella boxMessaggi1\n");
                try{
		stampa = boxMessaggi1.readMessage();
		System.out.println(stampa + "\n");
                }catch(Exception jmbe){
                    System.out.println("error in mex1 read test");
                }

		//legge mex2
		System.out.println("stampa il primo messaggio corrispondende all'agente nella boxMessaggi1\n");
		try{
                stampa = boxMessaggi1.readMessage(agente2);
		System.out.println(stampa + "\n");
                }catch(Exception jmbe){
                    System.out.println("error in mex2 read test");
                }

		//legge mex3
		/*modifica inform INFORM*/
		System.out.println("stampa il primo messaggio con performativa REQUEST in boxMessaggi1\n");
		try{
                stampa = boxMessaggi1.readMessage(Performative.REQUEST);
		System.out.println(stampa + "\n");
                }catch(Exception jmbe){
                    System.out.println("error in mex3 read test");
                }

		//legge mex4
		System.out.println("stampa il primo messaggio con agente4 e Perf.INFORM boxMessaggi1\n");
                try{
		stampa = boxMessaggi1.readMessage(agente4, Performative.INFORM);
		System.out.println(stampa + "\n");
                }catch(Exception jmbe){
                    System.out.println("error in mex4 read test");
                }

                try{
                    boxMessaggi1.writeMessage(mex5);
                    boxMessaggi1.writeMessage(mex6);
                    boxMessaggi1.writeMessage(mex7);
                    boxMessaggi1.writeMessage(mex8);
                }catch(InterruptedException e){}catch(JAMMessageBoxException ex){}

		//legge mex5
		System.out.print("1) primo messaggio in coda trovato ?  ");
		trovato = boxMessaggi1.isThereMessage();
		System.out.println(trovato);
		System.out.println();

		//legge mex6
		System.out.print("2) primo messaggio in coda di un certo agente trovato ?  ");
		trovato = boxMessaggi1.isThereMessage(agente6);
		System.out.println(trovato);
		System.out.println();


		//legge mex7
		System.out.print("3) primo messaggio in coda di una certa performativa trovato ?  ");
		trovato = boxMessaggi1.isThereMessage(Performative.INFORM);
		System.out.println(trovato);
		System.out.print("\n");

		//legge mex8
		System.out.print("4) primo messaggio in coda di un certo agente + performativa trovato ?  ");
		trovato = boxMessaggi1.isThereMessage(agente8, Performative.REQUEST);
		System.out.println(trovato);
                System.out.println();
            }catch(Exception e){}
	}//end main
}//end class
