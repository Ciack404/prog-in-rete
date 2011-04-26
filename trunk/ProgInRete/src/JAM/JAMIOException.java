package JAM;

public class JAMIOException extends JAMException{

	public JAMIOException(){
			super();
			System.out.println("E' stata sollevata un'eccezione di tipo JAMIO");
	}

	public JAMIOException(String messaggio){
			super(messaggio);
			System.out.println("E' stata sollevata un'eccezione di tipo JAMIO");
	}

	public JAMIOException(String messaggio,Throwable causa){
			super(messaggio,causa);
			System.out.println("E' stata sollevata un'eccezione di tipo JAMIO");
	}

	public JAMIOException(Throwable causa){
			super(causa);
			System.out.println("E' stata sollevata un'eccezione di tipo JAMIO");
	}
}
