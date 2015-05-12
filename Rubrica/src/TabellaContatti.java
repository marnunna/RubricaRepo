import javax.swing.JTable;


public class TabellaContatti {
	
	JTable table;
	ModelloTabellaContatti model;
	
	
	public TabellaContatti() {
		
		model = new ModelloTabellaContatti();
		this.table = new JTable(model);
	}
	
	
	public JTable getTable() {
		
		return table;
	}
	
	public ModelloTabellaContatti getModel() {
		
		return model;
	}
	
	
}
