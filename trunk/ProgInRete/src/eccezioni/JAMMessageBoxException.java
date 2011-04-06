package eccezioni;

public class JAMMessageBoxException extends JAMException{

	public JAMMessageBoxException(){
			super();
			System.out.println("E' stata sollevata un'eccezione di tipo MessageBox");
	}

	public JAMMessageBoxException(String error){
			super(error);
			System.out.println("E' stata sollevata un'eccezione di tipo MessageBox");
	}

	public JAMMessageBoxException(String error,Throwable causa){
			super(error,causa);
			System.out.println("E' stata sollevata un'eccezione di tipo MessageBox");

	}

	public JAMMessageBoxException(Throwable causa){
			super(causa);
			System.out.println("E' stata sollevata un'eccezione di tipo MessageBox");
	}
}

