import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;




public class PersistenzaClass {
	 
	
	public void daDbALista() {
				
		// Nome del driver.
		String DRIVER = "com.mysql.jdbc.Driver";
		
		// Indirizzo del database.
		String DB_URL = "jdbc:mysql://localhost:3306/rubrica";
		
		try {
			// Carico il driver.
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			// Il driver non può essere caricato.
			System.out.println("Driver non trovato...");
			System.exit(1);
		}
		
		// Preparo il riferimento alla connessione.
		Connection connection = null;
		
		try {
			// Apro la connessione verso il database.
			connection = DriverManager.getConnection(DB_URL, "admRubrica", "rubricapass");
			// Ottengo lo Statement per interagire con il database.
			Statement statement = connection.createStatement();
			// Estrapolo dati con le query e li immagazzino in un oggetto Resultset
			ResultSet resultset = statement.executeQuery( "select * from contatti" );
			
			while (resultset.next()) {
									
				Persona nuovoContatto = new Persona ();
				
				String nome = resultset.getString(1);
				nuovoContatto.setNome(nome);
				
				String cognome = resultset.getString(2);
				nuovoContatto.setCognome(cognome);
				
				String telefono = resultset.getString(3);
				nuovoContatto.setTelefono(telefono);
				
				String indirizzo = resultset.getString(4);
				nuovoContatto.setIndirizzo(indirizzo);
				
				int eta = resultset.getInt(5);
				nuovoContatto.setEta(eta);
				
				
				Main.tabella.getModel().addContatto(nuovoContatto);
				Main.tabella.getModel().fireTableDataChanged();
				
				
			}
		
		} catch (SQLException e) {
			// In caso di errore...
			System.out.println("errore di connessione...");
		} finally {
			if (connection != null) {
				try {
		
					connection.close();
				} 
				catch (Exception e) {
				}
			}
		}
		
		
	 }
	
	
	
	 public void daFileALista() {
	    	
	    	try {
	    		
	    		
		    	Scanner righeScanner = new Scanner( new BufferedReader( new FileReader ("informazioni.txt")));
		    	
		    	while (righeScanner.hasNextLine()) {
					
		    		String riga = righeScanner.nextLine();
					
					Scanner tokenScanner = new Scanner(riga);
					tokenScanner.useDelimiter("\\s*;\\s*");
					
					Persona nuovoContatto = new Persona ();
					
					String nome = tokenScanner.next();
					nuovoContatto.setNome(nome);
					
					String cognome = tokenScanner.next();
					nuovoContatto.setCognome(cognome);
					
					String telefono = tokenScanner.next();
					nuovoContatto.setTelefono(telefono);
					
					String indirizzo = tokenScanner.next();
					nuovoContatto.setIndirizzo(indirizzo);
					
					String eta = tokenScanner.next();
					nuovoContatto.setEta(eta);
					
					
					Main.tabella.getModel().addContatto(nuovoContatto);
					Main.tabella.getModel().fireTableDataChanged();
					
					tokenScanner.close();
		    	}	
		    	
		    	
				righeScanner.close();
			
	    	} catch (Exception e) {
	    	
	        }

	    }
	 
	 
	 
	 
	 
	 public void daListaAFile() {
		 
		 FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("informazioni.txt");
				Vector<Persona> lista = Main.tabella.getModel().contatti;
				for (int i = 0; i < lista.size(); i++) {
					Persona contatto = lista.get(i);
					fileWriter.append(contatto.getNome()+";"+contatto.getCognome()+";"+contatto.getTelefono()+";"+contatto.getIndirizzo()+";"+contatto.getEta()+"\n");
				}     
				fileWriter.flush();
				fileWriter.close();
			} catch (Exception exc) {
				System.out.println(exc);
			}
	 }
	 
	 
	 
	 public void daListaADb() {
		 		 
				
			// Nome del driver.
			String DRIVER = "com.mysql.jdbc.Driver";		
				
			// Indirizzo del database.
			String DB_URL = "jdbc:mysql://localhost:3306/rubrica";
				
			try {
				// Carico il driver.
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				// Il driver non può essere caricato.
				System.out.println("Driver non trovato...");
				System.exit(1);
			}
				
			// Preparo il riferimento alla connessione.
			Connection connection = null;
				
			try {
				// Apro la connessione verso il database.
				connection = DriverManager.getConnection(DB_URL, "admRubrica", "rubricapass");
				// Ottengo lo statement per interagire con il database mettendo anche stringhe con apici e apostrofi
				PreparedStatement statement;
				statement = connection.prepareStatement("truncate contatti;");
				statement.executeUpdate();
				Vector<Persona> lista = Main.tabella.getModel().contatti;
				for (int i = 0; i < lista.size(); i++) {
					
					Persona contatto = lista.get(i);

					String nome = contatto.getNome();
					String cognome = contatto.getCognome();
					String telefono = contatto.getTelefono();
					String indirizzo = contatto.getIndirizzo();
					int eta = contatto.getEta();
					
					PreparedStatement statement2 = connection.prepareStatement(
							"INSERT INTO contatti ( " +
							" nome, cognome, telefono, indirizzo, eta " +
							") VALUES ( " +
							" ?, ?, ?, ?, ? " +
							")"
							);
					// Imposto i parametri.
					statement2.setString(1, nome);
					statement2.setString(2, cognome);
					statement2.setString(3, telefono);
					statement2.setString(4, indirizzo);
					statement2.setInt(5, eta);
					// Eseguo l'aggiornamento.
					statement2.executeUpdate();
					
				}
				
				
			} catch (SQLException e) {
					// In caso di errore...
					System.out.println("errore di connessione...");
			} catch (Exception e) {
					// In caso di errore...
					System.out.println("errore sconosciuto...");
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} 
					catch (Exception e) {
					}
				}
			}
			 
		 }
	 
	 
	 
	 
	 
	 
	 
}
