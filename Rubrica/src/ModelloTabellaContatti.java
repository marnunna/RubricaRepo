import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class ModelloTabellaContatti extends AbstractTableModel {
    
	Vector<Persona> contatti;
    String[] headers;

    public ModelloTabellaContatti()
    {
    	contatti = new Vector<Persona>();
        headers = new String[]{"Nome", "Cognome", "Telefono"};
    }

    // numero di righe
    public int getRowCount()
    {
        return this.contatti.size();
    }

    // numero di colonne
    public int getColumnCount()
    {
        return headers.length;
    }

    // nome di una determinata colonna
    @Override
    public String getColumnName(int columnIndex)
    {
        return headers[columnIndex];
    }

    // valore di una determinata cella
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Persona row = contatti.elementAt(rowIndex);
        if(columnIndex == 0)
        {
            return row.getNome();
        }
        else if(columnIndex == 1)
        {
            return row.getCognome();
        }
        else if(columnIndex == 2)
        {
            return row.getTelefono();
        }

        return null;
    }

    // setto il valore di una cella
    // viene richiamato subito dopo l'editing fatto attraverso la GUI
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
        Persona row = this.contatti.elementAt(rowIndex);
        String strValue = (String)value;
        if(columnIndex == 0)
        {
            row.setNome(strValue);
        }
        else if(columnIndex == 1)
        {
            row.setCognome(strValue);
        }
        else if(columnIndex == 2)
        {
            row.setTelefono(strValue);
        }
    }

 
    @Override
    public boolean isCellEditable(int rowIndex, int colIndex)
    {
        return false;
    }


    // aggiungo un contatto
    public void addContatto(Persona contatto) {
    	
    	this.contatti.add(contatto);
    } 
    
    public void removeContatto(int row) {
    	
    	this.contatti.remove(row);
    }
    
    public Persona getContatto(int row) {
    	
    	return this.contatti.elementAt(row);
    }
    
    
   
    
	
}


