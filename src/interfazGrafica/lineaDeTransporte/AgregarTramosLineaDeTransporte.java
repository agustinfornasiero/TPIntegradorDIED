package interfazGrafica.lineaDeTransporte;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import entidades.Estacion;
import entidades.LineaDeTransporte;
import entidades.Tramo;
import entidades.Tramo.Estado;
import grafo.RedDeTransporte;

// https://stackoverflow.com/questions/3179136/jtable-how-to-refresh-table-model-after-insert-delete-or-update-the-data

public class AgregarTramosLineaDeTransporte extends JPanel 
{
	private ModeloTablaTramos mtt;
	private JTable tbl;
	private JScrollPane sp1;
	private JComboBox<String> cb1, cb2, cb3, cb4;
	private JButton btn1, btn2, btn3, btn4;
	private JLabel lbl1, lbl2, lbl3;
	private GridBagConstraints gbc;
	private JFrame ventana;
	private JPanel padre;
	
	private LinkedList<DuplaEstaciones> estacionesTramos;
	private RedDeTransporte redDeTransporte;
	private Map<String, Estacion> estacionesCb;
	private Map<String, LineaDeTransporte> lineasCb;
	
	public AgregarTramosLineaDeTransporte(JFrame ventana, JPanel padre, RedDeTransporte redDeTransporte) 
	{	
		this.ventana = ventana;
		this.padre = padre;
		this.redDeTransporte = redDeTransporte;
	
		estacionesTramos = new LinkedList<DuplaEstaciones>();
		estacionesCb = new HashMap<String, Estacion>();
		lineasCb = new HashMap<String, LineaDeTransporte>();
		gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}

