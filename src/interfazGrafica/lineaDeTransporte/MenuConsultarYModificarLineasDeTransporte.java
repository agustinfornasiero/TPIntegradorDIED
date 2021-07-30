package interfazGrafica.lineaDeTransporte;

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

import grafo.RedDeTransporte;

@SuppressWarnings("serial")
public class MenuConsultarYModificarLineasDeTransporte extends JPanel implements TableModelListener
{
	@SuppressWarnings("serial")
	class ModeloTablaLineasDeTransporte extends AbstractTableModel
	{
		private String[] nombreColumnas = {"Id", "Nombre", "Color", "Estado"};
		private Object[][] datos = { {"", "", ""} };
		 
		public void setData(Object[][] datos) 			{ this.datos = datos; 					}
		public int getColumnCount() 					{ return nombreColumnas.length; 		}
		public int getRowCount() 						{ return datos.length; 					}
		public String getColumnName(int col) 			{ return nombreColumnas[col]; 			}
		public Object getValueAt(int row, int col) 		{ return datos[row][col]; 				}
		public Class getColumnClass(int c) 				{ return getValueAt(0, c).getClass(); 	}
		public boolean isCellEditable(int row, int col) { return true; 							}
		public void setValueAt(Object value, int row, int col) 	
		{
		    datos[row][col] = value;
		    fireTableCellUpdated(row, col);
		}
	 }
	
	private GridBagConstraints gbc;
	private JButton btn1;
	private JTable tabla;
	private ModeloTablaLineasDeTransporte modeloTabla;
	private JScrollPane sp;
	private JFrame ventana;
	private JPanel padre;
	private JComboBox estado;
	Object[][] datos = { //Provisional
							{1, "Linea 1", "Rojo", "Activa"},
							{2, "Linea 2", "Verde", "No Activa"},
	   						{3, "Linea 3", "Azul", "Activa"},
					   };
	
	private RedDeTransporte redDeTransporte;

	public MenuConsultarYModificarLineasDeTransporte(JFrame ventana, JPanel padre, RedDeTransporte redDeTransporte)
	{
		this.redDeTransporte = redDeTransporte;
		this.ventana = ventana;
		this.padre = padre;
	    this.setLayout(new GridBagLayout());
	    gbc = new GridBagConstraints();
	    this.armarPanel();
	}
	
	@SuppressWarnings("unchecked")
	private void armarPanel()
	{
		btn1 = new JButton("Volver");
		estado = new JComboBox<String>();
		modeloTabla = new ModeloTablaLineasDeTransporte();
		tabla = new JTable(modeloTabla);
	    sp = new JScrollPane(tabla);
		
		estado.addItem("Activa");
		estado.addItem("No Activa");
		
		tabla.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(estado));
		tabla.setPreferredScrollableViewportSize(new Dimension(600, 200));
	    tabla.getModel().addTableModelListener(this);
	    tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
	    tabla.getColumnModel().getColumn(3).setPreferredWidth(120);
	    
	    modeloTabla.setData(datos);
	
	    gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.ipady = 0;
		this.add(sp, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
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
