
public class Main {
	static TabellaContatti tabella;
	static FinestraPrincipale gui;
	static PersistenzaClass pc;
	
	public static void main (String args[]) {
		
		tabella = new TabellaContatti();
		gui = new FinestraPrincipale(tabella);
		gui.init();
		pc = new PersistenzaClass();
		pc.daDbALista();
		//pc.daFileALista();
		
	}
}
