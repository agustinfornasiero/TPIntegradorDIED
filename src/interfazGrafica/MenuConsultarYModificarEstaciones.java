package interfazGrafica;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class MenuConsultarYModificarEstaciones extends JPanel implements TableModelListener
{
	@SuppressWarnings("serial")
	class ModeloTablaEstaciones extends AbstractTableModel
	{
		private String[] nombreColumnas = {"Id", "Nombre", "Horario apertura", "Horario cierre", "Estado"};
		private Object[][] datos = {
										{"", "", "", ""}
								   };
		 
		public void setData(Object[][] datos) {
		 	this.datos = datos;
		}
		    
		public int getColumnCount() {
			return nombreColumnas.length;
		}

		public int getRowCount() {
		    return datos.length;
		 }

		public String getColumnName(int col) {
		    return nombreColumnas[col];
		}

		public Object getValueAt(int row, int col) {
		    return datos[row][col];
		}

		public Class getColumnClass(int c) {
		    return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
		    return true;
		}

		public void setValueAt(Object value, int row, int col) {
		    datos[row][col] = value;
		        // Lanza un evento notificando que la celda de fila/columna cambio
		    fireTableCellUpdated(row, col);
		}
	 }
	
	private GridBagConstraints gbc;
	private JButton btn1;
	private JTable t;
	private ModeloTablaEstaciones modeloTabla;
	private JScrollPane sp;
	private JFrame ventana;
	private JPanel padre;
	private JComboBox estado;
	Object[][] datos = { //Provisional
							{1, "Estación A", "7:00", "23:00", "Operativa"},
							{2, "Estación B", "7:00", "23:00", "En mantenimiento"},
	   						{3, "Estación C", "7:00", "23:00", "Operativa"},
					   };

	
	public MenuConsultarYModificarEstaciones(JFrame ventana, JPanel padre)
	{
		this.ventana = ventana;
		this.padre = padre;
		
		
	    this.setLayout(new GridBagLayout());
	    gbc = new GridBagConstraints();
		
	    btn1 = new JButton("Volver");
		estado = new JComboBox();
		
		modeloTabla = new ModeloTablaEstaciones();
		t = new JTable(modeloTabla);
	    sp = new JScrollPane(t);
	         
	    this.armarPanel();
	}
	
	@SuppressWarnings("unchecked")
	private void armarPanel()
	{
		estado.addItem("Operativa");
		estado.addItem("En mantenimiento");
		
		t.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(estado));
		t.setPreferredScrollableViewportSize(new Dimension(500, 70));
	    t.getModel().addTableModelListener(this);
	    t.getColumnModel().getColumn(0).setPreferredWidth(20);
	    t.getColumnModel().getColumn(4).setPreferredWidth(120);
	    
	    modeloTabla.setData(datos);
	
		this.add(sp);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.ipady = 15;
		gbc.insets = new Insets(30, 20, 10, 20);
		gbc.fill = GridBagConstraints.CENTER;
	    this.add(btn1, gbc);
	    btn1.addActionListener(
	    	e -> {
				ventana.setContentPane(padre);
				ventana.pack();
				ventana.setVisible(true);
			} 
	    );
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        
        System.out.println("Nuevo valor: " + data);
	}
}
