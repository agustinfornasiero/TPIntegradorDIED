package interfazGrafica;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MenuAgregarLineaDeTransporte extends JPanel
{
	private GridBagConstraints gbc;
	private JButton btn1, btn2;
	private JLabel lbl1, lbl2, lbl3;
	private JTextField txtf1, txtf2;
	private JComboBox<String> cb1;
	private JFrame ventana;
	private JPanel padre;
	
	public MenuAgregarLineaDeTransporte(JFrame ventana, JPanel padre)
	{
		this.ventana = ventana;
		this.padre = padre;
		gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}
	
	private void armarPanel() 
	{
		btn1 = new JButton("Aceptar");
		btn2 = new JButton("Cancelar");
		
		lbl1 = new JLabel("Nombre"); 
		lbl2 = new JLabel("Color");
		lbl3 = new JLabel("Estado");
		
		txtf1 = new JTextField(25);
		txtf2 = new JTextField(25);
		
		cb1 = new JComboBox<String>();
		cb1.addItem("Activa");
		cb1.addItem("No Activa");
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0.25;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 5, 5, 5);
		this.add(lbl1, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 0.75;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 5, 5, 5);
		this.add(txtf1, gbc);
		txtf1.addActionListener(e -> {}); // Pendiente
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.25;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(lbl2, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.75;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(txtf2, gbc);
		txtf2.addActionListener(
			e -> {
					txtf1.setText("");
					txtf2.setText("");
					cb1.setSelectedItem("Activa");
					txtf1.validate(); // Quiero que una vez ingresada una estacion se limpien los valores, pero no anda
					txtf2.validate();
					cb1.validate();
					// *Agregar a la DB*
				 }	
		); 
		
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
		this.add(cb1, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.EAST;
		gbc.ipady = 15;
		gbc.insets = new Insets(30, 20, 10, 20);
		this.add(btn1, gbc);
		btn1.addActionListener(e -> {});  // Pendiente
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.WEST;
		gbc.ipady = 15;
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
