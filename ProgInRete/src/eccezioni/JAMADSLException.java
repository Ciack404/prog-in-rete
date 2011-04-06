package eccezioni;

public class JAMADSLException extends JAMException{

	public JAMADSLException(){

			super();
			System.out.println("E' stata sollevata un'eccezione di tipo ADSL");
	}

	public JAMADSLException(String messaggio){

			super(messaggio);
			System.out.println("E' stata sollevata un'eccezione di tipo ADSL");
	}

	public JAMADSLException(String messaggio,Throwable causa){

			super(messaggio,causa);
			System.out.println("E' stata sollevata un'eccezione di tipo ADSL");
	}

	public JAMADSLException(Throwable causa){

			super(causa);
			System.out.println("E' stata sollevata un'eccezione di tipo ADSL");
	}



}
