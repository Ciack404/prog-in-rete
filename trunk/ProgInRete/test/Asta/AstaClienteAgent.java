package Asta;

/**

*/

import JAM.*;

public class AstaClienteAgent extends JAMAgent {
	public AstaClienteAgent(String name, String category) throws JAMADSLException {
        super(new PersonalAgentID(name, category));
	}

	public static void main(String[] args) throws JAMADSLException {
		AstaClienteAgent cliente1= new AstaClienteAgent("Mario Rossi", "Cliente");
		AstaClienteAgent cliente2= new AstaClienteAgent("Matteo Capello", "Cliente");
		AstaClienteAgent cliente3= new AstaClienteAgent("Massimo Bolognesi", "Cliente");
      cliente1.addBehaviour(new AstaClienteBehaviour(cliente1));
      cliente2.addBehaviour(new AstaClienteBehaviour(cliente2));
      cliente3.addBehaviour(new AstaClienteBehaviour(cliente3));
      cliente1.init();
      cliente1.start();
      cliente2.init();
      cliente2.start();
		cliente3.init();
      cliente3.start();
      
      /*cliente1.addInterface();
      cliente2.addInterface();
      cliente3.addInterface();*/
	}
}
