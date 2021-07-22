package interfazGrafica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuEliminarEstacion extends JPanel 
{
	private JFrame ventana;
	private JPanel padre;
	private GridBagConstraints gbc;
	private JComboBox<Integer> cb;
	private JButton btn1, btn2;
	private JLabel lbl1, lbl2;
	Integer[] opciones = {1, 2, 3}; // Provisional
	String[] nombresEstaciones = {"Estación A", "Estación B", "Estación C"}; // Provisional
	
	
	public MenuEliminarEstacion(JFrame ventana, JPanel padre)
	{
		this.ventana = ventana;
		this.padre = padre;
		gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}

	private void armarPanel() 
	{
		btn1 = new JButton("Eliminar");
		btn2 = new JButton("Volver");
		lbl1 = new JLabel("Seleccione la estación que desea eliminar: ");
		cb = new JComboBox<Integer>();
		
		// *Obtener ids estaciones*
		for (Integer i: opciones)
			cb.addItem(i);
		
		lbl2 = new JLabel("(" + nombresEstaciones[(int) cb.getSelectedItem() - 1] + ")");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 15;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.insets = new Insets(10, 20, 5, 20);
		this.add(lbl1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.ipady = 15;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.EAST;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.insets = new Insets(5, 20, 5, 5);
		this.add(lbl2, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.ipady = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.insets = new Insets(5, 20, 5, 20);
		this.add(cb, gbc);
		cb.addActionListener(
				e -> lbl2.setText("(" + nombresEstaciones[(int) cb.getSelectedItem() - 1] + ")")
		); // Reemplazar con una consulta que obtenga el nombre de estacion 
	
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.ipady = 15;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.WEST;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.insets = new Insets(5, 20, 0, 20);
		this.add(btn1, gbc);
		btn1.addActionListener(
			e -> {
					System.out.println(cb.getSelectedItem());
					cb.removeItem(cb.getSelectedItem());
					// *Eliminar de la DB*
					
				 }
		);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.ipady = 15;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.insets = new Insets(30, 20, 10, 20);
		this.add(btn2, gbc);
		btn2.addActionListener(
			e -> {
					ventana.setContentPane(padre);
					ventana.pack();
					ventana.setVisible(true);
				 }
		);
	}
}

