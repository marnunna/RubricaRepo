import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class FinestraPrincipale extends JFrame {
	
	JToolBar toolbar;
	JScrollPane panel_bottom;
	//JPanel panel_button;
	JButton pulsante_nuovo, pulsante_modifica, pulsante_elimina;
	
	public FinestraPrincipale(TabellaContatti tabella) {
		
		super("Rubrica");
		
		panel_bottom = new JScrollPane(tabella.getTable());
		
		//creo i bottoni e il pannello per i bottoni 
		
		//panel_button = new JPanel();
		toolbar = new JToolBar("rubrica toolbar");
		ImageIcon nuovo = new ImageIcon("nuovo.png");
		pulsante_nuovo = new JButton(nuovo);
		ImageIcon modifica = new ImageIcon("modifica.png");
		pulsante_modifica = new JButton(modifica);
		ImageIcon elimina = new ImageIcon("elimina.png");
		pulsante_elimina = new JButton(elimina);
		//panel_button.add(pulsante_nuovo);
		//panel_button.add(pulsante_modifica);
		//panel_button.add(pulsante_elimina);
		toolbar.add(pulsante_nuovo);
		toolbar.add(pulsante_modifica);
		toolbar.add(pulsante_elimina);
		
		//implemento la gestione degli eventi
		
		NewListener newl= new NewListener();
		pulsante_nuovo.addActionListener(newl);
		
		ModListener modl = new ModListener();
		pulsante_modifica.addActionListener(modl);
		
		DelListener dell = new DelListener();
		pulsante_elimina.addActionListener(dell);
		
		// aggiungo i componenti alla finestra
		add(panel_bottom, BorderLayout.CENTER);
		//add(panel_button, BorderLayout.PAGE_END);		
		add(toolbar, BorderLayout.PAGE_START);
	}
	
	public void init() {
		
		// imposto le dimensioni della finestra
		this.setSize(900,550);
		//window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		// scelgo che cosa succederà quando si chiuderà la finestra
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// rendo visibile la finestra
		this.setVisible(true);
		// centro la finestra nello schermo
		this.setLocationRelativeTo(null);
		
	}
	
	// creo le classi listener interne
	private class NewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			EditorPersona ep = new EditorPersona();
			ep.init();		
						
		}
	}
	
	private class ModListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int rigaSel = Main.tabella.getTable().getSelectedRow();
			
			if (rigaSel != -1)  {
				
				Persona selezionata = Main.tabella.getModel().getContatto(rigaSel);
				//EditorPersona ep = new EditorPersona(selezionata);
				ModEditorPersona ep = new ModEditorPersona(selezionata);
				ep.init();
			} 
			else { JOptionPane.showMessageDialog(null, "Per modificare una persona devi selezionarla!"); }
			
		}
	}
	
	private class DelListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			int rigaSel = Main.tabella.getTable().getSelectedRow();
			
			if (rigaSel != -1)  {
				
				String nome = Main.tabella.getModel().getContatto(rigaSel).getNome();
				String cognome = Main.tabella.getModel().getContatto(rigaSel).getCognome();
				
				if (JOptionPane.showConfirmDialog(null, "Eliminare "+nome+" "+cognome+"?", "ATTENZIONE", JOptionPane.YES_NO_OPTION) == 0) {
					
					Main.tabella.getModel().removeContatto(rigaSel);
					Main.tabella.getModel().fireTableDataChanged(); 
					
					//Main.pc.daListaAFile();
					Main.pc.daListaADb();
				
				} 
				
			}
			else { JOptionPane.showMessageDialog(null, "Per eliminare devi selezionare una persona"); }
		}
	}
	
	
}