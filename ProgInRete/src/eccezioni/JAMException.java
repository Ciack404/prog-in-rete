package eccezioni;

public class JAMException extends Exception{

	public JAMException(){
	
		super();
	}
	
	public JAMException(String messaggio){
	
		super(messaggio);
	}
	
	public JAMException(String messaggio,Throwable causa){
	
		super(messaggio,causa);
	}
	
	public JAMException(Throwable causa){
	
		super(causa);
	}
}
