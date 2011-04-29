package JAM;

public class JAMMessageBoxException extends JAMException{

	public JAMMessageBoxException(){
			super();
	}

	public JAMMessageBoxException(String error){
			super(error);
	}

	public JAMMessageBoxException(String error,Throwable causa){
			super(error,causa);

	}

	public JAMMessageBoxException(Throwable causa){
			super(causa);
	}
}

