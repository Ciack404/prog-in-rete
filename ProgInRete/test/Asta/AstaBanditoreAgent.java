package Asta;
/**

*/

import JAM.*;

public class AstaBanditoreAgent extends JAMAgent {
	public AstaBanditoreAgent(String name, String category) throws JAMADSLException {
        super(new PersonalAgentID(name, category));
	}

	public static void main(String[] args) throws JAMADSLException {
		Oggetto oggetto=new Oggetto();
		AstaBanditoreAgent banditoreQuery= new AstaBanditoreAgent("Roberto Baffo", "Query");
		AstaBanditoreAgent banditoreRequest= new AstaBanditoreAgent("Roberto Baffo", "Request");
		banditoreQuery.addBehaviour(new AstaBanditoreBehaviourQuery(banditoreQuery, oggetto));
		banditoreRequest.addBehaviour(new AstaBanditoreBehaviourRequest(banditoreRequest, oggetto));
		banditoreQuery.init();
		banditoreQuery.start();
		banditoreRequest.init();
		banditoreRequest.start();
	}
}
