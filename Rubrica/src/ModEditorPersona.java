import java.awt.event.ActionEvent;


public class ModEditorPersona extends EditorPersona {
	
	Persona contatto = null;
	
	public ModEditorPersona(Persona contatto) {
		
		super();
		this.contatto = contatto;
		valueNome.setText(contatto.getNome());
		valueCognome.setText(contatto.getCognome());
		valueTelefono.setText(contatto.getTelefono());
		valueIndirizzo.setText(contatto.getIndirizzo());
		valueEta.setText( Integer.toString(contatto.getEta()));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == pulsante_annulla) {
			this.dispose();
		}
		else if (e.getSource() == pulsante_salva) {
			
			String nome = valueNome.getText();
			String cognome = valueCognome.getText();
			String telefono = valueTelefono.getText();
			String indirizzo = valueIndirizzo.getText();
			String eta = valueEta.getText();
			
			contatto.setNome(nome);
			contatto.setCognome(cognome);
			contatto.setTelefono(telefono);
			contatto.setIndirizzo(indirizzo);
			contatto.setEta(eta);
			
			Main.tabella.getModel().fireTableDataChanged();
			
			//Main.pc.daListaAFile();
			Main.pc.daListaADb();
						
		}
			
			this.dispose();
		
	}
}