	private void armarPanel() 
	{
		cb1 = new JComboBox<String>();
		cb2 = new JComboBox<String>();
		cb3 = new JComboBox<String>();
		
		for (LineaDeTransporte l : redDeTransporte.getAllLineasDeTransporte())
		{
			lineasCb.put(l.getNombre() + " (id: " + l.getId() + ")", l);
			cb1.addItem(l.getNombre() + " (id: " + l.getId() + ")");
		}
		
		for(Estacion e : redDeTransporte.getAllEstaciones())
		{
			estacionesCb.put(e.getNombre() + " (id: " + e.getId() + ")", e);
			cb2.addItem(e.getNombre() + " (id: " + e.getId() + ")");
			cb3.addItem(e.getNombre() + " (id: " + e.getId() + ")");
		}
		
		for (Tramo t : redDeTransporte.getAllTramos())
		
		btn1 = new JButton("Agregar");
		btn2 = new JButton("Eliminar");
		btn3 = new JButton("Volver");
		btn4 = new JButton("Aceptar");
		
		lbl1 = new JLabel("Línea de Transporte a completar: ");
		lbl2 = new JLabel("Especifique un tramo: ");
		
		lbl3 = new JLabel(">>>       ");
		
		mtt = new ModeloTablaTramos();
		tbl = new JTable(mtt);
		sp1 = new JScrollPane(tbl);
		mtt.setData(this.recuperarDatosEstacionesTramos());
		
	
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.ipady = 0;
		this.add(lbl1, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.8;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.ipady = 0;
		this.add(cb1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.ipady = 0;
		this.add(lbl2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.ipady = 0;
		this.add(cb2, gbc);
		ActionListener a = 
			e -> {
					DuplaEstaciones auxDuplaEstaciones = 
						new DuplaEstaciones(
							estacionesCb.get(cb2.getSelectedItem()),
							estacionesCb.get(cb3.getSelectedItem())
						);
					
					// Controlar que un tramo no tenga de origen y destino la misma estacion,
					// ni que haya tramos "repetidos"
					if(cb2.getSelectedItem().equals(cb3.getSelectedItem()) || 
					   estacionesTramos.contains(auxDuplaEstaciones))
						btn1.setEnabled(false);						
					else
						btn1.setEnabled(true);
		 	     };
		a.actionPerformed(null);
		cb2.addActionListener(a);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.ipady = 0;
		this.add(lbl3, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.weightx = 0.2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.ipady = 0;
		this.add(cb3, gbc);
		cb3.addActionListener(a);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.weightx = 0.2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.ipady = 15;
		this.add(btn1, gbc);
		btn1.addActionListener(
			e -> {
					estacionesTramos.add(
						new DuplaEstaciones(
							estacionesCb.get(cb2.getSelectedItem()),
							estacionesCb.get(cb3.getSelectedItem())
						)
					);
					mtt.setData(this.recuperarDatosEstacionesTramos());
					mtt.fireTableDataChanged();
					btn2.setEnabled(true);
					//btn1.setEnabled(false);
					// Hacer la estacion destino de un tramo el origen de la siguiente:
					cb2.setSelectedItem(cb3.getSelectedItem());
					cb3.setSelectedIndex((cb3.getSelectedIndex() + 1) % cb3.getItemCount());
				 }
		);
		
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.weightx = 0.2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.ipady = 15;
		btn2.setEnabled(false);
		this.add(btn2, gbc);
		btn2.addActionListener(
			e -> {
					ArrayList<Integer> filasSeleccionadas = arrayAArrayList(tbl.getSelectedRows());
					LinkedList<DuplaEstaciones> nuevoEstacionesTramos = new LinkedList<DuplaEstaciones>();
					
					for (Integer i = 0; i < estacionesTramos.size(); i++)
						if (!filasSeleccionadas.contains(i))
							nuevoEstacionesTramos.add(estacionesTramos.get((int) i));
					
					estacionesTramos = nuevoEstacionesTramos;
					
					mtt.setData(this.recuperarDatosEstacionesTramos());
					mtt.fireTableDataChanged();
						
					if (estacionesTramos.isEmpty())
						btn2.setEnabled(false);
				 }
		);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 1.0;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.ipady = 0;
		this.add(sp1, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.weightx = 0.2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.ipady = 15;
		this.add(btn3, gbc);
		btn3.addActionListener(
			e -> {
					ventana.setContentPane(padre);
					ventana.pack();
					ventana.setVisible(true);
				 }
		);

		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.weightx = 0.2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.ipady = 15;
		this.add(btn4, gbc);
		btn4.addActionListener(
			e -> {
					for (DuplaEstaciones d : estacionesTramos)
					{
						ventana.setContentPane(new AgregarDatosTramo(d.origen, d.destino, this));
						ventana.pack();
						ventana.setVisible(true);
					}
				 }
		);
	}
	
	private ArrayList<Integer> arrayAArrayList(int[] arr) 
	{
		ArrayList<Integer> arrL = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++)
			arrL.add(Integer.valueOf(arr[i]));
		
		return arrL;
	}

	public Object[][] recuperarDatosEstacionesTramos()
	{
		Object[][] datos = new Object[estacionesTramos.size()][2];
		
		for (Integer i = 0; i < estacionesTramos.size(); i++)
		{
			datos[i][0] = estacionesTramos.get(i).origen.getNombre() + " (id: " + estacionesTramos.get(i).origen.getId() + ")";
			datos[i][1] = estacionesTramos.get(i).destino.getNombre() + " (id: " + estacionesTramos.get(i).destino.getId() + ")";
		}
		
		return datos;
	}
	
	class AgregarDatosTramo extends JPanel
	{
		GridBagConstraints gbc;
		JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;
		JTextField txtf1, txtf2, txtf3, txtf4;
		JButton btn1;
		JComboBox<String> cb4;
		JPanel padre;
		
		Estacion estacionOrigen;
		Estacion estacionDestino;
		
		public AgregarDatosTramo(Estacion estacionOrigen, Estacion estacionDestino, JPanel padre) 
		{	
			this.estacionOrigen = estacionOrigen;
			this.estacionDestino = estacionDestino;
			this.padre = padre;
			gbc = new GridBagConstraints();
			this.setLayout(new GridBagLayout());
			this.armarPanel();
		}
		
		private void armarPanel()
		{
			lbl1 = new JLabel(
				estacionOrigen.getNombre() + " (id: " + estacionOrigen.getId() + ") >>> " +
				estacionDestino.getNombre() + " (id: " + estacionDestino.getId() + ")"
			);
			lbl2 = new JLabel("Distancia [km]");
			lbl3 = new JLabel("Duración del viaje [min]");
			lbl4 = new JLabel("Cantidad máxima de pasajeros");
			lbl5 = new JLabel("Estado");
			lbl6 = new JLabel("Costo [$]");
		
			btn1 = new JButton("Aceptar");
			
			txtf1 = new JTextField(25);
			txtf2 = new JTextField(25);
			txtf3 = new JTextField(25);
			txtf4 = new JTextField(25);
			
			cb4 = new JComboBox<String>();
			cb4.addItem("Activo");
			cb4.addItem("Inactivo");
			
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 2;
			gbc.weightx = 1.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(10, 5, 5, 5);
			this.add(lbl1, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.weightx = 0.25;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(10, 5, 5, 5);
			this.add(lbl2, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.gridwidth = 2;
			gbc.weightx = 0.75;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(10, 5, 5, 5);
			this.add(txtf1, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 1;
			gbc.weightx = 0.25;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(5, 5, 5, 5);
			this.add(lbl3, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 2;
			gbc.gridwidth = 2;
			gbc.weightx = 0.75;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(5, 5, 5, 5);
			this.add(txtf2, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 1;
			gbc.weightx = 0.25;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(5, 5, 5, 5);
			this.add(lbl4, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 3;
			gbc.gridwidth = 2;
			gbc.weightx = 0.75;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(5, 5, 5, 5);
			this.add(txtf3, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.gridwidth = 1;
			gbc.weightx = 0.25;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(5, 5, 5, 5);
			this.add(lbl5, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 4;
			gbc.gridwidth = 2;
			gbc.weightx = 0.75;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(5, 5, 5, 5);
			this.add(cb4, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 5;
			gbc.gridwidth = 1;
			gbc.weightx = 0.25;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(10, 5, 5, 5);
			this.add(lbl6, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 5;
			gbc.gridwidth = 2;
			gbc.weightx = 0.75;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(10, 5, 5, 5);
			this.add(txtf4, gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 6;
			gbc.gridwidth = 1;
			gbc.weightx = 0.0;
			gbc.fill = GridBagConstraints.EAST;
			gbc.ipady = 15;
			gbc.insets = new Insets(30, 20, 10, 20);
			this.add(btn1, gbc);
			btn1.addActionListener(
				e -> {
						Tramo.Estado estado;
						if (((String) cb1.getSelectedItem()).equals("Activo")) 
							estado = Tramo.Estado.ACTIVO;
						else	
							estado = Tramo.Estado.INACTIVO;
						
						agregarTramo
						(
							Double.parseDouble(txtf1.getText()),
							Integer.parseInt(txtf2.getText()),
							Integer.parseInt(txtf3.getText()),
							estado,
							Double.parseDouble(txtf4.getText()),
							estacionOrigen.getId(),
							estacionDestino.getId(),
							lineasCb.get(cb1.getSelectedItem()).getId()
						);
						
						ventana.setContentPane(padre);
						ventana.pack();
						ventana.setVisible(true);
					 }			
			);  
		}
		
		public void agregarTramo(Double distanciaEnKm, Integer duracionViajeEnMin, Integer cantidadMaximaDePasajeros, 
								 Tramo.Estado estado, Double costo, Integer idOrigen, Integer idDestino, 
								 Integer idLineaDeTransporte) 
		{
			Tramo auxTramo = new Tramo(
				distanciaEnKm, duracionViajeEnMin, cantidadMaximaDePasajeros, 
				estado, costo, idOrigen, idDestino, idLineaDeTransporte
			);
			
			try {
				redDeTransporte.addTramo(auxTramo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("serial")
	class ModeloTablaTramos extends AbstractTableModel
	{
		private String[] nombreColumnas = {"Origen", "Destino"};
		private Object[][] datos = {{"", ""}};
		 
		public void setData(Object[][] datos) 			{ this.datos = datos; 					}
		public int getColumnCount() 					{ return nombreColumnas.length; 		}
		public int getRowCount() 						{ return datos.length; 					}
		public String getColumnName(int col) 			{ return nombreColumnas[col]; 			}
		public Object getValueAt(int row, int col) 		{ return datos[row][col]; 				}
		public Class getColumnClass(int c) 				{ return getValueAt(0, c).getClass(); 	}
		public boolean isCellEditable(int row, int col) { return false;							}
		public void setValueAt(Object value, int row, int col) 	
		{
		    datos[row][col] = value;
		    fireTableCellUpdated(row, col);
		}
	 }
	
	class DuplaEstaciones
	{
		public Estacion origen;
		public Estacion destino;
		
		public DuplaEstaciones() { } 
		
		public DuplaEstaciones(Estacion origen, Estacion destino) 
		{
			this.origen = origen;
			this.destino = destino;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DuplaEstaciones other = (DuplaEstaciones) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (destino == null) {
				if (other.destino != null)
					return false;
			} else if (!destino.equals(other.destino))
				return false;
			if (origen == null) {
				if (other.origen != null)
					return false;
			} else if (!origen.equals(other.origen))
				return false;
			return true;
		}

		private AgregarTramosLineaDeTransporte getEnclosingInstance() {
			return AgregarTramosLineaDeTransporte.this;
		}
		
		public String toString()
		{
			return this.origen.toString() + " // " + this.destino.toString();
		}
	}
}
