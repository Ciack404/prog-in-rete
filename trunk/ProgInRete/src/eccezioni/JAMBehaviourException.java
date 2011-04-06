package eccezioni;

public class JAMBehaviourException extends JAMException{

	public JAMBehaviourException(){

			super();
			System.out.println("E' stata sollevata un'eccezione di tipo JAMBehaviour");
	}

	public JAMBehaviourException(String messaggio){

			super(messaggio);
			System.out.println("E' stata sollevata un'eccezione di tipo JAMBehaviour");
	}

	public JAMBehaviourException(String messaggio,Throwable causa){

			super(messaggio,causa);
			System.out.println("E' stata sollevata un'eccezione di tipo JAMBehaviour");
	}

	public JAMBehaviourException(Throwable causa){

			super(causa);
			System.out.println("E' stata sollevata un'eccezione di tipo JAMBehaviour");
	}
}
