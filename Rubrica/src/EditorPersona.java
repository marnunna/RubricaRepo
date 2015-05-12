import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EditorPersona extends JFrame implements ActionListener {
	
	JPanel panello_cx;
	JLabel fieldNome, fieldCognome, fieldTelefono, fieldIndirizzo, fieldEta;
	JTextField valueNome, valueCognome, valueTelefono, valueIndirizzo, valueEta;
	
	JPanel panel_button;
	JButton pulsante_salva, pulsante_annulla;

	public EditorPersona() {
		
		super("Editor persona");
		
		panello_cx = new JPanel();
		panello_cx.setLayout(new GridLayout(5, 2, 10, 10));
		
		fieldNome = new JLabel ("nome:");
		valueNome = new JTextField();
		panello_cx.add(fieldNome);
		panello_cx.add(valueNome);
		
		fieldCognome = new JLabel ("cognome:");
		valueCognome = new JTextField();
		panello_cx.add(fieldCognome);
		panello_cx.add(valueCognome);
		
		fieldTelefono = new JLabel ("telefono:");
		valueTelefono = new JTextField();
		panello_cx.add(fieldTelefono);
		panello_cx.add(valueTelefono);
		
		fieldIndirizzo = new JLabel ("indirizzo:");
		valueIndirizzo = new JTextField();
		panello_cx.add(fieldIndirizzo);
		panello_cx.add(valueIndirizzo);
		
		fieldEta = new JLabel ("eta:");
		valueEta = new JTextField();
		panello_cx.add(fieldEta);
		panello_cx.add(valueEta);
		
		
		//creo i bottoni e il pannello per i bottoni 
		panel_button = new JPanel();
		pulsante_salva = new JButton("SALVA");
		pulsante_annulla = new JButton("ANNULLA");
		panel_button.add(pulsante_salva);
		panel_button.add(pulsante_annulla);
		
		//implemento la gestione eventi
		pulsante_annulla.addActionListener(this);
		pulsante_salva.addActionListener(this);
		
		add(panello_cx, BorderLayout.CENTER);
		add(panel_button, BorderLayout.PAGE_END);	
		
		
	}
	
	
	public void init() {
		
		// imposto le dimensioni della finestra
		this.setSize(600,300); 
		// scelgo che cosa succederà quando si chiuderà la finestra
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// rendo visibile la finestra
		this.setVisible(true);
		// centro la finestra nello schermo
		this.setLocationRelativeTo(null);
		
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
							
			Persona nuovoContatto = new Persona();
			nuovoContatto.setNome(nome);
			nuovoContatto.setCognome(cognome);
			nuovoContatto.setTelefono(telefono);
			nuovoContatto.setIndirizzo(indirizzo);
			nuovoContatto.setEta(eta);
			
			Main.tabella.getModel().addContatto(nuovoContatto);
			Main.tabella.getModel().fireTableDataChanged();
			
			//Main.pc.daListaAFile();	
			Main.pc.daListaADb();
			
			this.dispose();
		}
	}
	
}
