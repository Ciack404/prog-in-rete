package Asta;

/**


*/


public class Oggetto {
	private int prezzoBase, prezzoCorrente;
	private String nome;

	public Oggetto() { }

	public Oggetto (int p, String n) {
		prezzoBase=p;
		prezzoCorrente=p;
		nome=n;
	}

	public int getPrezzoCorrente() {
		return prezzoCorrente;
	}

	public int getPrezzoBase() {
		return prezzoBase;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String n) {
		nome=n;
	}

	public void setPrezzoCorrente(int p) {
		prezzoCorrente=p;
	}

	public void setPrezzoBase(int p) {
			prezzoBase=p;
	}

	public String toString() {
		return prezzoCorrente+"";
	}
}
