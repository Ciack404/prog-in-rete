package eccezioni;

public class JAMBehaviourInterruptedException extends JAMBehaviourException{

	public JAMBehaviourInterruptedException(){

			super();
			System.out.println("E' stata sollevata un'eccezione di tipo JAMBehaviourInterruptedException");
	}

	public JAMBehaviourInterruptedException(String messaggio){

			super(messaggio);
			System.out.println("E' stata sollevata un'eccezione di tipo JAMBehaviourInterruptedException");
	}

	public JAMBehaviourInterruptedException(String messaggio,Throwable causa){

			super(messaggio,causa);
			System.out.println("E' stata sollevata un'eccezione di tipo JAMBehaviourInterruptedException");
	}

	public JAMBehaviourInterruptedException(Throwable causa){

			super(causa);
			System.out.println("E' stata sollevata un'eccezione di tipo JAMBehaviourInterruptedException");
	}
}
